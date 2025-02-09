package nextstep.signup.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SignUpState(
    private val inputValidator: InputValidator,
) {

    var userName by mutableStateOf("")
        private set
    var userNameValidation by mutableStateOf(UserNameValidationState())
        private set

    var email by mutableStateOf("")
        private set
    var emailValidation by mutableStateOf(false)
        private set

    var password by mutableStateOf("")
        private set
    var passwordValidation by mutableStateOf(PasswordValidation())
        private set

    var passwordConfirm by mutableStateOf("")
        private set
    var isPasswordConfirmValid by mutableStateOf(true)
        private set

    fun onAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.OnEmailChange -> {
                email = action.value
                emailValidation = inputValidator.checkEmail(action.value)
            }

            is SignUpAction.OnPasswordChange -> {
                password = action.value
                passwordValidation = inputValidator.checkPassword(action.value)
                isPasswordConfirmValid = action.value == passwordConfirm
            }

            is SignUpAction.OnPasswordConfirmChange -> {
                passwordConfirm = action.value
                isPasswordConfirmValid = password == action.value
            }

            SignUpAction.OnSignUpClick -> {

            }

            is SignUpAction.OnUsernameChange -> {
                userName = action.value
                userNameValidation = inputValidator.checkUserName(action.value)
            }
        }
    }
}
