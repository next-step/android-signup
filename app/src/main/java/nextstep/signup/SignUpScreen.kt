package nextstep.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.components.SignUpButton
import nextstep.signup.components.SignUpTextField
import nextstep.signup.components.SignUpTitle
import nextstep.signup.domain.Email
import nextstep.signup.domain.Password
import nextstep.signup.domain.PasswordConfirm
import nextstep.signup.domain.Username
import nextstep.signup.mapper.toUiState
import nextstep.signup.state.InputFieldState
import nextstep.signup.ui.theme.SignupTheme

@Composable
internal fun SignUpScreen(
    inputFields: Map<String, InputFieldState>,
    inputFieldChangeListeners: Map<String, InputFieldChangeListener>,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(60.dp))
        SignUpTitle(stringResource(R.string.sign_up_title))
        Spacer(Modifier.height(42.dp))
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            inputFields.forEach { (key, state) ->
                SignUpTextField(
                    value = state.input,
                    onValueChange = { inputFieldChangeListeners[key]?.onChanged(it) },
                    label = stringResource(state.label),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = state.keyboardType,
                    needHide = state.needHide,
                    isError = state.isError,
                    supportingText = state.supportingText,
                )
            }
        }
        Spacer(Modifier.height(22.dp))
        SignUpButton(
            text = stringResource(R.string.sign_up_sign_up_button_text),
            onClick = onSignUpButtonClick,
            enabled = inputFields.values.all { it.isError.not() && it.input.isNotEmpty() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        var inputFields: Map<String, InputFieldState> by remember {
            mutableStateOf(
                mapOf(
                    "username" to Username("김컴포즈").toUiState(),
                    "email" to Email("compose12@gmail.com").toUiState(),
                    "password" to Password("asdf1234").toUiState(),
                    "passwordConfirm" to PasswordConfirm("asdf1234", "asdf1234").toUiState()
                )
            )
        }
        val inputFieldChangeListeners: Map<String, InputFieldChangeListener> by remember {
            mutableStateOf(
                mapOf(
                    "username" to InputFieldChangeListener { username: String ->
                        inputFields = inputFields.toMutableMap().apply {
                            this["username"] = Username(username).toUiState()
                        }
                    },
                    "email" to InputFieldChangeListener { it: String ->
                        inputFields = inputFields.toMutableMap().apply {
                            this["email"] = Email(it).toUiState()
                        }
                    },
                    "password" to InputFieldChangeListener { it: String ->
                        inputFields = inputFields.toMutableMap().apply {
                            this["password"] = Password(it).toUiState()
                            this["passwordConfirm"] = PasswordConfirm(
                                it,
                                this["passwordConfirm"]?.input ?: ""
                            ).toUiState()
                        }
                    },
                    "passwordConfirm" to InputFieldChangeListener { it: String ->
                        inputFields = inputFields.toMutableMap().apply {
                            this["passwordConfirm"] =
                                PasswordConfirm(this["password"]?.input ?: "", it).toUiState()
                        }
                    },
                )
            )
        }

        SignUpScreen(
            inputFields = inputFields,
            inputFieldChangeListeners = inputFieldChangeListeners,
            onSignUpButtonClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
