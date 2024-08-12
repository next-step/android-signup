package nextstep.signup.valid

import nextstep.signup.model.EmailState
import nextstep.signup.model.PasswordConfirmState
import nextstep.signup.model.PasswordError
import nextstep.signup.model.PasswordState
import nextstep.signup.model.UserNameError
import nextstep.signup.model.UserNameState

class RegexBasedSignUpValidator : SignUpValidator {
    override fun validateUsername(username: String): UserNameState {
        return when {
            username.isEmpty() -> UserNameState.Valid
            username.length !in 2..5 -> UserNameState.Invalid(UserNameError.Length)
            !username.matches(Regex(USERNAME_REGEX)) -> UserNameState.Invalid(UserNameError.InvalidCharacters)
            else -> UserNameState.Valid
        }
    }

    override fun validateEmail(email: String): EmailState {
        return when {
            email.isEmpty() -> EmailState.Initial
            email.matches(Regex(EMAIL_REGEX)) -> EmailState.Valid
            else -> EmailState.Invalid
        }
    }

    override fun validatePassword(password: String): PasswordState {
        return when {
            password.isEmpty() -> PasswordState.Initial
            password.length !in 8..16 -> PasswordState.Invalid(PasswordError.Length)
            !password.matches(Regex(PASSWORD_REGEX)) -> PasswordState.Invalid(
                PasswordError.Complexity
            )
            else -> PasswordState.Valid
        }
    }

    override fun validatePasswordConfirm(
        password: String, passwordConfirm: String
    ): PasswordConfirmState {
        return when {
            passwordConfirm.isEmpty() -> PasswordConfirmState.Initial
            password == passwordConfirm -> PasswordConfirmState.Valid
            else -> PasswordConfirmState.Mismatch
        }
    }

    companion object {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
    }
}
