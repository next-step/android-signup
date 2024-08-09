package nextstep.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R


@Composable
fun SignUpTitleText(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 26.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
        ),
    )
}

@Composable
private fun SignUpTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val customColors = TextFieldDefaults.colors(
        focusedIndicatorColor = colorResource(id = R.color.blue_50),
        cursorColor = colorResource(id = R.color.blue_50),
        focusedLabelColor = colorResource(id = R.color.blue_50)
    )
    TextField(
        value = text,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        colors = customColors,
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
    )
}

@Composable
fun SignUpFormComponent(
    userName: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        SignUpTextField(
            text = userName,
            label = stringResource(R.string.sign_up_text_field_username),
            onValueChange = onUserNameChange
        )

        SignUpTextField(
            text = email,
            label = stringResource(R.string.sign_up_text_field_email),
            onValueChange = onEmailChange
        )

        SignUpTextField(
            text = password,
            label = stringResource(R.string.sign_up_text_field_password),
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

        SignUpTextField(
            text = passwordConfirm,
            label = stringResource(R.string.sign_up_text_field_password_confirm),
            onValueChange = onPasswordConfirmChange,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
    }
}

@Composable
fun SignUpButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(modifier = modifier
        .fillMaxWidth()
        .height(50.dp),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue_50)),
        onClick = { onClick() }) {
        Text(
            text = stringResource(R.string.sign_up_button),
            color = Color.White,
            fontSize = 14.sp,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
private fun PreviewSignUpTitleText() {
    SignUpTitleText(text = stringResource(R.string.sign_up_text_title))
}

@Preview
@Composable
private fun PreviewSignUpTextField() {
    SignUpTextField(text = "compose", onValueChange = {}, label = "Username")
}

@Preview
@Composable
private fun PreviewSignUpFormComponent() {
    SignUpFormComponent(userName = "compose",
        email = "test@test.com",
        password = "12345678",
        passwordConfirm = "12345678",
        onUserNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onPasswordConfirmChange = {})
}

@Preview
@Composable
private fun PreviewSignUpButton() {
    SignUpButton(onClick = {})
}