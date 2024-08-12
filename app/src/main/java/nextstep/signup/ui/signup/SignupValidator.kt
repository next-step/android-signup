package nextstep.signup.ui.signup

import nextstep.signup.ui.signup.SignupInvalidationState.EMAIL_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.PASSWORD_CONFIRM_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.PASSWORD_LENGTH_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.PASSWORD_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_LENGTH_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success
import nextstep.signup.ui.signup.SignupValidator.Email
import nextstep.signup.ui.signup.SignupValidator.Password
import nextstep.signup.ui.signup.SignupValidator.PasswordConfirm
import nextstep.signup.ui.signup.SignupValidator.Username

sealed interface SignupValidator {

    data object Username : SignupValidator {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private val USERNAME_RANGE = 2..5

        fun String.validate(): SignupValidationResult = when {
            length !in USERNAME_RANGE -> Failure(USERNAME_LENGTH_INVALIDATION)
            !matches(Regex(USERNAME_REGEX)) -> Failure(USERNAME_RULE_INVALIDATION)
            else -> Success
        }
    }

    data object Email : SignupValidator {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

        fun String.validate(): SignupValidationResult = when {
            !matches(Regex(EMAIL_REGEX)) -> Failure(EMAIL_RULE_INVALIDATION)
            else -> Success
        }
    }

    data object Password : SignupValidator {
        private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
        private val PASSWORD_RANGE = 8..16

        fun String.validate(): SignupValidationResult = when {
            length !in PASSWORD_RANGE -> Failure(PASSWORD_LENGTH_INVALIDATION)
            !matches(Regex(PASSWORD_REGEX)) -> Failure(PASSWORD_RULE_INVALIDATION)
            else -> Success
        }
    }

    data object PasswordConfirm : SignupValidator {

        fun String.validate(password: String?): SignupValidationResult =
            when (this == password) {
                true -> Success
                false -> Failure(PASSWORD_CONFIRM_RULE_INVALIDATION)
            }
    }
}

inline fun <reified T : SignupValidator> String.isValid(password: String? = null): SignupValidationResult {
    return when (T::class) {
        Username::class -> Username.run { validate() }
        Email::class -> Email.run { validate() }
        Password::class -> Password.run { validate() }
        PasswordConfirm::class -> PasswordConfirm.run { validate(password) }
        else -> throw IllegalArgumentException("Unknown SignupInputType")
    }
}
