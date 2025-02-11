package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.signup.SignUpButton
import nextstep.signup.signup.SignUpScreen
import nextstep.signup.signup.SignUpTextField
import nextstep.signup.signup.SignUpTitle
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                SignUpScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpTitlePreview() {
    SignUpTitle()
}

@Preview(showBackground = true)
@Composable
private fun SignUpTextFieldPreview() {
    var previewText by remember { mutableStateOf("") }
    val onPreviewTextChanged: (String) -> Unit = { previewText = it }

    SignUpTextField(
        text = previewText,
        type = SignUpTextFieldType.USERNAME,
        onTextChanged = onPreviewTextChanged,
        errorMessage = ""
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpButtonPreview() {
    SignUpButton()
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}

enum class SignUpTextFieldType(val hint: String) {
    USERNAME("Username"),
    EMAIL("Email"),
    PASSWORD("Password"),
    PASSWORD_CONFIRM("Password Confirm")
}