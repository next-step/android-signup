package nextstep.signup.ui.utils

import android.content.Context
import nextstep.signup.R

object MessageUtils {
    fun getUserNameErorrMessage(context: Context, userName: String): String {
        return when {
            userName.isEmpty() -> ""
            !ValidateUtils.isValidUsernameLength(userName) -> context.getString(R.string.user_name_error_length)
            !ValidateUtils.isValidUsername(userName) -> context.getString(R.string.user_name_error_invalid)
            else -> ""
        }
    }

    fun getEmailErrorMessage(context: Context, email: String): String {
        return when {
            email.isEmpty() -> ""
            !ValidateUtils.isValidEmail(email) -> context.getString(R.string.email_error_invalid)
            else -> ""
        }
    }

    fun getPasswordErrorMessage(context: Context, password: String): String {
        return when {
            password.isEmpty() -> ""
            !ValidateUtils.isValidPasswordLength(password) -> context.getString(R.string.password_error_length)
            !ValidateUtils.isValidPassword(password) -> context.getString(R.string.password_error_invalid)
            else -> ""
        }
    }

    fun getPasswordConfirmErrorMessage(
        context: Context,
        password: String,
        confirmPassword: String,
    ): String {
        return when {
            password.isEmpty() || confirmPassword.isEmpty() -> ""
            !ValidateUtils.isValidatePasswordConfirm(
                password,
                confirmPassword
            ) -> context.getString(R.string.password_confirm_error_invalid)

            else -> ""
        }
    }

    fun getSignUpCompleteMessage(context: Context): String {
        return context.getString(R.string.sign_up_success)
    }
}