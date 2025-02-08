package nextstep.signup.ui.model

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
    );

    fun hasNextField() = entries.last() != this

    private fun validate(value: String) = validationRules.firstOrNull { !it.regex.matches(value) }

    fun isValid(value: String) = validate(value) == null
    fun errorMessageResId(value: String) = validate(value)?.errorMessageResId ?: 0
}

data class ValidationRule(
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