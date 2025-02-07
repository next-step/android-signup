package nextstep.signup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
                .fillMaxWidth()
                .fillMaxHeight()
                .background(White)
                .padding(
                    horizontal = 33.dp,
                    vertical = 60.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Title
            SignupTitle(title = title)

            // Username
            Spacer(Modifier.size(39.dp))
            SignupTextField(
                label = usernameLabel,
                value = username,
                onValueChange = { username = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            // Email
            Spacer(Modifier.size(33.dp))
            SignupTextField(
                label = emailLabel,
                value = email,
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            // Password
            Spacer(Modifier.size(33.dp))
            SignupTextField(
                label = passwordLabel,
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                )
            )

            // Password Confirm
            Spacer(Modifier.size(39.dp))
            SignupTextField(
                label = passwordConfirmLabel,
                value = passwordConfirm,
                onValueChange = { passwordConfirm = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            // Signup Button
            Spacer(Modifier.size(39.dp))
            SignupButton(
                text = signUpButton,
                onClick = {
                    // Signup Action
                },
            )
        }
    }
}

@Composable
fun SignupTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
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