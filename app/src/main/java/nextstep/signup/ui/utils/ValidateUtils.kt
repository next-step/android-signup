package nextstep.signup.ui.utils

import nextstep.signup.ui.utils.RegexPatterns.EMAIL_REGEX
import nextstep.signup.ui.utils.RegexPatterns.PASSWORD_REGEX
import nextstep.signup.ui.utils.RegexPatterns.USERNAME_REGEX

object ValidateUtils {
    // 이름은 2~5 검증
    fun isValidUsernameLength(username: String): Boolean {
        return username.length in 2..5
    }

    // 이름에는 숫자나 기호가 포함될 수 없음 검증
    fun isValidUsername(username: String): Boolean {
        return USERNAME_REGEX.toRegex().matches(username)
    }

    // 이메일 형식 검증
    fun isValidEmail(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email)
    }

    // 비밀번호 길이 8~16 검증
    fun isValidPasswordLength(password: String): Boolean {
        return password.length in 8..16
    }

    // 비밀번호에 영문과 숫자가 포함된 경우 검증
    fun isValidPassword(password: String): Boolean {
        return PASSWORD_REGEX.toRegex().matches(password)
    }

    // 비밀번호 확인이 일치하는지 검증
    fun isValidatePasswordConfirm(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    // 모든 입력 필드 유효성 검증
    fun isValidAll(username: String, email: String, password: String, confirmPassword: String): Boolean {
        return isValidUsernameLength(username) &&
            isValidUsername(username) &&
            isValidEmail(email) &&
            isValidPasswordLength(password) &&
            isValidPassword(password) &&
            isValidatePasswordConfirm(password, confirmPassword)
    }
}

