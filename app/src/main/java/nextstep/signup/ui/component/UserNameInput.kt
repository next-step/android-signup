package nextstep.signup.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
fun UserNameInput(
    value: String,
    onValueChange: (String) -> Unit,
    signUpUiState: SignUpUiState,
    modifier: Modifier = Modifier
) {
    SignUpInput(
        value = value,
        onValueChange = onValueChange,
        isError = signUpUiState.isUsernameLengthError || signUpUiState.isUsernameFormatError,
        supportingText = {
            if (signUpUiState.isUsernameLengthError) {
                Text(text = stringResource(id = R.string.userNameLengthValidationMessage))
            } else if (signUpUiState.isUsernameFormatError) {
                Text(text = stringResource(id = R.string.userNameInvalidCharactersMessage))
            }
        },
        label = stringResource(id = R.string.userNameLabel),
        modifier = modifier
    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@Preview
@Composable
private fun UserNameInputPreview() {
    val viewmodel: SignUpViewModel = viewModel()
    val signUpUiState by viewmodel.uiState.collectAsState()

    SignupTheme {
        UserNameInput(
            value = signUpUiState.username,
            onValueChange = viewmodel::updateUsername,
            signUpUiState = signUpUiState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}