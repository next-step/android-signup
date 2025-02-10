package nextstep.signup.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey50
import nextstep.signup.ui.theme.RedError
import nextstep.signup.ui.utils.MessageUtils
import nextstep.signup.ui.utils.ValidateUtils

@Composable
fun SignUpScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
) {
    Surface(
        modifier = modifier.background(Color.White),
    ) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()

        var userName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }

        val signUpCompleteMsg = remember { MessageUtils.getSignUpCompleteMessage(context) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TitleText(
                title = stringResource(R.string.sign_up_title),
                modifier = Modifier.padding(top = 60.dp)
            )
            UsernameTextField(
                userName = userName,
                onUsernameChange = {
                    userName = it
                },
                modifier = Modifier.padding(top = 42.dp)
            )
            EmailTextField(
                email = email,
                onEmailChange = {
                    email = it
                },
                modifier = Modifier.padding(top = 32.dp)
            )
            PasswordTextField(
                password = password,
                onPasswordChange = {
                    password = it
                },
                modifier = Modifier.padding(top = 32.dp)
            )
            PasswordConfirmTextField(
                password = password,
                confirmPassword = confirmPassword,
                onPasswordChange = {
                    confirmPassword = it
                },
                modifier = Modifier.padding(top = 32.dp)
            )
            SignUpButton(
                buttonText = stringResource(R.string.sign_up_button),
                isEnabled = ValidateUtils.isValidAll(userName, email, password, confirmPassword),
                modifier = Modifier.padding(top = 42.dp)
            ) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = signUpCompleteMsg,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }
}

@Composable
fun TitleText(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        fontSize = 26.sp,
        fontWeight = FontWeight.W700,
        modifier = modifier
    )
}

@Composable
fun UsernameTextField(
    userName: String,
    onUsernameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val errorMsg = MessageUtils.getUserNameErorrMessage(context, userName)

    InputField(
        label = stringResource(R.string.user_name),
        value = userName,
        errorMsg = errorMsg,
        modifier = modifier,
        onValueChange = onUsernameChange,
    )
}

@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val errorMsg = MessageUtils.getEmailErrorMessage(context, email)

    InputField(
        label = stringResource(R.string.email),
        value = email,
        errorMsg = errorMsg,
        modifier = modifier,
        onValueChange = onEmailChange,
    )
}

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val errorMsg = MessageUtils.getPasswordErrorMessage(context, password)

    InputField(
        label = stringResource(R.string.password),
        value = password,
        errorMsg = errorMsg,
        inputType = KeyboardType.Password,
        onValueChange = onPasswordChange,
        modifier = modifier
    )
}

@Composable
fun PasswordConfirmTextField(
    password: String,
    confirmPassword: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val errorMsg = MessageUtils.getPasswordConfirmErrorMessage(context, password, confirmPassword)

    InputField(
        label = stringResource(R.string.password_confirm),
        value = confirmPassword,
        errorMsg = errorMsg,
        inputType = KeyboardType.Password,
        onValueChange = onPasswordChange,
        modifier = modifier
    )
}

@Composable
fun InputField(
    label: String,
    value: String,
    errorMsg: String,
    inputType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        isError = errorMsg.isNotEmpty(),
        supportingText = {
            Text(
                text = errorMsg,
                color = RedError,
                fontSize = 12.sp,
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (inputType == KeyboardType.Password)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        label = {
            Text(
                text = label,
            )
        },
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Blue50,
            unfocusedContainerColor = BlueGrey50,
            focusedContainerColor = BlueGrey50,
            focusedLabelColor = Blue50,
            errorCursorColor = RedError,
            errorLabelColor = RedError,
        ),
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun SignUpButton(
    buttonText: String,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue50,
        ),
        enabled = isEnabled,
        onClick = onClick,
        contentPadding = PaddingValues(vertical = 15.dp),
    ) {
        Text(
            text = buttonText,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TitlePreView() {
    TitleText(
        title = "회원가입 화면이에용"
    )
}

@Preview(showBackground = true)
@Composable
private fun InputFieldPreView() {
    InputField(
        label = "홀리몰리 입력필드",
        value = "홀리몰리",
        errorMsg = "",
        onValueChange = { }
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpButtonPreView() {
    SignUpButton(
        buttonText = "SingUp",
        isEnabled = true
    )
}
