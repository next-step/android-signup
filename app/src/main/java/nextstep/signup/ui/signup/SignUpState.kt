package nextstep.signup.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SignUpState {

    var userName: String by mutableStateOf("")
        private set
    var email: String by mutableStateOf("")
        private set
    var password: String by mutableStateOf("")
        private set
    var passwordConfirm: String by mutableStateOf("")
        private set

    fun onAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.OnEmailChange -> {
                email = action.value
            }
            is SignUpAction.OnPasswordChange -> {
                password = action.value
            }
            is SignUpAction.OnPasswordConfirmChange -> {
                passwordConfirm = action.value
            }
            SignUpAction.OnSignUpClick -> {

            }
            is SignUpAction.OnUsernameChange -> {
                userName = action.value
            }
        }
    }
}
