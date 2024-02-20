package nextstep.signup.domain

data class InputUsername(
    val text: String,
) {
    private val validationState: ValidationState = when {
        text.length !in UsernameLengthRange -> ValidationState.OutOfLength
        !text.matches(UsernameRegex) -> ValidationState.HasNumberOrSymbol
        else -> ValidationState.Valid
    }

    fun isValid(): Boolean = this.validationState == ValidationState.Valid

    fun isOutOfLength(): Boolean = this.validationState == ValidationState.OutOfLength

    fun hasNumberOfSymbol() :Boolean = this.validationState == ValidationState.HasNumberOrSymbol

    companion object {
        private val UsernameLengthRange: IntRange = 2..5
        private val UsernameRegex: Regex = "^[a-zA-Z가-힣]+$".toRegex()
    }

    private enum class ValidationState {
        Valid,
        OutOfLength,
        HasNumberOrSymbol;
    }
}
