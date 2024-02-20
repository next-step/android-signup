package nextstep.signup.domain

data class InputPassword(
    val text: String,
) {
    private val validationState: ValidationState = when {
        text.length !in PasswordLengthRange -> ValidationState.OutOfLength
        !text.matches(PasswordRegex) -> ValidationState.HasNotAlphabetAndNumber
        else -> ValidationState.Valid
    }

    fun isValid(): Boolean = this.validationState == ValidationState.Valid

    fun isOutOfLength(): Boolean = this.validationState == ValidationState.OutOfLength

    fun hasNotAlphabetAndNumber(): Boolean = this.validationState == ValidationState.HasNotAlphabetAndNumber

    companion object {
        private val PasswordLengthRange: IntRange = 8..16
        private val PasswordRegex: Regex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$".toRegex()
    }

    private enum class ValidationState {
        Valid,
        OutOfLength,
        HasNotAlphabetAndNumber;
    }
}
