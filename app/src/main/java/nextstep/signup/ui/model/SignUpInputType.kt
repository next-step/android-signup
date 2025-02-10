package nextstep.signup.ui.model

import android.content.res.Resources
import android.os.Build
import androidx.annotation.StringRes
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.R

enum class SignUpInputType(
    @StringRes val labelResId: Int,
    val keyboardType: KeyboardType,
    private val validationRules: List<ValidationRule>,
) {
    USERNAME(
        labelResId = R.string.username,
        keyboardType = KeyboardType.Text,
        validationRules = listOf(
            ValidationRule(
                regex = USERNAME_LENGTH_REGEX,
                errorMessageResId = R.string.username_length_error_message,
            ),
            ValidationRule(
                regex = USERNAME_CHARACTER_TYPE_REGEX,
                errorMessageResId = R.string.username_character_type_error_message,
            ),
        ),
    ),
    EMAIL(
        labelResId = R.string.email,
        keyboardType = KeyboardType.Email,
        validationRules = listOf(
            ValidationRule(
                regex = EMAIL_REGEX,
                errorMessageResId = R.string.email_error_message,
            ),
        ),
    ),
    PASSWORD(
        labelResId = R.string.password,
        keyboardType = KeyboardType.Password,
        validationRules = listOf(
            ValidationRule(
                regex = PASSWORD_LENGTH_REGEX,
                errorMessageResId = R.string.password_length_error_message,
            ),
            ValidationRule(
                regex = PASSWORD_CHARACTER_TYPE_REGEX,
                errorMessageResId = R.string.password_character_type_error_message,
            ),
        ),
    ),
    PASSWORD_CONFIRM(
        labelResId = R.string.password_confirm,
        keyboardType = KeyboardType.Password,
        validationRules = emptyList(),
    );

    fun hasNextField() = entries.last() != this

    fun isError(inputModel: SignUpInputModel): Boolean {
        val value = inputModel.getValueBySignUpInputType(this)

        if (value.isEmpty()) return false

        return if (this == PASSWORD_CONFIRM) {
            inputModel.run { passwordConfirm != password }
        } else {
            validate(value) != null
        }
    }

    fun errorMessageResId(inputModel: SignUpInputModel): Int? {
        return if (this == PASSWORD_CONFIRM) {
            if (inputModel.run { passwordConfirm != password }) R.string.password_is_equal_confirm_error_message else 0
        } else {
            validate(inputModel.getValueBySignUpInputType(this))?.errorMessageResId
        }
    }

    private fun validate(value: String) = validationRules.firstOrNull { !it.regex.matches(value) }
}

private data class ValidationRule(
    val regex: Regex,
    val errorMessageResId: Int,
)

private val USERNAME_LENGTH_REGEX = Regex(pattern = "^.{2,5}$")
private val USERNAME_CHARACTER_TYPE_REGEX =
    Regex(pattern = "^[a-zA-Z가-힣]+$", option = RegexOption.IGNORE_CASE)
private val EMAIL_REGEX =
    Regex(
        pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
        option = RegexOption.IGNORE_CASE
    )
private val PASSWORD_LENGTH_REGEX = Regex(pattern = "^.{8,16}$")
private val PASSWORD_CHARACTER_TYPE_REGEX =
    Regex(pattern = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$", option = RegexOption.IGNORE_CASE)