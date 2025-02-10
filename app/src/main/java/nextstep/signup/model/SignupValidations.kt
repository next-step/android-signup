package nextstep.signup.model

data class SignupValidations(
    val isUserNamePassed: Boolean = false,
    val isEmailPassed: Boolean = false,
    val isPasswordPassed: Boolean = false,
    val isPasswordConfirmPassed: Boolean = false,
) {
    fun isAllValidation() =
        isUserNamePassed && isEmailPassed && isPasswordPassed && isPasswordConfirmPassed
}
