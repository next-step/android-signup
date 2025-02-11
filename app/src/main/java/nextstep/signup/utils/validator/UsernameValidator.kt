package nextstep.signup.utils.validator

import nextstep.signup.R
import nextstep.signup.ui.model.SignUpInputModel

object UsernameValidator : SignUpInputValidator {
    override fun value(inputModel: SignUpInputModel) = inputModel.username

    override val validationRules = listOf(
        ValidationRule(
            regex = USERNAME_LENGTH_REGEX,
            errorMessageResId = R.string.username_length_error_message,
            errorType = ValidationRule.ErrorType.OUT_OF_LENGTH_RANGE,
        ),
        ValidationRule(
            regex = USERNAME_CHARACTER_TYPE_REGEX,
            errorMessageResId = R.string.username_character_type_error_message,
            errorType = ValidationRule.ErrorType.INVALID_CHARACTER_TYPE_INCLUDED,
        ),
    )
}

private val USERNAME_LENGTH_REGEX = Regex(pattern = "^.{2,5}$")
private val USERNAME_CHARACTER_TYPE_REGEX =
    Regex(pattern = "^[a-zA-Z가-힣]+$", option = RegexOption.IGNORE_CASE)