package nextstep.signup.utils.validator

import nextstep.signup.R
import nextstep.signup.ui.model.SignUpInputModel


object PasswordValidator : SignUpInputValidator {

    override fun value(inputModel: SignUpInputModel) = inputModel.password
    override val validationRules = listOf(
        ValidationRule(
            regex = PASSWORD_LENGTH_REGEX,
            errorMessageResId = R.string.password_length_error_message,
            errorType = ValidationRule.ErrorType.OUT_OF_LENGTH_RANGE,
        ),
        ValidationRule(
            regex = PASSWORD_CHARACTER_TYPE_REGEX,
            errorMessageResId = R.string.password_character_type_error_message,
            errorType = ValidationRule.ErrorType.LACK_OF_CHARACTER_TYPE,
        ),
    )
}

private val PASSWORD_LENGTH_REGEX = Regex(pattern = "^.{8,16}$")
private val PASSWORD_CHARACTER_TYPE_REGEX =
    Regex(pattern = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$", option = RegexOption.IGNORE_CASE)
