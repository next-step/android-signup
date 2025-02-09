package nextstep.signup.util

import nextstep.signup.util.RegexUtil.isEmailFormat
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

    const val USER_NAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
    const val USER_NAME_IN_SPECIAL_CHARS_ERROR = "이름에 특수문자가 들어갈 수 없습니다."
    const val EMAIL_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
}
