package nextstep.signup.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.component.InputTextField
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.UserNameTextField
import nextstep.signup.ui.theme.Blue50


@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp, 60.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordConfirm by remember { mutableStateOf("") }

        WelcomeTitle()
        UserNameTextField(
            username = username,
            onNameChange = { username = it },
        )
        EmailTextField(
            email = email,
            onEmailChange = { email = it },
        )
        InputTextField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.sign_up_label_password),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        InputTextField(
            value = passwordConfirm,
            onValueChange = { passwordConfirm = it },
            label = stringResource(R.string.sign_up_label_password_confirm),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        SignUpButton {}
    }
}

@Composable
private fun WelcomeTitle() {
    Text(
        text = stringResource(id = R.string.sign_up_title),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 26.sp,
        color = Color.Black,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun SignUpButton(onClicked: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(top = 6.dp),
        onClick = onClicked,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue50
        )
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_button),
            fontSize = 14.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}
