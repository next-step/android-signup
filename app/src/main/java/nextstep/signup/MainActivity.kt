package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGray20
import nextstep.signup.ui.theme.Gray
import nextstep.signup.ui.theme.RobotoBold
import nextstep.signup.ui.theme.RobotoRegular
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
                    SignUpScreen()
                }
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 20.dp),
    ) {
        val (userName, setUserName) = remember { mutableStateOf("") }
        val (email, setEmail) = remember { mutableStateOf("") }
        val (password, setPassword) = remember { mutableStateOf("") }
        val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = stringResource(R.string.signup_header_title),
            color = Color.Black,
            fontSize = 26.sp,
            fontFamily = RobotoBold,
        )
        Spacer(modifier = Modifier.height(42.dp))
        SignUpTextField(
            label = stringResource(R.string.signup_user_name),
            onTextChanged = { setUserName(it) },
            text = userName,
        )
        Spacer(modifier = Modifier.height(36.dp))
        SignUpTextField(
            label = stringResource(R.string.signup_email),
            onTextChanged = { setEmail(it) },
            text = email,
        )
        Spacer(modifier = Modifier.height(36.dp))
        SignUpTextField(
            label = stringResource(R.string.signup_password),
            onTextChanged = { setPassword(it) },
            text = password,
        )
        Spacer(modifier = Modifier.height(36.dp))
        SignUpTextField(
            label = stringResource(R.string.signup_password_confirm),
            onTextChanged = { setPasswordConfirm(it) },
            text = passwordConfirm,
        )
    }
}

@Composable
fun SignUpTextField(
    label: String,
    onTextChanged: (String) -> Unit,
    text: String,
) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp,
            fontFamily = RobotoRegular,
        ),
        label = {
            Text(
                text = label,
                fontFamily = RobotoRegular,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedLabelColor = Blue50,
            unfocusedLabelColor = Gray,
            unfocusedContainerColor = BlueGray20,
            focusedContainerColor = BlueGray20,
            focusedIndicatorColor = Blue50,
        ),
        modifier = Modifier.background(
            color = Color.Transparent,
            shape = RoundedCornerShape(
                topStart = 4.dp,
                topEnd = 4.dp,
            ),
        )
    )
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}