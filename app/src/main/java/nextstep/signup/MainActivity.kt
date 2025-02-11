package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import nextstep.signup.domain.Email
import nextstep.signup.domain.Password
import nextstep.signup.domain.PasswordConfirm
import nextstep.signup.domain.Username
import nextstep.signup.mapper.toUiState
import nextstep.signup.state.InputFieldState
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var inputFields: Map<String, InputFieldState> by remember {
                mutableStateOf(
                    mapOf(
                        INPUT_FIELD_KEY_USERNAME to Username().toUiState(),
                        INPUT_FIELD_KEY_EMAIL to Email().toUiState(),
                        INPUT_FIELD_KEY_PASSWORD to Password().toUiState(),
                        INPUT_FIELD_KEY_PASSWORD_CONFIRM to PasswordConfirm().toUiState()
                    )
                )
            }
            val inputFieldChangeListeners: Map<String, InputFieldChangeListener> by remember {
                mutableStateOf(
                    mapOf(
                        INPUT_FIELD_KEY_USERNAME to InputFieldChangeListener { username: String ->
                            inputFields = inputFields.toMutableMap().apply {
                                this[INPUT_FIELD_KEY_USERNAME] = Username(username).toUiState()
                            }
                        },
                        INPUT_FIELD_KEY_EMAIL to InputFieldChangeListener { it: String ->
                            inputFields = inputFields.toMutableMap().apply {
                                this[INPUT_FIELD_KEY_EMAIL] = Email(it).toUiState()
                            }
                        },
                        INPUT_FIELD_KEY_PASSWORD to InputFieldChangeListener { it: String ->
                            inputFields = inputFields.toMutableMap().apply {
                                this[INPUT_FIELD_KEY_PASSWORD] = Password(it).toUiState()
                                this[INPUT_FIELD_KEY_PASSWORD_CONFIRM] = PasswordConfirm(
                                    it,
                                    this[INPUT_FIELD_KEY_PASSWORD_CONFIRM]?.input ?: ""
                                ).toUiState()
                            }
                        },
                        INPUT_FIELD_KEY_PASSWORD_CONFIRM to InputFieldChangeListener { it: String ->
                            inputFields = inputFields.toMutableMap().apply {
                                this[INPUT_FIELD_KEY_PASSWORD_CONFIRM] =
                                    PasswordConfirm(this[INPUT_FIELD_KEY_PASSWORD]?.input ?: "", it).toUiState()
                            }
                        },
                    )
                )
            }

            SignupTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                ) { paddingValues ->
                    SignUpScreen(
                        inputFields = inputFields,
                        inputFieldChangeListeners = inputFieldChangeListeners,
                        onSignUpButtonClick = {},
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    )
                }
            }
        }
    }

    companion object {
        private const val INPUT_FIELD_KEY_USERNAME = "username"
        private const val INPUT_FIELD_KEY_EMAIL = "email"
        private const val INPUT_FIELD_KEY_PASSWORD = "password"
        private const val INPUT_FIELD_KEY_PASSWORD_CONFIRM = "passwordConfirm"
    }
}
