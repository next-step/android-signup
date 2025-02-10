package nextstep.signup

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.signupvalidation.SignUpInvalidType
import nextstep.signup.signupvalidation.SignUpValidEmail
import nextstep.signup.signupvalidation.SignUpValidPassword
import nextstep.signup.signupvalidation.SignUpValidPasswordConfirm
import nextstep.signup.signupvalidation.SignUpValidUsername
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.roboto

@Composable
fun SignUpScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {
    var state by remember { mutableStateOf(SignUpState()) }
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = stringResource(R.string.sign_up_title),
            fontSize = 26.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        InputFormContent(
            state = state,
            onUsernameChange = { value ->
                state = state.copy(
                    username = value, userNameErrorMessage = getErrorMessage(
                        context = context,
                        input = value,
                        validSignUp = SignUpValidUsername()
                    )
                )
            },
            onEmailChange = { value ->
                state = state.copy(
                    email = value,
                    emailErrorMessage = getErrorMessage(
                        context = context,
                        input = value,
                        validSignUp = SignUpValidEmail(),
                    )
                )
            },
            onPasswordChange = { value ->
                state = state.copy(
                    password = value,
                    passwordConfirmErrorMessage = getErrorMessage(
                        context = context,
                        input = value,
                        validSignUp = SignUpValidPasswordConfirm(state.passwordConfirm)
                    ),
                    passwordErrorMessage = getErrorMessage(
                        context = context,
                        input = value,
                        validSignUp = SignUpValidPassword()
                    )
                )
            },
            onPasswordConfirmChange = { value ->
                state = state.copy(
                    passwordConfirm = value,
                    passwordConfirmErrorMessage = getErrorMessage(
                        context = context,
                        input = value,
                        validSignUp = SignUpValidPasswordConfirm(state.password)
                    )
                )
            },
        )

        Spacer(modifier = Modifier.height(42.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = state.isSignUpEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue50,
            ),
        ) {
            Text(stringResource(R.string.btn_sign_up))
        }
    }
}

@Composable
private fun InputFormContent(
    state: SignUpState,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        SignUpInputForm(
            input = state.username,
            label = stringResource(R.string.sign_up_user_name_label),
            errorMessage = state.userNameErrorMessage,
            onInputChange = onUsernameChange,
        )
        SignUpInputForm(
            input = state.email,
            label = stringResource(R.string.sign_up_email_label),
            errorMessage = state.emailErrorMessage,
            onInputChange = onEmailChange
        )
        SignUpInputForm(
            input = state.password,
            label = stringResource(R.string.sign_up_password_label),
            onInputChange = onPasswordChange,
            errorMessage = state.passwordErrorMessage,
            visualTransformation = PasswordVisualTransformation(),
        )
        SignUpInputForm(
            input = state.passwordConfirm,
            label = stringResource(R.string.sign_up_password_confirm_label),
            onInputChange = onPasswordConfirmChange,
            errorMessage = state.passwordConfirmErrorMessage,
            visualTransformation = PasswordVisualTransformation(),
        )
    }
}

private fun getErrorMessage(
    context: Context,
    input: String,
    validSignUp: (String) -> SignUpInvalidType?,
): String {
    val signUpValidType = validSignUp.invoke(input) ?: return ""
    return context.getString(signUpValidType.resId)
}

@Composable
private fun SignUpInputForm(
    input: String,
    label: String,
    onInputChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = input,
        onValueChange = onInputChange,
        label = { Text(label) },
        supportingText = {
            Text(
                errorMessage,
                modifier = Modifier.semantics { contentDescription = "SignUpInputFormErrorText" })
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BlueGrey20,
            focusedContainerColor = BlueGrey20,
            focusedIndicatorColor = Blue50,
            focusedLabelColor = Blue50
        ),
        isError = errorMessage.isNotBlank(),
        visualTransformation = visualTransformation,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    MaterialTheme {
        SignUpScreen(PaddingValues())
    }
}

@Preview
@Composable
private fun SignUpInputFormPreview() {
    MaterialTheme {
        SignUpInputForm(label = "Username", input = "", errorMessage = "", onInputChange = {})
    }
}
