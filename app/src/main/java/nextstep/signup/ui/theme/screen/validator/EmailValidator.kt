package nextstep.signup.ui.theme.screen.validator

class EmailValidator : TextFieldValidator {
    override fun getErrorMessage(input: String): String {
        return if (!input.matches(Regex(EMAIL_REGEX))) {
            EMAIL_REX_ERROR
        } else {
            ""
        }
    }

    companion object {
        const val EMAIL_REX_ERROR = "이메일 형식이 올바르지 않습니다."

        const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    }
}
