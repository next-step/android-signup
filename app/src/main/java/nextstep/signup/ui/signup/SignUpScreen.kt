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
import androidx.compose.ui.focus.FocusDirection
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
fun SignUpScreen(modifier: Modifier = Modifier) {
    val state = SignUpState()

    SignUpScreen(
        modifier = modifier,
        state = state,
        onAction = { action ->
            state.onAction(action)
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
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.userName,
                    onValueChange = {
                        onAction(SignUpAction.OnUsernameChange(it))
                    },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    label = {
                        Text(stringResource(R.string.username_label))
                    },
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Next)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
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
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Next)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
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
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Next)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
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
                    )
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

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen(
            state = SignUpState(),
            onAction = {},
        )
    }
}
