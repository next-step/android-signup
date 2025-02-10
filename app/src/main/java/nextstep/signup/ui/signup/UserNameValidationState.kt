package nextstep.signup.ui.signup

data class UserNameValidationState(
    val isInLength: Boolean = false,
    val hasNumber: Boolean = false,
    val hasSpecialCharacter: Boolean = false,
) {
    val isValidUsername: Boolean
        get() = isInLength && !hasNumber && !hasSpecialCharacter
}
