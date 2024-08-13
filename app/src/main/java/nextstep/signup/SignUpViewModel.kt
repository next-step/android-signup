package nextstep.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    var userName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordConfirm by mutableStateOf("")

    fun validateUserName(): Boolean {
        if(userName.isEmpty()) return true
        return userName.matches(Regex(REGEX_USER_NAME))
    }

    fun validateEmail(): Boolean {
        if(email.isEmpty()) return true
        return email.matches(Regex(REGEX_EMAIL))
    }

    fun validatePassword(): Boolean {
        if(password.isEmpty()) return true
        return password.matches(Regex(REGEX_PASSWORD))
    }

    fun validatePasswordConfirm(): Boolean {
        if(passwordConfirm.isEmpty()) return true
        return password == passwordConfirm
    }

    fun validateAll(): Boolean {
        return validateUserName() && validateEmail() && validatePassword() && validatePasswordConfirm()
    }

    companion object {
        const val REGEX_USER_NAME = "^[a-zA-Z가-힣]{2,5}$"
        const val REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        const val REGEX_PASSWORD = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
    }
}