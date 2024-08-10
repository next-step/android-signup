package nextstep.signup.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SingUpView() {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 62.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TitleView("Welcome to Compose \uD83D\uDE80")
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextFieldView(
                    "Username",
                    KeyboardType.Text
                )
                TextFieldView(
                    "Email",
                    KeyboardType.Email
                )
                TextFieldView(
                    "Password",
                    KeyboardType.Password,
                    PasswordVisualTransformation()
                )
                TextFieldView(
                    "Password Confirm",
                    KeyboardType.Password,
                    PasswordVisualTransformation()
                )
                SingUpButtonView(
                    Modifier.padding(16.dp)
                )
            }
        },
        modifier = Modifier.padding(horizontal = 33.dp)
    )
}