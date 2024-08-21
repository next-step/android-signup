package nextstep.signup.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.component.SignUpButton
import nextstep.signup.ui.component.SignUpTextField
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.ui.theme.keyboardEmailNextType
import nextstep.signup.ui.theme.keyboardPasswordNextType
import nextstep.signup.ui.theme.keyboardPasswordSendType

@Preview(showBackground = true)
@Composable
fun SignUpScreen() {
    SignupTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(Modifier.height(112.dp))
            SignUpTitle()
            Spacer(Modifier.height(36.dp))
            SignUpTextField(label = "Username")
            Spacer(Modifier.height(36.dp))
            SignUpTextField(label = "Email", keyboardOptions = keyboardEmailNextType)
            Spacer(Modifier.height(36.dp))
            SignUpTextField(label = "Password", keyboardOptions = keyboardPasswordNextType)
            Spacer(Modifier.height(36.dp))
            SignUpTextField("Password Confirm", keyboardOptions = keyboardPasswordSendType)
            Spacer(Modifier.height(36.dp))
            SignUpButton("Sign Up")
        }
    }
}

@Composable
private fun SignUpTitle() {
    Text(style = MaterialTheme.typography.titleLarge, text = "Welcome to Compose \uD83D\uDE80")
}
