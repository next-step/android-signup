package nextstep.signup.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.InputValidator.isValid
import nextstep.signup.R
import nextstep.signup.TextFieldState
import nextstep.signup.TextFieldType
import nextstep.signup.setMessage
import nextstep.signup.view.ui.theme.Dimens.EndPadding
import nextstep.signup.view.ui.theme.Dimens.LargePadding
import nextstep.signup.view.ui.theme.Dimens.StartPadding
import nextstep.signup.view.ui.theme.Dimens.TopPadding
import nextstep.signup.view.ui.theme.Red50
import nextstep.signup.view.ui.theme.SignupTheme

class SignUpScreen {
    @Composable
    fun Screen() {
        var password by remember { mutableStateOf("") }
        var passwordConfirm by remember { mutableStateOf("") }
        var isMatchPassword by remember { mutableStateOf(true) }

        SignupTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = TopPadding,
                            start = StartPadding,
                            end = EndPadding
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SignUpTitle()
                    Spacer(modifier = Modifier.height(LargePadding))
                    UsernameTextField()
                    Spacer(modifier = Modifier.height(LargePadding))
                    EmailTextField()
                    Spacer(modifier = Modifier.height(LargePadding))
                    PasswordTextField(
                        onTextChange = {
                            password = it
                            isMatchPassword = password == passwordConfirm
                        }
                    )
                    Spacer(modifier = Modifier.height(LargePadding))
                    PasswordConfirmTextField(
                        onTextChange = {
                            passwordConfirm = it
                            isMatchPassword = password == passwordConfirm
                        })
                    if (isMatchPassword.not()) {
                        SignUpHelperText(stringResource(id = R.string.password_do_not_match))
                    }
                    Spacer(modifier = Modifier.height(LargePadding))
                    SignUpButton()
                }
            }
        }
    }

    @Composable
    fun SignUpTextField(
        hint: String,
        type: TextFieldType,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        onTextChange: (String) -> Unit = {}
    ) {
        var textState by remember { mutableStateOf("") }
        var validationState: TextFieldState by remember { mutableStateOf(TextFieldState.Default) }

        val onValueChange = { value: String ->
            textState = value
            validationState = isValid(value, type)
            onTextChange(value)
        }

        Column {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = textState,
                onValueChange = onValueChange,
                label = {
                    Text(text = hint)
                },
                visualTransformation = visualTransformation,
                colors = TextFieldDefaults.colors(
                    focusedLabelColor =
                    if (validationState == TextFieldState.Valid || validationState == TextFieldState.Default) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Red50
                    }
                ),
                supportingText = {
                    SignUpHelperText(
                        setMessage(validationState, type)
                    )
                },
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun SignUpTextFieldPreview() {
        SignupTheme {
            SignUpTextField(stringResource(id = R.string.username), TextFieldType.Username)
        }
    }
}