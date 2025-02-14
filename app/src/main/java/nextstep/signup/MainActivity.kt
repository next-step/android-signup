package nextstep.signup

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUpScreen()
                }
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(vertical = 37.dp))
        Text(
            "Welcome to Compose \uD83D\uDE80",
            fontSize = 26.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(vertical = 14.dp))

        UserInputTextField("Username", 12.dp)
        UserInputTextField("Email", 12.dp)
        UserInputTextField("Password", 12.dp)
        UserInputTextField("Password Confirmation", 13.dp)

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue50,
                contentColor = Color.White
            ),
            onClick = {
                Log.d("SignUp", "SignUp Button Clicked")
            }
        ) {
            Text(text = "Sign Up", fontSize = 14.sp)
        }
    }
}

@Composable
fun UserInputTextField(label: String, bottomPadding: Dp) {
    var text by remember { mutableStateOf("") }

    TextField(
        label = { Text(label) },
        value = text,
        onValueChange = {
            text = it
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = BlueGrey20,
            unfocusedContainerColor = BlueGrey20,
            focusedLabelColor = Blue50,
            unfocusedLabelColor = Color(0xFF49454F),
            focusedIndicatorColor = Blue50,
            focusedTextColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.padding(vertical = bottomPadding))
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignupTheme {
        SignUpScreen()
    }
}