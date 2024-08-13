package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Pink80
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                ScreenLayoutSetting()
            }
        }
    }
}

@Composable
private fun TitleTextComponent() {
    Text(
        text = stringResource(id = R.string.signup_title),
        style = TextStyle(
            fontSize = 26.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(700),
            color = Color.Black,
            letterSpacing = 0.26.sp
        ),
        modifier = Modifier
            .fillMaxWidth(1.0F)
            .padding(bottom = 42.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun TextFieldComponent(
    label: Int,
    input: MutableState<String>,
    inputEntered: MutableState<Boolean>
) {
    Row(modifier = Modifier.padding(bottom = 32.dp)) {
        val focusManager = LocalFocusManager.current

        TextField(
            value = input.value,
            onValueChange = {
                input.value = it
            },
            label = {
                Text(text = stringResource(id = label))
            },
            modifier = Modifier
                .alignByBaseline()
                .weight(1.0F),
            singleLine = true,
            visualTransformation = if (label == R.string.signup_password || label == R.string.signup_password_confirm) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = when (label) {
                    R.string.signup_username -> KeyboardType.Text
                    R.string.signup_email -> KeyboardType.Email
                    R.string.signup_password,
                    R.string.signup_password_confirm -> KeyboardType.Password

                    else -> KeyboardType.Text
                }
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    inputEntered.value = true
                    if (label == R.string.signup_password_confirm) focusManager.clearFocus()
                    else focusManager.moveFocus(FocusDirection.Next)
                }
            )
        )
    }
}

@Composable
private fun SignupButtonComponent() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth(1.0F),
        colors = ButtonColors(
            containerColor = Color(Pink80.value),
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(id = R.string.signup_button),
            modifier = Modifier
                .padding(vertical = 15.dp),
            color = Color.White,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                letterSpacing = 0.1.sp
            )
        )
    }
}

@Composable
private fun ScreenLayoutSetting() {
    val name = remember {
        mutableStateOf("")
    }
    val nameEntered = remember {
        mutableStateOf(false)
    }
    val email = remember {
        mutableStateOf("")
    }
    val emailEntered = remember {
        mutableStateOf(false)
    }
    val password = remember {
        mutableStateOf("")
    }
    val passwordEntered = remember {
        mutableStateOf(false)
    }
    val passwordConfirm = remember {
        mutableStateOf("")
    }
    val passwordConfirmEntered = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TitleTextComponent()
            TextFieldComponent(
                label = R.string.signup_username,
                input = name,
                inputEntered = nameEntered
            )
            TextFieldComponent(
                label = R.string.signup_email,
                input = email,
                inputEntered = emailEntered
            )
            TextFieldComponent(
                label = R.string.signup_password,
                input = password,
                inputEntered = passwordEntered
            )
            TextFieldComponent(
                label = R.string.signup_password_confirm,
                input = passwordConfirm,
                inputEntered = passwordConfirmEntered
            )
            SignupButtonComponent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldSettingPreview() {
    SignupTheme {
        ScreenLayoutSetting()
    }
}