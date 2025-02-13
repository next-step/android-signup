package nextstep.signup

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import nextstep.signup.ui.screen.signup.SignupScreen
import nextstep.signup.ui.theme.SignupTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                Scaffold (
                    modifier = Modifier.fillMaxSize().background(color = Color.White),
                ) { _ ->
                    val username = remember { mutableStateOf("") }
                    val email = remember { mutableStateOf("") }
                    val password = remember { mutableStateOf("") }
                    val passwordConfirm = remember { mutableStateOf("") }

                    SignupScreen(
                        modifier = Modifier.fillMaxSize(),
                        username = username.value,
                        email = email.value,
                        password = password.value,
                        passwordConfirm = passwordConfirm.value,
                        onUsernameChange = {
                            username.value = it
                        },
                        onEmailChange = {
                            email.value = it
                        },
                        onPasswordChange = {
                            password.value = it
                        },
                        onPasswordConfirmChange = {
                            passwordConfirm.value = it
                        }
                    )
                }
            }
        }
    }
}
