package nextstep.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SignUpViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())

    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun updateUsername(username: String) {
        _uiState.update { currentState ->
            currentState.copy(username = username)
        }
    }

    fun updateEmail(email: String) {
        _uiState.update { currentState ->
            currentState.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _uiState.update { currentState ->
            currentState.copy(password = password)
        }
    }

    fun updatePasswordConfirm(passwordConfirm: String) {
        _uiState.update { currentState ->
            currentState.copy(passwordConfirm = passwordConfirm)
        }
    }

}