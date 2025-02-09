package nextstep.signup.util

object RegexUtil {

    private const val WITHOUT_SPECIAL_CHARS_REGEX = "^[a-zA-Z가-힣0-9]+$"
    private const val EMAIL_FORMAT_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    private const val PASSWORD_FORMAT_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

    fun String.isWithoutSpecialChars() = matches(WITHOUT_SPECIAL_CHARS_REGEX.toRegex())
    fun String.isEmailFormat() = matches(EMAIL_FORMAT_REGEX.toRegex())
    fun String.isPasswordFormat() = matches(PASSWORD_FORMAT_REGEX.toRegex())
}
