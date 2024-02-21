package nextstep.signup.domain

data class InputEmail(
    val text: String,
) {
    val isValid: Boolean = text.matches(EmailRegex)
    val isInvalid: Boolean = !isValid

    companion object {
        private val EmailRegex: Regex =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
    }
}
