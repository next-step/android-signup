package nextstep.signup.ui.component

import androidx.compose.runtime.Immutable
import androidx.core.util.PatternsCompat
import nextstep.signup.ui.component.EmailValidation.EmailValidationResult
import nextstep.signup.ui.component.PasswordConfirmValidation.PasswordConfirm
import nextstep.signup.ui.component.PasswordConfirmValidation.PasswordConfirmValidationResult
import nextstep.signup.ui.component.PasswordValidation.PasswordValidationResult
import nextstep.signup.ui.component.UsernameValidation.UsernameValidationResult
import java.util.regex.Pattern

@Immutable
interface SignUpTextFieldValidation<T, R : ValidationResult> {
    fun isValid(value: T): R
}

@Immutable
interface ValidationResult {
    val isSuccessFull: Boolean
    val isFailure: Boolean
}

class UsernameValidation(
    private val regex: Regex = USERNAME_REGEX.toRegex(),
    private val lengthRange: IntRange = USERNAME_LENGTH_RANGE,
) : SignUpTextFieldValidation<String, UsernameValidationResult> {
    override fun isValid(value: String): UsernameValidationResult = validateUsername(value)

    private fun validateUsername(username: String): UsernameValidationResult =
        when {
            username.isEmpty() -> UsernameValidationResult.Empty
            username.length !in lengthRange -> UsernameValidationResult.FailureUsernameLength
            !regex.matches(username) -> UsernameValidationResult.FailureUsernameFormat
            else -> UsernameValidationResult.Success
        }

    sealed interface UsernameValidationResult : ValidationResult {
        data object Empty : UsernameValidationResult {
            override val isFailure: Boolean = false
            override val isSuccessFull: Boolean = false
        }

        data object Success : UsernameValidationResult {
            override val isFailure: Boolean = false
            override val isSuccessFull: Boolean = true
        }

        data object FailureUsernameLength : UsernameValidationResult {
            override val isFailure: Boolean = true
            override val isSuccessFull: Boolean = false
        }

        data object FailureUsernameFormat : UsernameValidationResult {
            override val isFailure: Boolean = true
            override val isSuccessFull: Boolean = false
        }
    }

    companion object {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]*\$"
        private val USERNAME_LENGTH_RANGE = 2..5
    }
}

class EmailValidation(
    private val pattern: Pattern = PatternsCompat.EMAIL_ADDRESS,
) : SignUpTextFieldValidation<String, EmailValidationResult> {
    override fun isValid(value: String): EmailValidationResult = validateEmail(value)

    private fun validateEmail(email: String): EmailValidationResult =
        when {
            email.isEmpty() -> EmailValidationResult.Empty
            !pattern.matcher(email).matches() -> EmailValidationResult.FailureEmailFormat
            else -> EmailValidationResult.Success
        }

    sealed interface EmailValidationResult : ValidationResult {
        data object Empty : EmailValidationResult {
            override val isFailure: Boolean = false
            override val isSuccessFull: Boolean = false
        }

        data object Success : EmailValidationResult {
            override val isFailure: Boolean = false
            override val isSuccessFull: Boolean = true
        }

        data object FailureEmailFormat : EmailValidationResult {
            override val isFailure: Boolean = true
            override val isSuccessFull: Boolean = false
        }
    }
}

class PasswordValidation(
    private val regex: Regex = PASSWORD_REGEX.toRegex(),
    private val lengthRange: IntRange = PASSWORD_LENGTH_RANGE,
) : SignUpTextFieldValidation<String, PasswordValidationResult> {
    override fun isValid(value: String): PasswordValidationResult = validatePassword(value)

    private fun validatePassword(password: String): PasswordValidationResult =
        when {
            password.isBlank() -> PasswordValidationResult.Empty
            password.length !in lengthRange -> PasswordValidationResult.FailurePasswordLength
            !regex.matches(password) -> PasswordValidationResult.FailurePasswordFormat
            else -> PasswordValidationResult.Success
        }

    sealed interface PasswordValidationResult : ValidationResult {
        data object Empty : PasswordValidationResult {
            override val isFailure: Boolean = false
            override val isSuccessFull: Boolean = false
        }

        data object Success : PasswordValidationResult {
            override val isFailure: Boolean = false
            override val isSuccessFull: Boolean = true
        }

        data object FailurePasswordLength : PasswordValidationResult {
            override val isFailure: Boolean = true
            override val isSuccessFull: Boolean = false
        }

        data object FailurePasswordFormat : PasswordValidationResult {
            override val isFailure: Boolean = true
            override val isSuccessFull: Boolean = false
        }
    }

    companion object {
        private val PASSWORD_LENGTH_RANGE = 8..16

        // 정규식 영문과 숫자 반드시 포함
        private const val PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*\$"
    }
}

class PasswordConfirmValidation : SignUpTextFieldValidation<PasswordConfirm, PasswordConfirmValidationResult> {
    override fun isValid(value: PasswordConfirm): PasswordConfirmValidationResult =
        validatePasswordConfirm(
            value.password,
            value.passwordConfirm,
        )

    private fun validatePasswordConfirm(
        password: String,
        passwordConfirm: String,
    ): PasswordConfirmValidationResult =
        when {
            password.isBlank() || passwordConfirm.isBlank() -> PasswordConfirmValidationResult.Empty
            password != passwordConfirm -> PasswordConfirmValidationResult.FailurePasswordNotMatch
            else -> PasswordConfirmValidationResult.Success
        }

    sealed interface PasswordConfirmValidationResult : ValidationResult {
        data object Empty : PasswordConfirmValidationResult {
            override val isFailure: Boolean = false
            override val isSuccessFull: Boolean = false
        }

        data object Success : PasswordConfirmValidationResult {
            override val isFailure: Boolean = false
            override val isSuccessFull: Boolean = true
        }

        data object FailurePasswordNotMatch : PasswordConfirmValidationResult {
            override val isFailure: Boolean = true
            override val isSuccessFull: Boolean = false
        }
    }

    data class PasswordConfirm(
        val password: String,
        val passwordConfirm: String,
    )
}
