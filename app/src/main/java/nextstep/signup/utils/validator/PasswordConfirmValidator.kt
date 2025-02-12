package nextstep.signup.utils.validator

import nextstep.signup.R
import nextstep.signup.ui.model.SignUpInputModel


object PasswordConfirmValidator : SignUpInputValidator {
    override fun value(inputModel: SignUpInputModel) = inputModel.password

    override val validationRules = emptyList<ValidationRule>()

    override fun state(model: SignUpInputModel): State {
        val value = value(model)

        if (value.isEmpty()) return State.NO_VALUE

        return if (model.password == model.passwordConfirm) State.VALID else State.INVALID
    }

    override fun errorMessageResId(model: SignUpInputModel): Int? {
        return if (hasError(model)) R.string.password_is_equal_confirm_error_message else null
    }

    override fun errorType(model: SignUpInputModel): ValidationRule.ErrorType? {
        return if (hasError(model)) ValidationRule.ErrorType.NOT_EQUAL_VALUES else null
    }
}