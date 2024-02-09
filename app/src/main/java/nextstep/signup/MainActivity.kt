package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(32.dp)) {
        Text(
            text = "Welcome to Compose \uD83D\uDE80",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )

        val username = remember { mutableStateOf("") }
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            placeholder = { Text(text = "Username") },
            singleLine = true,
        )

        val email = remember { mutableStateOf("") }
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            placeholder = { Text(text = "Email") },
            singleLine = true,
        )

        val password = remember { mutableStateOf("") }
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            placeholder = { Text(text = "Password") },
            singleLine = true,
        )

        val passwordConfirm = remember { mutableStateOf("") }
        TextField(
            value = passwordConfirm.value,
            onValueChange = { passwordConfirm.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            placeholder = { Text(text = "Password Confirm") },
            singleLine = true,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SignupTheme {
        Greeting()
    }
}
