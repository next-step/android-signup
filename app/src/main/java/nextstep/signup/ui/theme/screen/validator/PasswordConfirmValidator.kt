package nextstep.signup.ui.theme.screen.validator

class PasswordConfirmValidator(private val anotherInout: String) : TextFieldValidator {
    override fun getErrorMessage(input: String): String {
        return if (input != anotherInout) {
            PASSWORD_COMPARE_ERROR
        } else {
            ""
        }
    }

    companion object {
        private const val PASSWORD_COMPARE_ERROR = "비밀번호가 일치하지 않습니다."
    }
}
