package nextstep.signup.ui.signup

import nextstep.signup.ui.signup.SignupInvalidationState.EMAIL_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_LENGTH_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success

sealed interface SignupInputType {
    data object Username : SignupInputType {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private val USERNAME_RANGE = 2..5

        fun String.isValid(): SignupValidationResult = when {
            length !in USERNAME_RANGE -> Failure(USERNAME_LENGTH_INVALIDATION)
            !matches(Regex(USERNAME_REGEX)) -> Failure(USERNAME_RULE_INVALIDATION)
            else -> Success
        }
    }

    data object Email : SignupInputType {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

        fun String.isValid(): SignupValidationResult = when {
            !matches(Regex(EMAIL_REGEX)) -> Failure(EMAIL_RULE_INVALIDATION)
            else -> Success
        }
    }

    data object Password : SignupInputType
}
