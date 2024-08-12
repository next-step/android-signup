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
fun PasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    signUpUiState: SignUpUiState,
    modifier: Modifier = Modifier
) {
    SignUpInput(
        value = value,
        onValueChange = onValueChange,
        isError = signUpUiState.isPasswordValidationError,
        supportingText = {
            if (signUpUiState.isPasswordValidationError) {
                Text(text = stringResource(id = R.string.passwordValidationMessage))
            }
        },
        label = stringResource(id = R.string.passwordLabel),
        modifier = modifier
    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@Preview
@Composable
private fun PasswordInputPreview() {
    val viewmodel: SignUpViewModel = viewModel()

    SignupTheme {
        PasswordInput(
            value = viewmodel.uiState.value.password,
            onValueChange = {
                viewmodel.updatePassword(it)
            },
            signUpUiState = viewmodel.uiState.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}