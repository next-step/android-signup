package nextstep.signup.ui.theme.screen.validator

class NameValidator : TextFieldValidator {
    override fun getErrorMessage(input: String): String {
        return if (input.length !in USERNAME_MIN_LENGTH..USERNAME_MAX_LENGTH) {
            USERNAME_LENGTH_ERROR
        } else if (!input.matches(Regex(USERNAME_REGEX))) {
            USERNAME_REX_ERROR
        } else {
            ""
        }
    }

    companion object {
        const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        const val USERNAME_REX_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."

        const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"

        const val USERNAME_MIN_LENGTH = 2
        const val USERNAME_MAX_LENGTH = 5
    }
}
