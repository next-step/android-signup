package nextstep.signup.utils.validator

import nextstep.signup.R
import nextstep.signup.ui.model.SignUpInputModel


object EmailValidator : SignUpInputValidator {
    override fun value(inputModel: SignUpInputModel) = inputModel.email

    override val validationRules = listOf(
        ValidationRule(
            regex = EMAIL_REGEX,
            errorMessageResId = R.string.email_error_message,
            ValidationRule.ErrorType.INVALID_FORM,
        ),
    )
}

private val EMAIL_REGEX =
    Regex(
        pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
        option = RegexOption.IGNORE_CASE
    )