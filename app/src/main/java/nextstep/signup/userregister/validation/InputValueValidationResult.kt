package nextstep.signup.userregister.validation

sealed interface InputValueValidationResult {

    data object Success: InputValueValidationResult
    data class Failure(val errorMessage: String): InputValueValidationResult
    data object Empty: InputValueValidationResult
}
