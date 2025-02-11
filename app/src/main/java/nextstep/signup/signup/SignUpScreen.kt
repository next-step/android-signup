package nextstep.signup.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.SignUpTextFieldValidation


@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordConfirm by remember { mutableStateOf("") }

        var usernameSupportingText by remember { mutableStateOf("") }
        var emailSupportingText by remember { mutableStateOf("") }
        var passwordSupportingText by remember { mutableStateOf("") }
        var passwordConfirmSupportingText by remember { mutableStateOf("") }

        val validation = SignUpTextFieldValidation()

        val onUsernameChanged: (String) -> Unit = {
            username = it
            usernameSupportingText = validation.getUsernameValidationMessage(username)
        }

        val onEmailChanged: (String) -> Unit = {
            email = it
            emailSupportingText = validation.getEmailValidationMessage(email)
        }

        val onPasswordChanged: (String) -> Unit = {
            password = it
            passwordSupportingText =
                validation.getPasswordValidationMessage(password, passwordConfirm)
            passwordConfirmSupportingText =
                validation.getPasswordConfirmValidationMessage(password, passwordConfirm)
        }

        val onPasswordConfirmChanged: (String) -> Unit = {
            passwordConfirm = it
            passwordConfirmSupportingText =
                validation.getPasswordConfirmValidationMessage(password, passwordConfirm)
        }

        SignUpTitle(Modifier.padding(top = 60.dp))
        SignUpTextField(
            text = username,
            label = {
                Text(
                    text = stringResource(id = R.string.username),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 0.5.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.padding(top = 42.dp),
            onTextChanged = onUsernameChanged,
            errorMessage = usernameSupportingText,
            supportingText = if (usernameSupportingText.isNotEmpty()) {
                {
                    Text(
                        text = usernameSupportingText,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.W400,
                    )
                }
            } else {
                null
            }
        )
        SignUpTextField(
            text = email,
            label = {
                Text(
                    text = stringResource(id = R.string.email),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 0.5.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.padding(top = 33.dp),
            onTextChanged = onEmailChanged,
            errorMessage = emailSupportingText,
            supportingText = if (emailSupportingText.isNotEmpty()) {
                {
                    Text(
                        text = emailSupportingText,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.W400,
                    )
                }
            } else {
                null
            }
        )
        SignUpTextField(
            text = password,
            label = {
                Text(
                    text = stringResource(id = R.string.password),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 0.5.sp
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.padding(top = 33.dp),
            onTextChanged = onPasswordChanged,
            errorMessage = passwordSupportingText,
            supportingText = if (passwordSupportingText.isNotEmpty()) {
                {
                    Text(
                        text = passwordSupportingText,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.W400,
                    )
                }
            } else {
                null
            }
        )
        SignUpTextField(
            text = passwordConfirm,
            label = {
                Text(
                    text = stringResource(id = R.string.password_confirm),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 0.5.sp
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.padding(top = 33.dp),
            onTextChanged = onPasswordConfirmChanged,
            errorMessage = passwordConfirmSupportingText,
            supportingText = if (passwordConfirmSupportingText.isNotEmpty()) {
                {
                    Text(
                        text = passwordConfirmSupportingText,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.W400,
                    )
                }
            } else {
                null
            }
        )
        SignUpButton(Modifier.padding(top = 39.dp))
    }
}