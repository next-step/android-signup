package nextstep.signup.ui.screen

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.component.SignupButton
import nextstep.signup.ui.component.SignupTextField
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignupScreen(
    username: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                top = 60.dp,
                start = 32.dp,
                end = 32.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleContent()
        Spacer(modifier = Modifier.height(42.dp))
        TextFieldsContent(
            username = username,
            email = email,
            password = password,
            passwordConfirm = passwordConfirm,
            onUsernameChange = onUsernameChange,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onPasswordConfirmChange = onPasswordConfirmChange
        )
        Spacer(modifier = Modifier.height(39.dp))
        ButtonContent()
    }
}

@Composable
private fun TitleContent() {
    Text(
        text = stringResource(R.string.welcome_to_compose),
        fontWeight = FontWeight.W700,
        fontSize = 26.sp,
        lineHeight = 20.sp,
    )
}

@Composable
private fun TextFieldsContent(
    username: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(33.dp)
    ) {
        SignupTextField(
            value = username,
            onValueChange = onUsernameChange,
            label = stringResource(R.string.username),
            modifier = Modifier.fillMaxWidth()
        )
        SignupTextField(
            value = email,
            onValueChange = onEmailChange,
            label = stringResource(R.string.email),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth()
        )
        SignupTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = stringResource(R.string.password),
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Password
            ),
            secure = true,
            modifier = Modifier.fillMaxWidth()
        )
        SignupTextField(
            value = passwordConfirm,
            onValueChange = onPasswordConfirmChange,
            label = stringResource(R.string.password_confirm),
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Password
            ),
            secure = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ButtonContent() {
    SignupButton(
        label = stringResource(R.string.signup),
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )
}


@Preview(
    showBackground = true,
    backgroundColor = Color.WHITE.toLong()
)
@Composable
private fun SignupScreenPreview() {
    SignupTheme {
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
