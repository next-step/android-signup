package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.signup.ui.SignUpTextFieldComponent
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.validation.InputValidation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SignupTheme(dynamicColor = false) {
                SignUpScreen()
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    var inputPassword = ""

    Scaffold(
        topBar = {
            Text(
                text = "Welcome to Compose 🚀",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(16.dp)
            ) {
                SignUpTextFieldComponent(
                    "Username",
                    {
                        InputValidation.UserNameValidation(
                            "이름에는 숫자나 기호가 포함될 수 없습니다.",
                            "이름은 2~5자여야 합니다."
                        ).checkValidation(it)
                    }
                )
                SignUpTextFieldComponent(
                    "Email",
                    {
                        InputValidation.EmailValidation(
                            "이메일 형식이 올바르지 않습니다."
                        ).checkValidation(it)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                SignUpTextFieldComponent(
                    "Password",
                    {
                        inputPassword = it
                        InputValidation.PasswordValidation(
                            "비밀번호는 영문과 숫자를 포함해야 합니다.",
                            "비밀번호는 8~16자여야 합니다."
                        ).checkValidation(it)
                    },
                    PasswordVisualTransformation()
                )
                SignUpTextFieldComponent(
                    "Password Confirm",
                    {
                        InputValidation.PasswordConfirmValidation(
                            inputPassword,
                            "비밀번호가 일치하지 않습니다."
                        ).checkValidation(it)
                    },
                    PasswordVisualTransformation()
                )
                ButtonSignUpComponent { /* Handle Sign Up */ }
            }
        }
    }
}

@Composable
private fun TextFieldComponent(
    labelText: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    var inputText by remember { mutableStateOf("") }

    TextField(
        value = inputText,
        onValueChange = { inputText = it },
        label = { Text(labelText) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions
    )
}

@Composable
private fun ButtonSignUpComponent(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        shape = RoundedCornerShape(50)
    ) {
        Text("Sign Up")
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}
