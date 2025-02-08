package nextstep.signup.util

object RegexUtil {

    private const val WITHOUT_SPECIAL_CHARS_REGEX = "^[a-zA-Z가-힣0-9]+$"

    fun String.isWithoutSpecialChars() = matches(WITHOUT_SPECIAL_CHARS_REGEX.toRegex())
}