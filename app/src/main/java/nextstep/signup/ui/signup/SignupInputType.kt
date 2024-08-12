package nextstep.signup.ui.signup

import nextstep.signup.ui.signup.SignupInputType.Email
import nextstep.signup.ui.signup.SignupInputType.Password
import nextstep.signup.ui.signup.SignupInputType.Username
import nextstep.signup.ui.signup.SignupInvalidationState.EMAIL_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.PASSWORD_LENGTH_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.PASSWORD_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_LENGTH_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success

sealed interface SignupInputType {
    fun String.validate(): SignupValidationResult

    data object Username : SignupInputType {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private val USERNAME_RANGE = 2..5

        override fun String.validate(): SignupValidationResult = when {
            length !in USERNAME_RANGE -> Failure(USERNAME_LENGTH_INVALIDATION)
            !matches(Regex(USERNAME_REGEX)) -> Failure(USERNAME_RULE_INVALIDATION)
            else -> Success
        }
    }

    data object Email : SignupInputType {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

        override fun String.validate(): SignupValidationResult = when {
            !matches(Regex(EMAIL_REGEX)) -> Failure(EMAIL_RULE_INVALIDATION)
            else -> Success
        }
    }

    data object Password : SignupInputType {
        private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
        private val PASSWORD_RANGE = 8..16

        override fun String.validate(): SignupValidationResult = when {
            length !in PASSWORD_RANGE -> Failure(PASSWORD_LENGTH_INVALIDATION)
            !matches(Regex(PASSWORD_REGEX)) -> Failure(PASSWORD_RULE_INVALIDATION)
            else -> Success
        }
    }
}

inline fun <reified T : SignupInputType> String.isValid(): SignupValidationResult {
    return when (T::class) {
        Username::class -> Username.run { validate() }
        Email::class -> Email.run { validate() }
        Password::class -> Password.run { validate() }
        else -> throw IllegalArgumentException("Unknown SignupInputType")
    }
}
