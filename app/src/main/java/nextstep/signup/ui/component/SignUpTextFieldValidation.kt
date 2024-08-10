package nextstep.signup.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.core.util.PatternsCompat
import java.util.regex.Pattern

@Immutable
interface SignUpTextFieldValidation<T> {
    fun isValid(value: T): ValidationResult
}

sealed interface ValidationResult {
    val isFailure: Boolean

    data object Empty : ValidationResult {
        override val isFailure: Boolean = false
    }

    data object Success : ValidationResult {
        override val isFailure: Boolean = false
    }
}

class UsernameValidation(
    private val regex: String = USERNAME_REGEX,
    private val lengthRange: IntRange = USERNAME_LENGTH_RANGE,
) : SignUpTextFieldValidation<String> {
    override fun isValid(value: String): ValidationResult = validateUsername(value)

    private fun validateUsername(username: String): ValidationResult =
        when {
            username.isEmpty() -> ValidationResult.Empty
            username.length !in lengthRange -> FailureUsernameLength
            !regex.toRegex().matches(username) -> FailureUsernameFormat
            else -> ValidationResult.Success
        }

    data object FailureUsernameLength : ValidationResult {
        override val isFailure: Boolean = true
    }

    data object FailureUsernameFormat : ValidationResult {
        override val isFailure: Boolean = true
    }

    companion object {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]*\$"
        private val USERNAME_LENGTH_RANGE = 2..5
    }
}

class EmailValidation(
    private val pattern: Pattern = PatternsCompat.EMAIL_ADDRESS,
) : SignUpTextFieldValidation<String> {
    override fun isValid(value: String): ValidationResult = validateEmail(value)

    private fun validateEmail(email: String): ValidationResult =
        when {
            email.isEmpty() -> ValidationResult.Empty
            !pattern.matcher(email).matches() -> FailureEmailFormat
            else -> ValidationResult.Success
        }

    data object FailureEmailFormat : ValidationResult {
        override val isFailure: Boolean = true
    }
}

class PasswordValidation(
    private val regex: String = PASSWORD_REGEX,
    private val lengthRange: IntRange = PASSWORD_LENGTH_RANGE,
) : SignUpTextFieldValidation<String> {
    override fun isValid(value: String): ValidationResult = validatePassword(value)

    private fun validatePassword(password: String): ValidationResult =
        when {
            password.isEmpty() -> ValidationResult.Empty
            password.length !in lengthRange -> FailurePasswordLength
            !regex.toRegex().matches(password) -> FailurePasswordFormat
            else -> ValidationResult.Success
        }

    data object FailurePasswordLength : ValidationResult {
        override val isFailure: Boolean = true
    }

    data object FailurePasswordFormat : ValidationResult {
        override val isFailure: Boolean = true
    }

    companion object {
        private val PASSWORD_LENGTH_RANGE = 8..16

        // 정규식 영문과 숫자 반드시 포함
        private const val PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*\$"
    }
}

class PasswordConfirmValidation : SignUpTextFieldValidation<PasswordConfirmValidation.PasswordConfirm> {
    override fun isValid(value: PasswordConfirm): ValidationResult =
        validatePasswordConfirm(
            value.password,
            value.passwordConfirm,
        )

    private fun validatePasswordConfirm(
        password: String,
        passwordConfirm: String,
    ): ValidationResult =
        when {
            password.isBlank() || passwordConfirm.isBlank() -> ValidationResult.Empty
            password != passwordConfirm -> FailurePasswordNotMatch
            else -> ValidationResult.Success
        }

    data object FailurePasswordNotMatch : ValidationResult {
        override val isFailure: Boolean = true
    }

    data class PasswordConfirm(
        val password: String,
        val passwordConfirm: String,
    )
}

@Composable
fun <T> rememberSignUpTextFieldValidation(validation: () -> SignUpTextFieldValidation<T>): SignUpTextFieldValidation<T> =
    remember {
        validation()
    }
