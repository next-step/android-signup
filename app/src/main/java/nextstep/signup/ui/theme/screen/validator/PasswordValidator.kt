package nextstep.signup.ui.theme.screen.validator

class PasswordValidator : TextFieldValidator {
    override fun getErrorMessage(input: String): String {
        return if (input.length !in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH) {
            PASSWORD_LENGTH_ERROR
        } else if (!input.matches(Regex(PASSWORD_REGEX))) {
            PASSWORD_REGEX_ERROR
        } else {
            ""
        }
    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_REGEX_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."

        const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

        const val PASSWORD_MIN_LENGTH = 8
        const val PASSWORD_MAX_LENGTH = 16
    }
}
