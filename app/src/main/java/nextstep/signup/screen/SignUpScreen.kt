package nextstep.signup.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.Gray10
import nextstep.signup.ui.theme.Gray40
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 60.dp, bottom = 6.dp),
                text = stringResource(id = R.string.sign_up_title),
                style = MaterialTheme.typography.headlineMedium
            )
            SignUpTextField(
                modifier = Modifier.fillMaxWidth(),
                text = userName,
                onValueChange = { value ->
                    userName = value
                },
                labelText = stringResource(id = R.string.sign_up_user_name_label)
            )
            SignUpTextField(
                modifier = Modifier.fillMaxWidth(),
                text = email,
                onValueChange = { value ->
                    email = value
                },
                labelText = stringResource(id = R.string.sign_up_email_label)
            )
            SignUpTextField(
                modifier = Modifier.fillMaxWidth(),
                text = password,
                onValueChange = { value ->
                    password = value
                },
                labelText = stringResource(id = R.string.sign_up_password_label),
                visualTransformation = PasswordVisualTransformation()
            )
            SignUpTextField(
                modifier = Modifier.fillMaxWidth(),
                text = passwordConfirm,
                onValueChange = { value ->
                    passwordConfirm = value
                },
                labelText = stringResource(id = R.string.sign_up_password_confirm_label),
                visualTransformation = PasswordVisualTransformation()
            )
            TextButton(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Blue50,
                    contentColor = Color.White
                ),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up_button)
                )
            }
        }
    }
}

@Composable
fun SignUpTextField(
    text: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    maxLines : Int = 1,
    colors : TextFieldColors = TextFieldDefaults.colors(
        focusedLabelColor = Blue50,
        focusedIndicatorColor = Blue50,
        unfocusedLabelColor = Gray40,
        cursorColor = Blue50,
        focusedTextColor = Gray10,
        unfocusedTextColor = Gray10
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = onValueChange,
        label = {
            Text(
                text = labelText
            )
        },
        colors = colors,
        maxLines = maxLines,
        singleLine = maxLines == 1,
        visualTransformation = visualTransformation
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpTextFieldPreview() {
    SignupTheme {
        SignUpTextField(
            text = "",
            labelText = "Preview",
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen()
    }
}
