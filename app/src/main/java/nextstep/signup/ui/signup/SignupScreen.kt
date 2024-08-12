package nextstep.signup.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success
import nextstep.signup.ui.signup.SignupValidator.Email
import nextstep.signup.ui.signup.SignupValidator.Password
import nextstep.signup.ui.signup.SignupValidator.PasswordConfirm
import nextstep.signup.ui.signup.SignupValidator.Username
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGray20
import nextstep.signup.ui.theme.Gray
import nextstep.signup.ui.theme.RobotoBold
import nextstep.signup.ui.theme.RobotoMedium
import nextstep.signup.ui.theme.RobotoRegular

@Composable
fun SignUpScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 32.dp),
    ) {
        val (userName, setUserName) = remember { mutableStateOf("") }
        val (email, setEmail) = remember { mutableStateOf("") }
        val (password, setPassword) = remember { mutableStateOf("") }
        val (passwordConfirm, setPasswordConfirm) = remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = stringResource(R.string.signup_header_title),
            color = Color.Black,
            fontSize = 26.sp,
            fontFamily = RobotoBold,
        )
        Spacer(modifier = Modifier.height(42.dp))
        SignUpTextField(
            label = stringResource(R.string.signup_user_name),
            onTextChanged = setUserName,
            text = userName,
            inputValidation = userName.isValid<Username>(),
        )
        Spacer(modifier = Modifier.height(36.dp))
        SignUpTextField(
            label = stringResource(R.string.signup_email),
            onTextChanged = setEmail,
            text = email,
            inputValidation = email.isValid<Email>(),
        )
        Spacer(modifier = Modifier.height(36.dp))
        SignUpTextField(
            label = stringResource(R.string.signup_password),
            onTextChanged = setPassword,
            text = password,
            visualTransformation = PasswordVisualTransformation(),
            inputValidation = password.isValid<Password>(),
        )
        Spacer(modifier = Modifier.height(36.dp))
        SignUpTextField(
            label = stringResource(R.string.signup_password_confirm),
            onTextChanged = setPasswordConfirm,
            text = passwordConfirm,
            visualTransformation = PasswordVisualTransformation(),
            inputValidation = passwordConfirm.isValid<PasswordConfirm>(password),
        )
        Spacer(modifier = Modifier.height(42.dp))
        SignUpButton()
    }
}

@Composable
private fun SignUpButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = Blue50),
        contentPadding = PaddingValues(vertical = 15.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.signup_button),
            fontSize = 14.sp,
            color = Color.White,
            fontFamily = RobotoMedium,
        )
    }
}

@Composable
private fun SignUpTextField(
    label: String,
    onTextChanged: (String) -> Unit,
    text: String,
    inputValidation: SignupValidationResult = Success,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp,
            fontFamily = RobotoRegular,
        ),
        label = {
            Text(
                text = label,
                fontFamily = RobotoRegular,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedLabelColor = Blue50,
            unfocusedLabelColor = Gray,
            unfocusedContainerColor = BlueGray20,
            focusedContainerColor = BlueGray20,
            focusedIndicatorColor = Blue50,
        ),
        isError = inputValidation !is Success,
        supportingText = {
            if (inputValidation !is Success) Text(
                text = (inputValidation as Failure).result.message
            )
        },
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 4.dp,
                ),
            ),
    )
}

@Preview
@Composable
private fun SignUpTextFieldPreview() {
    SignUpTextField(
        label = "비밀번호",
        onTextChanged = {},
        text = "산군",
    )
}

@Preview
@Composable
private fun SignUpButtonPreview() {
    SignUpButton()
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}