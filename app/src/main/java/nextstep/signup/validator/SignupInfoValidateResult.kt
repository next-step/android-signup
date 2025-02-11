package nextstep.signup.validator

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import nextstep.signup.R

sealed interface SignupInfoValidateResult {
    fun isSuccess() = this == Success
    fun isError() = this != Success && this != Empty

    @Composable
    fun getErrorMessage(): String

    data object Success : SignupInfoValidateResult {
        @Composable
        override fun getErrorMessage(): String = ""

    }

    data object Empty : SignupInfoValidateResult {
        @Composable
        override fun getErrorMessage(): String = ""
    }

    sealed interface Failure : SignupInfoValidateResult {

        enum class Username : Failure {
            INVALID_LENGTH_USERNAME,
            INVALID_FORMAT_USERNAME;

            @Composable
            override fun getErrorMessage(): String {
                return when (this) {
                    INVALID_LENGTH_USERNAME -> stringResource(R.string.invalid_length_username)
                    INVALID_FORMAT_USERNAME -> stringResource(R.string.invalid_format_username)
                }
            }
        }

        enum class Email : Failure {
            INVALID_FORMAT_EMAIL;

            @Composable
            override fun getErrorMessage(): String {
                return when (this) {
                    INVALID_FORMAT_EMAIL -> stringResource(R.string.invalid_format_email)
                }
            }
        }

        enum class Password : Failure {
            INVALID_LENGTH_PASSWORD,
            INVALID_FORMAT_PASSWORD;

            @Composable
            override fun getErrorMessage(): String {
                return when (this) {
                    INVALID_LENGTH_PASSWORD -> stringResource(R.string.invalid_length_password)
                    INVALID_FORMAT_PASSWORD -> stringResource(R.string.invalid_format_password)
                }
            }
        }

        enum class PasswordConfirm : Failure {
            NOT_MATCH_PASSWORD;

            @Composable
            override fun getErrorMessage(): String {
                return when (this) {
                    NOT_MATCH_PASSWORD -> stringResource(R.string.not_match_password)
                }
            }
        }
    }
}
