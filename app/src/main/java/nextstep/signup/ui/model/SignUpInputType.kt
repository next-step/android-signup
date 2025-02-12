package nextstep.signup.ui.model

import androidx.annotation.StringRes
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R
import nextstep.signup.utils.validator.EmailValidator
import nextstep.signup.utils.validator.PasswordConfirmValidator
import nextstep.signup.utils.validator.PasswordValidator
import nextstep.signup.utils.validator.SignUpInputValidator
import nextstep.signup.utils.validator.UsernameValidator

enum class SignUpInputType(
    @StringRes val labelResId: Int,
    val keyboardType: KeyboardType,
    private val validator: SignUpInputValidator,
) {
    USERNAME(
        labelResId = R.string.username,
        keyboardType = KeyboardType.Text,
        validator = UsernameValidator,
    ),
    EMAIL(
        labelResId = R.string.email,
        keyboardType = KeyboardType.Email,
        validator = EmailValidator,
    ),
    PASSWORD(
        labelResId = R.string.password,
        keyboardType = KeyboardType.Password,
        validator = PasswordValidator,

        ),
    PASSWORD_CONFIRM(
        labelResId = R.string.password_confirm,
        keyboardType = KeyboardType.Password,
        validator = PasswordConfirmValidator,
    );

    fun hasNextField() = entries.last() != this

    fun hasError(inputModel: SignUpInputModel): Boolean {
        return validator.hasError(inputModel)
    }

    fun errorMessageResId(inputModel: SignUpInputModel): Int? {
        return validator.errorMessageResId(inputModel)
    }

    fun isValid(inputModel: SignUpInputModel): Boolean {
        return validator.isValid(inputModel)
    }
}
