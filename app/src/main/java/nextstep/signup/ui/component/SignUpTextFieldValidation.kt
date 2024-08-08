package nextstep.signup.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember

@Immutable
interface SignUpTextFieldValidation {
    var errorType: ValidationErrorType?

    fun isValid(): Boolean

    interface ValidationErrorType
}

data class UsernameValidation(
    private val value: String,
    private val regex: String = USERNAME_REGEX,
    private val lengthRange: IntRange = USERNAME_LENGTH_RANGE,
) : SignUpTextFieldValidation {
    override var errorType: SignUpTextFieldValidation.ValidationErrorType? = null

    override fun isValid(): Boolean =
        validateUsername(value).also {
            errorType = it
        } == null

    private fun validateUsername(username: String): ErrorType? =
        when {
            username.isEmpty() -> null
            username.length !in lengthRange -> ErrorType.LENGTH
            !regex.toRegex().matches(username) -> ErrorType.FORMAT
            else -> null
        }

    enum class ErrorType : SignUpTextFieldValidation.ValidationErrorType {
        LENGTH,
        FORMAT,
    }

    companion object {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]*\$"
        private val USERNAME_LENGTH_RANGE = 2..5
    }
}

@Composable
fun rememberSignUpTextFieldValidation(
    vararg values: Any,
    validation: () -> SignUpTextFieldValidation,
): SignUpTextFieldValidation =
    remember(values) {
        validation()
    }
