package nextstep.signup.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.core.util.PatternsCompat

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

data class EmailValidation(
    private val value: String,
) : SignUpTextFieldValidation {
    override var errorType: SignUpTextFieldValidation.ValidationErrorType? = null

    override fun isValid(): Boolean =
        validateEmail(value).also {
            errorType = it
        } == null

    private fun validateEmail(email: String): ErrorType? =
        when {
            email.isEmpty() -> null
            !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() -> ErrorType.FORMAT
            else -> null
        }

    enum class ErrorType : SignUpTextFieldValidation.ValidationErrorType {
        FORMAT,
    }
}

data class PasswordValidation(
    private val value: String,
    private val regex: String = PASSWORD_REGEX,
    private val lengthRange: IntRange = PASSWORD_LENGTH_RANGE,
) : SignUpTextFieldValidation {
    override var errorType: SignUpTextFieldValidation.ValidationErrorType? = null

    override fun isValid(): Boolean =
        validatePassword(value).also {
            errorType = it
        } == null

    private fun validatePassword(password: String): ErrorType? =
        when {
            password.isEmpty() -> null
            password.length !in lengthRange -> ErrorType.LENGTH
            !PASSWORD_REGEX.toRegex().matches(password) -> ErrorType.FORMAT
            else -> null
        }

    enum class ErrorType : SignUpTextFieldValidation.ValidationErrorType {
        LENGTH,
        FORMAT,
    }

    companion object {
        private val PASSWORD_LENGTH_RANGE = 8..16

        // 정규식 영문과 숫자 반드시 포함
        private const val PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*\$"
    }
}

data class PasswordConfirmValidation(
    private val password: String,
    private val passwordConfirm: String,
) : SignUpTextFieldValidation {
    override var errorType: SignUpTextFieldValidation.ValidationErrorType? = null

    override fun isValid(): Boolean =
        validatePasswordConfirm(password, passwordConfirm).also {
            errorType = it
        } == null

    private fun validatePasswordConfirm(
        password: String,
        passwordConfirm: String,
    ): ErrorType? =
        when {
            password.isEmpty() || passwordConfirm.isEmpty() -> null
            password != passwordConfirm -> ErrorType.NOT_MATCH
            else -> null
        }

    enum class ErrorType : SignUpTextFieldValidation.ValidationErrorType {
        NOT_MATCH,
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
