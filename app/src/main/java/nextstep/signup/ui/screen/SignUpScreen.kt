package nextstep.signup.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.SignUpViewModel
import nextstep.signup.ui.component.SignUpTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 112.dp)
        ) {
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
                SignUpTextField(
                    inputText = viewModel.userName,
                    label = stringResource(id = R.string.user_name),
                    onTextValueChange = { viewModel.userName = it },
                    validResult = viewModel.validateUserName(),
                    visualTransformation = VisualTransformation.None,
                    errMsg = stringResource(id = R.string.err_msg_user_name)
                )

                SignUpTextField(
                    inputText = viewModel.email,
                    label = stringResource(id = R.string.email),
                    onTextValueChange = { viewModel.email = it },
                    validResult = viewModel.validateEmail(),
                    visualTransformation = VisualTransformation.None,
                    errMsg = stringResource(id = R.string.err_msg_email)
                )

                SignUpTextField(
                    inputText = viewModel.password,
                    label = stringResource(id = R.string.password),
                    onTextValueChange = { viewModel.password = it },
                    validResult = viewModel.validatePassword(),
                    visualTransformation = PasswordVisualTransformation(),
                    errMsg = stringResource(id = R.string.err_msg_password)
                )

                SignUpTextField(
                    inputText = viewModel.passwordConfirm,
                    label = stringResource(id = R.string.password_confirm),
                    onTextValueChange = { viewModel.passwordConfirm = it },
                    validResult = viewModel.validatePasswordConfirm(),
                    visualTransformation = PasswordVisualTransformation(),
                    errMsg = stringResource(id = R.string.err_msg_password_confirm)
                )
            }

            Button(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                enabled = viewModel.validateAll(),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    bottom = 16.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
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