package nextstep.signup.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.signup.R
import nextstep.signup.SignUpUiState
import nextstep.signup.SignUpViewModel
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun PasswordConfirmInput(
    value: String,
    onValueChange: (String) -> Unit,
    signUpUiState: SignUpUiState,
    modifier: Modifier = Modifier
) {
    SignUpInput(
        value = value,
        onValueChange = onValueChange,
        isError = signUpUiState.isPasswordMismatchError,
        supportingText = {
            if (signUpUiState.isPasswordMismatchError) {
                Text(text = stringResource(id = R.string.passwordMismatchMessage))
            }
        },
        label = stringResource(id = R.string.passwordConfirmLabel),
        modifier = modifier
    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@Preview
@Composable
private fun PasswordConfirmInputPreview() {
    val viewmodel: SignUpViewModel = viewModel()

    SignupTheme {
        PasswordConfirmInput(
            value = viewmodel.passwordConfirm,
            onValueChange = {
                viewmodel.updatePasswordConfirm(it)
            },
            signUpUiState = viewmodel.uiState.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}