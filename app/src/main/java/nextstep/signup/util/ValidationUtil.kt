package nextstep.signup.util

import nextstep.signup.util.RegexUtil.isEmailFormat
import nextstep.signup.util.RegexUtil.isPasswordFormat
import nextstep.signup.util.RegexUtil.isWithoutSpecialChars

object ValidationUtil {

    fun setUsernameErrorMessage(userName: String) = when {
        userName.isBlank() -> ""
        userName.length !in (2..5) -> USER_NAME_LENGTH_ERROR
        userName.isWithoutSpecialChars().not() -> USER_NAME_IN_SPECIAL_CHARS_ERROR
        else -> ""
    }

    fun setEmailErrorMessage(email: String) = when {
        email.isBlank() -> ""
        email.isEmailFormat().not() -> EMAIL_FORMAT_ERROR
        else -> ""
    }

    fun setPasswordErrorMessage(password: String) = when {
        password.isBlank() -> ""
        password.length !in (8..16) -> PASSWORD_LENGTH_ERROR
        password.isPasswordFormat().not() -> PASSWORD_FORMAT_ERROR
        else -> ""
    }

    const val USER_NAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
    const val USER_NAME_IN_SPECIAL_CHARS_ERROR = "이름에 특수문자가 들어갈 수 없습니다."
    const val EMAIL_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
    const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
    const val PASSWORD_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
}
