package nextstep.signup.ui.theme.screen.validator

interface TextFieldValidator {
    fun getErrorMessage(input: String): String
}