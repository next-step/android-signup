package nextstep.signup.validator

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import nextstep.signup.R

enum class SignupInfoValidateResult {
    SUCCESS,
    INVALID_LENGTH_USERNAME,
    INVALID_FORMAT_USERNAME,
    INVALID_FORMAT_EMAIL,
    INVALID_LENGTH_PASSWORD,
    INVALID_FORMAT_PASSWORD,
    NOT_MATCH_PASSWORD;

    fun isSuccess() = this == SUCCESS

    @Composable
    fun getErrorMessage() =
        when (this) {
            SUCCESS -> ""
            INVALID_LENGTH_USERNAME -> stringResource(R.string.invalid_length_username)
            INVALID_FORMAT_USERNAME -> stringResource(R.string.invalid_format_username)
            INVALID_FORMAT_EMAIL -> stringResource(R.string.invalid_format_email)
            INVALID_LENGTH_PASSWORD -> stringResource(R.string.invalid_length_password)
            INVALID_FORMAT_PASSWORD -> stringResource(R.string.invalid_format_password)
            NOT_MATCH_PASSWORD -> stringResource(R.string.not_match_password)
        }
}
