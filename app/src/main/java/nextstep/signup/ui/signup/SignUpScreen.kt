package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
) {
    val state = SignUpState(InputValidator())

    SignUpScreen(
        modifier = modifier,
        state = state,
        onAction = { action ->
            when (action) {
                is SignUpAction.OnEmailChange -> {
                    state.updateEmail(action.value)
                }

                is SignUpAction.OnPasswordChange -> {
                    state.updatePassword(action.value)
                }

                is SignUpAction.OnPasswordConfirmChange -> {
                    state.updatePasswordConfirm(action.value)
                }

                SignUpAction.OnSignUpClick -> {

                }

                is SignUpAction.OnUsernameChange -> {
                    state.updateUserName(action.value)
                }
            }
        }
    )
}

@Composable
private fun SignUpScreen(
    state: SignUpState,
    onAction: (SignUpAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold { paddingValues ->
        val focusManager = LocalFocusManager.current

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text(
                    modifier = Modifier.padding(top = 48.dp, bottom = 6.dp),
                    text = stringResource(R.string.sign_up_welcome),
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            item {
                // 입력 폼
                UsernameTextField(
                    username = state.userName,
                    isUsernameValid = state.userNameValidation.isValidUsername,
                    isUsernameLengthValid = state.userNameValidation.isInLength,
                    isUsernameHasNumberOrSpecialCharacter = state.userNameValidation.hasNumber || state.userNameValidation.hasSpecialCharacter,
                    onUsernameChange = {
                        onAction(SignUpAction.OnUsernameChange(it))
                    },
                )
            }

            item {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.email,
                    onValueChange = {
                        onAction(SignUpAction.OnEmailChange(it))
                    },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    label = {
                        Text(stringResource(R.string.email_label))
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    isError = !state.emailValidation,
                    supportingText = {
                        if (!state.emailValidation) {
                            Text(stringResource(R.string.email_error_format))
                        }
                    }
                )
            }

            item {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.password,
                    onValueChange = {
                        onAction(SignUpAction.OnPasswordChange(it))
                    },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    label = {
                        Text(stringResource(R.string.password_label))
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    isError = !state.passwordValidation.isValidPassword,
                    supportingText = {
                        if (!state.passwordValidation.isInLength) {
                            Text(stringResource(R.string.password_error_length))
                        } else if (!state.passwordValidation.hasNumber || !state.passwordValidation.hasCharacter) {
                            Text(stringResource(R.string.password_error_number_or_character))
                        }
                    }
                )
            }

            item {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.passwordConfirm,
                    onValueChange = {
                        onAction(SignUpAction.OnPasswordConfirmChange(it))
                    },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    label = {
                        Text(stringResource(R.string.password_confirm_label))
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    isError = !state.isPasswordConfirmValid,
                    supportingText = {
                        if (!state.isPasswordConfirmValid) {
                            Text(stringResource(R.string.password_confirm_error))
                        }
                    }
                )
            }

            item {
                // 회원가입 버튼
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    onClick = {
                        onAction(SignUpAction.OnSignUpClick)
                    }
                ) {
                    Text(
                        style = MaterialTheme.typography.bodyLarge,
                        text = stringResource(R.string.sign_up_sign),
                        modifier = Modifier.padding(vertical = 8.dp),
                    )
                }
            }
        }
    }
}

@Composable
internal fun UsernameTextField(
    username: String,
    isUsernameValid: Boolean,
    isUsernameLengthValid: Boolean,
    isUsernameHasNumberOrSpecialCharacter: Boolean,
    onUsernameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = username,
        onValueChange = {
            onUsernameChange(it)
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            Text(stringResource(R.string.username_label))
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        isError = !isUsernameValid,
        supportingText = {
            if (!isUsernameLengthValid) {
                Text(stringResource(R.string.username_error_length))
            } else if (isUsernameHasNumberOrSpecialCharacter) {
                Text(stringResource(R.string.username_error_number_or_special_character))
            }
        }
    )
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen(
            state = SignUpState(InputValidator()),
            onAction = {},
        )
    }
}
