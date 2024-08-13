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
    fun validate(value: T): R
}

@Immutable
interface ValidationResult {
    val isValid: Boolean
    val isError: Boolean
}

class UsernameValidation(
    private val regex: Regex = USERNAME_REGEX.toRegex(),
    private val lengthRange: IntRange = USERNAME_LENGTH_RANGE,
) : SignUpTextFieldValidation<String, UsernameValidationResult> {
    override fun validate(value: String): UsernameValidationResult = validateUsername(value)

    private fun validateUsername(username: String): UsernameValidationResult =
        when {
            username.isEmpty() -> UsernameValidationResult.Empty
            username.length !in lengthRange -> UsernameValidationResult.UsernameLengthError
            !regex.matches(username) -> UsernameValidationResult.UsernameFormatError
            else -> UsernameValidationResult.Success
        }

    sealed interface UsernameValidationResult : ValidationResult {
        override val isValid: Boolean
            get() = this is Success

        override val isError: Boolean
            get() = this is UsernameLengthError || this is UsernameFormatError

        data object Empty : UsernameValidationResult

        data object Success : UsernameValidationResult

        data object UsernameLengthError : UsernameValidationResult

        data object UsernameFormatError : UsernameValidationResult
    }

    companion object {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]*\$"
        private val USERNAME_LENGTH_RANGE = 2..5
    }
}

class EmailValidation(
    private val pattern: Pattern = PatternsCompat.EMAIL_ADDRESS,
) : SignUpTextFieldValidation<String, EmailValidationResult> {
    override fun validate(value: String): EmailValidationResult = validateEmail(value)

    private fun validateEmail(email: String): EmailValidationResult =
        when {
            email.isEmpty() -> EmailValidationResult.Empty
            !pattern.matcher(email).matches() -> EmailValidationResult.EmailFormatError
            else -> EmailValidationResult.Success
        }

    sealed interface EmailValidationResult : ValidationResult {
        override val isValid: Boolean
            get() = this is Success

        override val isError: Boolean
            get() = this is EmailFormatError

        data object Empty : EmailValidationResult

        data object Success : EmailValidationResult

        data object EmailFormatError : EmailValidationResult
    }
}

class PasswordValidation(
    private val regex: Regex = PASSWORD_REGEX.toRegex(),
    private val lengthRange: IntRange = PASSWORD_LENGTH_RANGE,
) : SignUpTextFieldValidation<String, PasswordValidationResult> {
    override fun validate(value: String): PasswordValidationResult = validatePassword(value)

    private fun validatePassword(password: String): PasswordValidationResult =
        when {
            password.isBlank() -> PasswordValidationResult.Empty
            password.length !in lengthRange -> PasswordValidationResult.PasswordLengthError
            !regex.matches(password) -> PasswordValidationResult.PasswordFormatError
            else -> PasswordValidationResult.Success
        }

    sealed interface PasswordValidationResult : ValidationResult {
        override val isValid: Boolean
            get() = this is Success

        override val isError: Boolean
            get() = this is PasswordFormatError || this is PasswordLengthError

        data object Empty : PasswordValidationResult

        data object Success : PasswordValidationResult

        data object PasswordLengthError : PasswordValidationResult

        data object PasswordFormatError : PasswordValidationResult
    }

    companion object {
        private val PASSWORD_LENGTH_RANGE = 8..16

        // 정규식 영문과 숫자 반드시 포함
        private const val PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*\$"
    }
}

class PasswordConfirmValidation : SignUpTextFieldValidation<PasswordConfirm, PasswordConfirmValidationResult> {
    override fun validate(value: PasswordConfirm): PasswordConfirmValidationResult =
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
            password != passwordConfirm -> PasswordConfirmValidationResult.PasswordNotMatchError
            else -> PasswordConfirmValidationResult.Success
        }

    sealed interface PasswordConfirmValidationResult : ValidationResult {
        override val isValid: Boolean
            get() = this is Success

        override val isError: Boolean
            get() = this is PasswordNotMatchError

        data object Empty : PasswordConfirmValidationResult

        data object Success : PasswordConfirmValidationResult

        data object PasswordNotMatchError : PasswordConfirmValidationResult
    }

    data class PasswordConfirm(
        val password: String,
        val passwordConfirm: String,
    )
}
