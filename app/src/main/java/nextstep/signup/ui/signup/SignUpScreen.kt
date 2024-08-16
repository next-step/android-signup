package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.SignUpButton
import nextstep.signup.ui.component.UsernameTextField

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onShowSnackbar: (message: String) -> Unit,
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 60.dp),
        )

        UsernameTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        EmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        PasswordTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        PasswordConfirmTextField(
            value = passwordConfirm,
            passwordValue = password,
            onValueChange = { passwordConfirm = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )

        SignUpButton(
            username = username,
            email = email,
            password = password,
            passwordConfirm = passwordConfirm,
            onClick = { onShowSnackbar(context.getString(R.string.success_sign_up)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(onShowSnackbar = {})
}