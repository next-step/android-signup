package nextstep.signup.signup

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.SignUpTextFieldValidation
import nextstep.signup.ui.theme.Blue20
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.Gray50
import nextstep.signup.ui.theme.Gray70
import nextstep.signup.ui.theme.Red50

@Composable
fun UserNameTextField(
    text: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingMessage = SignUpTextFieldValidation.getUsernameValidationMessage(text)

    SignUpTextField(
        text = text,
        label = stringResource(id = R.string.username),
        onTextChanged = onTextChanged,
        errorMessage = supportingMessage,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Composable
fun EmailTextField(
    text: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingMessage = SignUpTextFieldValidation.getEmailValidationMessage(text)

    SignUpTextField(
        text = text,
        label = stringResource(id = R.string.email),
        onTextChanged = onTextChanged,
        errorMessage = supportingMessage,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Composable
fun PasswordTextField(
    text: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingMessage = SignUpTextFieldValidation.getPasswordValidationMessage(text)

    SignUpTextField(
        text = text,
        label = stringResource(id = R.string.password),
        onTextChanged = onTextChanged,
        errorMessage = supportingMessage,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Composable
fun PasswordConfirmTextField(
    text: String,
    password: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingMessage =
        SignUpTextFieldValidation.getPasswordConfirmValidationMessage(password, text)

    SignUpTextField(
        text = text,
        label = stringResource(id = R.string.password_confirm),
        onTextChanged = onTextChanged,
        errorMessage = supportingMessage,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Composable
fun SignUpTextField(
    text: String,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit,
    errorMessage: String
) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        label = {
            Text(
                text = label,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 0.5.sp,
            )
        },
        isError = errorMessage.isNotEmpty(),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Gray70,
            unfocusedTextColor = Gray70,
            focusedLabelColor = Blue50,
            unfocusedLabelColor = Gray50,
            errorLabelColor = Red50,
            focusedIndicatorColor = Blue50,
            unfocusedIndicatorColor = Gray50,
            errorIndicatorColor = Red50,
            cursorColor = Blue50,
            errorCursorColor = Red50,
            focusedContainerColor = Blue20,
            unfocusedContainerColor = Blue20,
        ),
        supportingText = if (errorMessage.isNotEmpty()) {
            {
                Text(
                    text = errorMessage,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.W400,
                )
            }
        } else {
            null
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        modifier = modifier
            .width(296.dp)
            .clip(shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
    )
}

@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldPreview() {
    var username by remember { mutableStateOf("") }

    UserNameTextField(
        text = username,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        onTextChanged = { username = it },
        modifier = Modifier.padding(top = 42.dp),
    )
}


@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview() {
    var email by remember { mutableStateOf("") }

    EmailTextField(
        text = email,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        onTextChanged = { email = it },
        modifier = Modifier.padding(top = 33.dp),
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    var password by remember { mutableStateOf("") }

    PasswordTextField(
        text = password,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        onTextChanged = { password = it },
        modifier = Modifier.padding(top = 33.dp),
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordConfirmTextFieldPreview() {
    var passwordConfirm by remember { mutableStateOf("") }

    PasswordConfirmTextField(
        text = passwordConfirm,
        password = "password",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        onTextChanged = { passwordConfirm = it },
        modifier = Modifier.padding(top = 33.dp),
    )
}