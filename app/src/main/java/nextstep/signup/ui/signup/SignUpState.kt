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
    val userNameValidation by derivedStateOf {
        inputValidator.checkUserName(userName)
    }

    var email by mutableStateOf("")
        private set
    val emailValidation by derivedStateOf {
        inputValidator.checkEmail(email)
    }

    var password by mutableStateOf("")
        private set
    val passwordValidation by derivedStateOf {
        inputValidator.checkPassword(password)
    }

    var passwordConfirm by mutableStateOf("")
        private set
    val isPasswordConfirmValid by derivedStateOf {
        password == passwordConfirm
    }

    val isInputAllValid by derivedStateOf {
        userNameValidation.isValidUsername && emailValidation && passwordValidation.isValidPassword && isPasswordConfirmValid
    }

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
}
