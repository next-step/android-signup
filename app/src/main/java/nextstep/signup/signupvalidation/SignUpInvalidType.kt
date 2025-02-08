package nextstep.signup.signupvalidation

import androidx.annotation.StringRes
import nextstep.signup.R

enum class SignUpInvalidType(@StringRes val resId: Int) {
    INVALID_USER_NAME_LENGTH(R.string.sign_up_invalid_user_name_length),
    INVALID_USER_NAME_LETTER(R.string.sign_up_invalid_user_name_letter),
    INVALID_EMAIL_FORM(R.string.sign_up_invalid_email_form),
    INVALID_PASSWORD_LENGTH(R.string.sign_up_invalid_password_length),
    INVALID_PASSWORD_LETTER(R.string.sign_up_invalid_password_letter),
    INVALID_PASSWORD_CONFIRM(R.string.sign_up_invalid_password_confirm)
}
