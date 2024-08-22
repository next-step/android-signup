package nextstep.signup.ui.component

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.ErrorTextColor
import nextstep.signup.ui.theme.keyboardEmailNextType
import nextstep.signup.ui.theme.keyboardPasswordNextType
import nextstep.signup.ui.theme.keyboardTextNextType

data class SignUpTextFieldState(
    val label: String = "",
    val text: String = "",
    val textFieldColors: Color = Blue50,
    val supportingText: String = "",
    val supportingTextFieldColors: Color = ErrorTextColor,
)

@Composable
@Preview
fun SignUpTextField(
    fieldState: SignUpTextFieldState = SignUpTextFieldState(label = "Username", supportingText = "Hello"),
    keyboardOptions: KeyboardOptions = keyboardTextNextType,
    onTextChanged: ((String) -> Unit) = { },
) {
    TextField(
        value = fieldState.text,
        modifier = Modifier.width(296.dp),
        colors = TextFieldDefaults.colors(
            focusedLabelColor = fieldState.textFieldColors,
            focusedIndicatorColor = fieldState.textFieldColors,
        ),
        keyboardOptions = keyboardOptions,
        onValueChange = onTextChanged,
        label = {
            Text(
                text = fieldState.label,
            )
        },
        supportingText = {
            Text(
                text = fieldState.supportingText,
                color = fieldState.supportingTextFieldColors
            )
        },
        visualTransformation = if (keyboardOptions.keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Composable
@Preview
fun UsernameTextField(
    username: MutableState<String> = mutableStateOf("Username"),
) {
    SignUpTextField(
        fieldState = getUsernameTextFieldState(username.value),
        onTextChanged = username::value::set
    )
}

@Composable
@Preview
fun EmailTextField(
    email: MutableState<String> = mutableStateOf("email"),
) {
    SignUpTextField(
        fieldState = getEmailTextFieldState(email.value),
        keyboardOptions = keyboardEmailNextType,
        onTextChanged = email::value::set
    )
}

@Composable
@Preview
fun PasswordTextField(
    password: MutableState<String> = mutableStateOf("Password"),
) {
    SignUpTextField(
        fieldState = getPasswordTextFieldState(password.value),
        keyboardOptions = keyboardPasswordNextType,
        onTextChanged = password::value::set
    )
}

@Composable
@Preview
fun PasswordConfirmTextField(
    passwordConfirm: MutableState<String> = mutableStateOf("Password Confirm"),
    passwordProvider: () -> String = { "" },
) {
    SignUpTextField(
        fieldState = getPasswordConfirmTextFieldState(text = passwordConfirm.value, inputedPassword = passwordProvider()),
        keyboardOptions = keyboardPasswordNextType,
        onTextChanged = passwordConfirm::value::set
    )
}

const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

const val userNameLengthErrorText = "이름은 2~5자여야 합니다."
const val userNameNotMatchErrorText = "이름에는 숫자나 기호가 포함될 수 없습니다."
const val emailFormatNotMatchErrorText = "이메일 형식이 올바르지 않습니다."
const val passwordFormatNotMatchErrorText = "비밀번호는 영문과 숫자를 포함해야 합니다."
const val passwordLengthErrorText = "비밀번호는 8~16자여야 합니다."
const val passwordConfirmNotMatchErrorText = "비밀번호가 일치하지 않습니다."

fun getPasswordConfirmTextFieldState(
    label: String = "Username",
    text: String,
    inputedPassword: String,
) = when {
    text.isEmpty() -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = Blue50,
        supportingText = ""
    )

    text != inputedPassword -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = ErrorTextColor,
        supportingText = passwordConfirmNotMatchErrorText
    )

    else -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = Blue50,
        supportingText = ""
    )
}



fun getPasswordTextFieldState(
    text: String,
    label: String = "Password",
) = when {
    text.isEmpty() -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = Blue50,
        supportingText = ""
    )

    text.length !in 8..16 -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = ErrorTextColor,
        supportingText = passwordLengthErrorText
    )

    !text.matches(Regex(PASSWORD_REGEX)) -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = ErrorTextColor,
        supportingText = passwordFormatNotMatchErrorText
    )

    else -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = Blue50,
        supportingText = ""
    )
}

fun getEmailTextFieldState(
    text: String,
    label: String = "Email",
) = when {
    text.isEmpty() -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = Blue50,
        supportingText = ""
    )

    !text.matches(Regex(EMAIL_REGEX)) -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = ErrorTextColor,
        supportingText = emailFormatNotMatchErrorText
    )

    else -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = Blue50,
        supportingText = ""
    )
}

fun getUsernameTextFieldState(
    text: String,
    label: String = "Username",
) = when {
    text.isEmpty() -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = Blue50,
        supportingText = ""
    )

    text.length !in 2..5 -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = ErrorTextColor,
        supportingText = userNameLengthErrorText
    )

    !text.matches(Regex(USERNAME_REGEX)) -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = ErrorTextColor,
        supportingText = userNameNotMatchErrorText
    )

    else -> SignUpTextFieldState(
        label = label,
        text = text,
        textFieldColors = Blue50,
        supportingText = ""
    )
}