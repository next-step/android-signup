package nextstep.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    var userNameValue by mutableStateOf("")
        private set
    var emailValue by mutableStateOf("")
        private set
    var passwordValue by mutableStateOf("")
        private set
    var passwordConfirmValue by mutableStateOf("")
        private set

    var isUserNameError: Boolean by mutableStateOf(false)
        private set
    var isEmailError: Boolean by mutableStateOf(false)
        private set
    var isPasswordError: Boolean by mutableStateOf(false)
        private set
    var isPasswordConfirmError: Boolean by mutableStateOf(false)
        private set

    fun setUserName(value: String) {
        userNameValue = value
        isUserNameError = validateUserName()
    }

    fun setEmail(value: String) {
        emailValue = value
        isEmailError = validateEmail()
    }

    fun setPassword(value: String) {
        passwordValue = value
        isPasswordError = validatePassword()
    }

    fun setPasswordConfirm(value: String) {
        passwordConfirmValue = value
        isPasswordConfirmError = validatePasswordConfirm()
    }

    private fun validateUserName(): Boolean {
        if (userNameValue.isEmpty()) return true
        return userNameValue.matches(Regex(REGEX_USER_NAME))
    }

    private fun validateEmail(): Boolean {
        if (emailValue.isEmpty()) return true
        return emailValue.matches(Regex(REGEX_EMAIL))
    }

    private fun validatePassword(): Boolean {
        if (passwordValue.isEmpty()) return true
        return passwordValue.matches(Regex(REGEX_PASSWORD))
    }

    private fun validatePasswordConfirm(): Boolean {
        if (passwordConfirmValue.isEmpty()) return true
        return passwordValue == passwordConfirmValue
    }

    fun validateAll(): Boolean {
        return isUserNameError && isEmailError && isPasswordError && isPasswordConfirmError
    }

    companion object {
        const val REGEX_USER_NAME = "^[a-zA-Z가-힣]{2,5}$"
        const val REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        const val REGEX_PASSWORD = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
    }
}