package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.component.SubmitButton
import nextstep.signup.ui.component.SignupForm
import nextstep.signup.ui.component.SignupTitle
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                SignupLayout()
            }
        }
    }
}

@Composable
fun SignupLayout() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 48.dp)
        ) {
            var username by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var passwordConfirm by remember { mutableStateOf("") }

            SignupTitle()
            EmptySpace()
            SignupForm(
                label = stringResource(R.string.signup_field_label_username),
                inputValue = username,
                onInputChange = { username = it },
            )
            EmptySpace()
            SignupForm(
                label = stringResource(R.string.signup_field_label_email),
                inputValue = email,
                onInputChange = { email = it },
                inputType = KeyboardType.Email,
            )
            EmptySpace()
            SignupForm(
                label = stringResource(R.string.signup_field_label_password),
                inputValue = password,
                onInputChange = { password = it },
                inputType = KeyboardType.Password,
            )
            EmptySpace()
            SignupForm(
                label = stringResource(R.string.signup_field_label_password_confirm),
                inputValue = passwordConfirm,
                onInputChange = { passwordConfirm = it },
                inputType = KeyboardType.Password,
            )
            EmptySpace()
            SubmitButton()
        }
    }
}

@Composable
fun EmptySpace(modifier: Modifier = Modifier) {
    Box(modifier = modifier.height(24.dp))
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
)
@Composable
fun SignupLayoutPreview() {
    SignupLayout()
}
