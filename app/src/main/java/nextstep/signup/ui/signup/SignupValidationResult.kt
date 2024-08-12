package nextstep.signup.ui.signup

sealed interface SignupValidationResult {
    data object Success : SignupValidationResult
    data class Failure(val result: SignupInvalidationState) : SignupValidationResult
}
