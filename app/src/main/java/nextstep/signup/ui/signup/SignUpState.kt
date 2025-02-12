package nextstep.signup.ui.signup

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SignUpState(
    private val inputValidator: InputValidator,
) {

    var userName by mutableStateOf("")
        private set
    val userNameValidation: UserNameValidationState by derivedStateOf {
        inputValidator.checkUserName(userName)
    }

    var email by mutableStateOf("")
        private set
    val isEmailValid: Boolean by derivedStateOf {
        inputValidator.checkEmail(email)
    }

    var password by mutableStateOf("")
        private set
    val passwordValidation : PasswordValidation by derivedStateOf {
        inputValidator.checkPassword(password)
    }

    var passwordConfirm by mutableStateOf("")
        private set
    val isPasswordConfirmValid: Boolean by derivedStateOf {
        password == passwordConfirm
    }

    val isInputAllValid: Boolean by derivedStateOf {
        userNameValidation.isValidUsername && isEmailValid && passwordValidation.isValidPassword && isPasswordConfirmValid
    }

    var isSignUpSuccess: Boolean? by mutableStateOf(null)
        private set

    fun updateUserName(value: String) {
        userName = value
    }

    fun updateEmail(value: String) {
        email = value
    }

    fun updatePassword(value: String) {
        password = value
    }

    fun updatePasswordConfirm(value: String) {
        passwordConfirm = value
    }

    fun updateSignUpResult(result: Boolean?) {
        isSignUpSuccess = result
    }
}
