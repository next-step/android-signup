package nextstep.signup.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpScreenUiState.Initial)
    val uiState: StateFlow<SignUpScreenUiState> = _uiState.asStateFlow()

    fun updateUserName(username: String) {
        val isUserNameLengthOutOfRange = username.length !in UsernameLengthRange
        val isUserNameHasNumberOrSymbol = !username.matches(UsernameRegex)

        _uiState.update {
            it.copy(
                username = username,
                isUserNameLengthOutOfRange = isUserNameLengthOutOfRange,
                isUserNameHasNumberOrSymbol = isUserNameHasNumberOrSymbol,
            )
        }
    }

    fun updateEmail(email: String) {
        val isInvalidEmail = !email.matches(EmailRegex)

        _uiState.update {
            it.copy(
                email = email,
                isInvalidEmail = isInvalidEmail,
            )
        }
    }

    fun updatePassword(password: String) {
        val isPasswordLengthOutOfRange = password.length !in PasswordLengthRange
        val isPasswordHasNotAlphabetAndNumber = !password.matches(PasswordRegex)

        _uiState.update {
            it.copy(
                password = password,
                isPasswordLengthOutOfRange = isPasswordLengthOutOfRange,
                isPasswordHasNotAlphabetAndNumber = isPasswordHasNotAlphabetAndNumber,
            )
        }
    }

    fun updatePasswordConfirm(passwordConfirm: String) {
        _uiState.update {
            it.copy(
                passwordConfirm = passwordConfirm,
                isPasswordConfirmNotMatched = it.password != passwordConfirm,
            )
        }
    }

    companion object {
        private val UsernameLengthRange: IntRange = 2..5
        private val UsernameRegex: Regex = "^[a-zA-Z가-힣]+$".toRegex()

        private val EmailRegex: Regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
            .toRegex()

        private val PasswordLengthRange: IntRange = 8..16
        private val PasswordRegex: Regex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$".toRegex()
    }
}
