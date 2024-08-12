package nextstep.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun setMessage(state: TextFieldState, type: TextFieldType): String {
    when (type) {
        TextFieldType.Username -> {
            return when (state) {
                TextFieldState.Valid -> ""
                TextFieldState.InValid -> stringResource(id = R.string.username_invalid)
                TextFieldState.LengthError -> stringResource(id = R.string.username_invalid_length)
                TextFieldState.Default -> ""
            }
        }

        TextFieldType.Email -> {
            return when (state) {
                TextFieldState.Valid -> ""
                TextFieldState.InValid -> stringResource(id = R.string.email_invalid)
                TextFieldState.LengthError -> ""
                TextFieldState.Default -> ""
            }
        }

        TextFieldType.Password -> {
            return when (state) {
                TextFieldState.Valid -> ""
                TextFieldState.InValid -> stringResource(id = R.string.password_invalid)
                TextFieldState.LengthError -> stringResource(id = R.string.password_invalid_length)
                TextFieldState.Default -> ""
            }
        }

        TextFieldType.Default -> return ""
    }
}