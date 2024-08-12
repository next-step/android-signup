package nextstep.signup.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun SingUpView() {
    val password = mutableStateOf("")
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
                NameTextFieldView(
                    "Username"
                )
                EmailTextFieldView(
                    "Email"
                )
                PasswordTextFieldView(
                    "Password",
                    inputState = password
                )
                PasswordConfirmTextFieldView(
                    "Password Confirm",
                    anotherInputState = password
                )
                SingUpButtonView(
                    Modifier.padding(16.dp)
                )
            }
        },
        modifier = Modifier.padding(horizontal = 33.dp)
    )
}