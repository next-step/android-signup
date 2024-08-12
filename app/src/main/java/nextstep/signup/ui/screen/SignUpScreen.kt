package nextstep.signup.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.component.InputView

@Composable
fun SignUpScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(
                text = "Welcome to Compose \uD83D\uDE80",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color(0xFF000000)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            InputView(hint = "UserName") {
                
            }
            Spacer(modifier = Modifier.padding(8.dp))
            InputView(hint = "Email") {

            }
            Spacer(modifier = Modifier.padding(8.dp))
            InputView(hint = "Password") {

            }
            Spacer(modifier = Modifier.padding(8.dp))
            InputView(hint = "Password Confirm") {

            }
        }
    }
}

@Preview
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen()
}