package nextstep.signup

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

        InputFormContent()

        Spacer(modifier = Modifier.height(42.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue50,
            )
        ) {
            Text(stringResource(R.string.btn_sign_up))
        }
    }
}

@Composable
private fun InputFormContent(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var userNameErrorMessage by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var emailErrorMessage by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordErrorMessage by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }
    var passwordConfirmErrorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = modifier,
    ) {
        SignUpInputForm(
            input = username,
            label = stringResource(R.string.sign_up_user_name_label),
            onInputChange = { value ->
                username = value
                val signUpValidType = SignUpValidUsername().invoke(value)
                userNameErrorMessage = if (signUpValidType == null) ""
                else context.getString(signUpValidType.resId)
            },
            errorMessage = userNameErrorMessage,
        )
        SignUpInputForm(
            input = email,
            label = stringResource(R.string.sign_up_email_label),
            onInputChange = { value ->
                email = value
                val signUpValidType = SignUpValidEmail().invoke(value)
                emailErrorMessage = if (signUpValidType == null) ""
                else context.getString(signUpValidType.resId)
            },
            errorMessage = emailErrorMessage,
        )

        SignUpInputForm(
            input = password,
            label = stringResource(R.string.sign_up_password_label),
            onInputChange = { value ->
                password = value
                val signUpValidType = SignUpValidPassword().invoke(value)
                passwordErrorMessage = if (signUpValidType == null) ""
                else context.getString(signUpValidType.resId)
            },
            errorMessage = passwordErrorMessage,
            visualTransformation = PasswordVisualTransformation(),
        )
        SignUpInputForm(
            input = passwordConfirm,
            label = stringResource(R.string.sign_up_password_confirm_label),
            onInputChange = { value ->
                passwordConfirm = value
                val signUpValidType = SignUpValidPasswordConfirm(password).invoke(value)
                passwordConfirmErrorMessage = if (signUpValidType == null) ""
                else context.getString(signUpValidType.resId)
            },
            errorMessage = passwordConfirmErrorMessage,
            visualTransformation = PasswordVisualTransformation(),
        )
    }
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
