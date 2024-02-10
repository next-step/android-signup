package nextstep.signup.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.signup.component.SignUpHeader
import nextstep.signup.signup.component.SignUpInputTextField
import nextstep.signup.signup.component.SignUpSubmitButton

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel,
) {
    Column(
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 60.dp),
        verticalArrangement = Arrangement.spacedBy(42.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val uiState by signUpViewModel.uiState.collectAsState()

        SignUpHeader()

        Column(
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            SignUpInputTextField(
                labelName = stringResource(id = R.string.signup_input_text_label_username),
                value = uiState.username,
                onTextChanged = { signUpViewModel.updateUserName(it) },
                errorMessage = when {
                    uiState.isUserNameLengthOutOfRange -> stringResource(id = R.string.signup_input_text_error_message_username_length_out_of_range)
                    uiState.isUserNameHasNumberOrSymbol -> stringResource(id = R.string.signup_input_text_error_message_username_has_number_or_symbol)
                    else -> null
                }
            )

            SignUpInputTextField(
                labelName = stringResource(id = R.string.signup_input_text_label_email),
                value = uiState.email,
                onTextChanged = { signUpViewModel.updateEmail(it) },
                errorMessage = when {
                    uiState.isInvalidEmail -> stringResource(id = R.string.signup_input_text_error_message_email_is_invalid)
                    else -> null
                }
            )

            SignUpInputTextField(
                labelName = stringResource(id = R.string.signup_input_text_label_password),
                value = uiState.password,
                isInputPassword = true,
                onTextChanged = { signUpViewModel.updatePassword(it) },
                errorMessage = when {
                    uiState.isPasswordLengthOutOfRange -> stringResource(id = R.string.signup_input_text_error_message_password_length_out_of_range)
                    uiState.isPasswordHasNotAlphabetAndNumber -> stringResource(id = R.string.signup_input_text_error_message_password_must_have_alphabet_and_number)
                    else -> null
                }
            )

            SignUpInputTextField(
                labelName = stringResource(id = R.string.signup_input_text_label_password_confirm),
                value = uiState.passwordConfirm,
                isInputPassword = true,
                onTextChanged = { signUpViewModel.updatePasswordConfirm(it) },
                errorMessage = when {
                    uiState.isPasswordConfirmNotMatched -> stringResource(id = R.string.signup_input_text_error_message_password_confirm_not_matched)
                    else -> null
                }
            )
        }

        SignUpSubmitButton(
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        signUpViewModel = SignUpViewModel(),
    )
}
