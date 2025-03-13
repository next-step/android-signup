package nextstep.signup.ui.state

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import nextstep.signup.ui.util.EmailValidator
import nextstep.signup.ui.util.NameValidator
import nextstep.signup.ui.util.PasswordMatchValidator
import nextstep.signup.ui.util.PasswordValidator
import nextstep.signup.ui.util.ValidationResult

class SignupScreenState(
    initialUsername: String = EMPTY_STRING,
    initialEmail: String = EMPTY_STRING,
    initialPassword: String = EMPTY_STRING,
    initialPasswordConfirm: String = EMPTY_STRING,
) {
    var username by mutableStateOf(initialUsername)
        private set
    var email by mutableStateOf(initialEmail)
        private set
    var password by mutableStateOf(initialPassword)
        private set
    var passwordConfirm by mutableStateOf(initialPasswordConfirm)
        private set

    val usernameValidation by derivedStateOf {
        NameValidator.validate(username)
    }

    val emailValidation by derivedStateOf {
        EmailValidator.validate(email)
    }

    val passwordValidation by derivedStateOf {
        PasswordValidator.validate(password)
    }

    val passwordConfirmValidation by derivedStateOf {
        PasswordMatchValidator.validate(password, passwordConfirm)
    }

    val isFormValid by derivedStateOf {
        usernameValidation == ValidationResult.Correct &&
                emailValidation == ValidationResult.Correct &&
                passwordValidation == ValidationResult.Correct &&
                passwordConfirmValidation == ValidationResult.Correct
    }

    fun updateUsername(newValue: String) {
        username = newValue
    }

    fun updateEmail(newValue: String) {
        email = newValue
    }

    fun updatePassword(newValue: String) {
        password = newValue
    }

    fun updatePasswordConfirm(newValue: String) {
        passwordConfirm = newValue
    }
}

private const val EMPTY_STRING = ""