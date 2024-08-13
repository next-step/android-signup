package nextstep.signup.ui.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.SignUpViewModel
import nextstep.signup.ui.component.EmailInput
import nextstep.signup.ui.component.PasswordConfirmInput
import nextstep.signup.ui.component.PasswordInput
import nextstep.signup.ui.component.SignUpButton
import nextstep.signup.ui.component.UserNameInput
import nextstep.signup.ui.theme.SignupTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val signUpUiState by viewModel.uiState.collectAsState()
    val inputModifier = Modifier.padding(top = 20.dp)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                text = stringResource(id = R.string.title),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            UserNameInput(
                value = signUpUiState.username,
                onValueChange = viewModel::updateUsername,
                isLengthError = signUpUiState.isUsernameLengthError,
                isFormatError = signUpUiState.isUsernameFormatError,
                modifier = inputModifier
            )

            EmailInput(
                value = signUpUiState.email,
                onValueChange = viewModel::updateEmail,
                isFormatError = signUpUiState.isEmailFormatError,
                modifier = inputModifier
            )

            PasswordInput(
                value = signUpUiState.password,
                onValueChange = viewModel::updatePassword,
                isValidationError = signUpUiState.isPasswordValidationError,
                modifier = inputModifier
            )

            PasswordConfirmInput(
                value = signUpUiState.passwordConfirm,
                onValueChange = viewModel::updatePasswordConfirm,
                isMismatchError = signUpUiState.isPasswordMismatchError,
                modifier = inputModifier
            )
            SignUpButton(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = context.getString(R.string.signupSuccessSnackBarContentDescription),
                            actionLabel = context.getString(R.string.signupSuccessSnackBarActionLabel),
                            duration = SnackbarDuration.Short
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.Gray
                ),
                enabled = signUpUiState.isSignUpButtonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(20.dp)
            )
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen(
            modifier = Modifier
        )
    }
}