package nextstep.signup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.White

@Composable
fun SignupScreen() {
    val title = stringResource(R.string.signup_title)
    val usernameLabel = stringResource(R.string.signup_label_username)
    val emailLabel = stringResource(R.string.signup_label_email)
    val passwordLabel = stringResource(R.string.signup_label_password)
    val passwordConfirmLabel = stringResource(R.string.signup_label_password_confirm)
    val signUpButton = stringResource(R.string.signup_button)

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(horizontal = 33.dp),
            verticalArrangement = Arrangement.spacedBy(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Title
            SignupTitle(
                title = title,
                modifier = Modifier.padding(top = 60.dp)
            )

            // Username
            SignupTextField(
                label = usernameLabel,
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.padding(top = 6.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            // Email
            SignupTextField(
                label = emailLabel,
                value = email,
                onValueChange = { email = it },
                modifier = Modifier,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            // Password
            SignupTextField(
                label = passwordLabel,
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                )
            )

            // Password Confirm
            SignupTextField(
                label = passwordConfirmLabel,
                value = passwordConfirm,
                onValueChange = { passwordConfirm = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            // Signup Button
            SignupButton(
                text = signUpButton,
                onClick = {
                    // Signup Action
                },
                modifier = Modifier.padding(top = 6.dp),
            )
        }
    }
}

@Composable
fun SignupTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        fontSize = 26.sp,
        fontWeight = FontWeight.W700,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignupScreen()
}

@Preview
@Composable
fun SignupTitlePreview() {
    SignupTitle(title = "테스트")
}