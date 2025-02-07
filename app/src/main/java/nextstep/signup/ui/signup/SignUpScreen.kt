package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreenRoot(modifier: Modifier = Modifier) {
    var state by remember {
        mutableStateOf(SignUpState())
    }

    SignUpScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is SignUpAction.OnEmailChanged -> {
                    state = state.copy(
                        email = state.email.copy(
                            value = action.value
                        ),
                    )
                }

                is SignUpAction.OnPasswordChanged -> {
                    state = state.copy(
                        password = state.password.copy(
                            value = action.value
                        ),
                    )
                }

                is SignUpAction.OnPasswordConfirmChanged -> {
                    state = state.copy(
                        passwordConfirm = state.passwordConfirm.copy(
                            value = action.value
                        ),
                    )
                }

                SignUpAction.OnSignUpClick -> {}
                is SignUpAction.OnUsernameChanged -> {
                    state = state.copy(
                        userName = state.userName.copy(
                            value = action.value
                        ),
                    )
                }

                SignUpAction.None -> {}
            }
        }
    )
}

@Composable
fun SignUpScreen(
    state: SignUpState,
    onAction: (SignUpAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            for (inputState in state.getInputStateList()) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = inputState.value,
                    label = {
                        if (inputState.label != null) {
                            Text(stringResource(inputState.label))
                        }
                    },
                    visualTransformation = inputState.visualTransformation,
                    onValueChange = {
                        onAction(inputState.onValueChange(it))
                    }
                )
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
