package nextstep.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SignUpViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())

    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    var username by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var passwordConfirm by mutableStateOf("")
        private set

    private val usernameRegex = "^[a-zA-Z가-힣]+$"
    private val emailREGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    private val passwordREGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

    fun updateUsername(value: String) {
        username = value
        checkUsernameValidation(value)
    }

    fun updateEmail(value: String) {
        email = value
        checkEmailValidation(value)
    }

    fun updatePassword(value: String) {
        password = value
        checkPasswordValidation(value)
    }

    fun updatePasswordConfirm(value: String) {
        passwordConfirm = value
        checkPasswordConfirmValidation(value)
    }

    private fun checkUsernameValidation(username: String) {

        if (username.isEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    isUsernameLengthError = false, isUsernameFormatError = false
                )
            }
        } else if (!username.matches(Regex(usernameRegex))) {
            _uiState.update { currentState ->
                currentState.copy(
                    isUsernameLengthError = false, isUsernameFormatError = true
                )
            }
        } else if (username.length !in 2..5) {
            _uiState.update { currentState ->
                currentState.copy(
                    isUsernameLengthError = true, isUsernameFormatError = false
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isUsernameLengthError = false, isUsernameFormatError = false
                )
            }
        }
    }

    private fun checkEmailValidation(email: String) {
        if (email.isEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    isEmailFormatError = false
                )
            }
        } else if (!email.matches(Regex(emailREGEX))) {
            _uiState.update { currentState ->
                currentState.copy(
                   isEmailFormatError = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isEmailFormatError = false
                )
            }
        }
    }

    private fun checkPasswordValidation(password: String) {
        if (password.isEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    isPasswordValidationError = false
                )
            }
        } else if (!password.matches(Regex(passwordREGEX))) {
            _uiState.update { currentState ->
                currentState.copy(
                    isPasswordValidationError = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isPasswordValidationError = false
                )
            }
        }
    }

    private fun checkPasswordConfirmValidation(passwordConfirm: String) {
        if (passwordConfirm != password) {
            _uiState.update { currentState ->
                currentState.copy(
                    isPasswordMismatchError = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isPasswordMismatchError = false
                )
            }
        }
    }

}