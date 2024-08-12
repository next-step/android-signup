package nextstep.signup.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.SignUpViewModel
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.InputErrorText
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.UserNameTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 112.dp)
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()
        val context = LocalContext.current

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Welcome to Compose \uD83D\uDE80",
            fontSize = 26.sp,
            color = Color.Black,
            style = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )
        Column(
            modifier = Modifier.padding(top = 62.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Column {
                UserNameTextField(
                    viewModel.userName,
                    onTextValueChange = { viewModel.userName = it }
                )

                if (viewModel.userName.isNotEmpty()) {
                    InputErrorText(
                        stringResource(id = R.string.err_msg_user_name),
                        viewModel.validateUserName()
                    )
                }
            }

            Column {
                EmailTextField(
                    text = viewModel.email,
                    onTextValueChange = { viewModel.email = it }
                )


                if (viewModel.email.isNotEmpty()) {
                    InputErrorText(
                        stringResource(id = R.string.err_msg_email),
                        viewModel.validateEmail()
                    )
                }
            }

            Column {
                PasswordTextField(
                    text = viewModel.password,
                    onTextValueChange = { viewModel.password = it }
                )


                if (viewModel.password.isNotEmpty()) {
                    InputErrorText(
                        stringResource(id = R.string.err_msg_password),
                        viewModel.validatePassword()
                    )
                }
            }

            Column {
                PasswordConfirmTextField(
                    text = viewModel.passwordConfirm,
                    onTextValueChange = { viewModel.passwordConfirm = it },
                    viewModel.password
                )

                if (viewModel.passwordConfirm.isNotEmpty()) {
                    InputErrorText(
                        stringResource(id = R.string.err_msg_password_confirm),
                        viewModel.validatePasswordConfirm()
                    )
                }
            }
        }

        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) {
            Button(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                enabled = viewModel.validateAll(),
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = context.getString(R.string.text_sign_up_success),
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up),
                    modifier = Modifier.padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        start = 24.dp,
                        end = 24.dp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(SignUpViewModel())
}