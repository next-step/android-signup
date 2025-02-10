package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.component.EmailTextFiled
import nextstep.signup.component.SignupButton
import nextstep.signup.component.SignupTextField
import nextstep.signup.component.SignupTitle
import nextstep.signup.component.UserNameTextFiled
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.validator.SignupInfoValidator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    SignupScreen(Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
internal fun SignupScreen(
    modifier: Modifier = Modifier,
) {
    val (userName, setUserName) = remember { mutableStateOf("") }
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SignupTitle(
            modifier = Modifier.padding(top = 6.dp),
            text = stringResource(R.string.signup_title)
        )
        UserNameTextFiled(
            modifier = Modifier.padding(top = 20.dp),
            text = userName,
            onValueChange = setUserName,
        )
        EmailTextFiled(
            text = email,
            onValueChange = setEmail,
        )
        SignupTextField(
            label = stringResource(R.string.signup_label_password),
            text = password,
            onValueChange = setPassword,
            visualTransformation = PasswordVisualTransformation(),
            validator = SignupInfoValidator.Password,
        )
        SignupTextField(
            label = stringResource(R.string.signup_label_password_confirm),
            text = passwordConfirm,
            onValueChange = setPasswordConfirm,
            visualTransformation = PasswordVisualTransformation(),
            validator = SignupInfoValidator.PasswordConfirm { password },
        )
        SignupButton(
            modifier = Modifier.padding(top = 6.dp),
            text = stringResource(R.string.signup_button),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupScreenPreview() {
    SignupScreen()
}
