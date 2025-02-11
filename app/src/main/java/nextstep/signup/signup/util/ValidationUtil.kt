package nextstep.signup.signup.util

const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

object ValidationUtil {
    fun isNameValid(username: String): String {
        return when {
            username.length < 2 || username.length > 5 -> {
                "이름은 2~5자여야 합니다."
            }

            !username.matches(Regex(USERNAME_REGEX)) -> {
                "이름에는 숫자나 기호가 포함될 수 없습니다."
            }

            else -> {
                ""
            }
        }
    }

    fun isEmailValid(email: String): String {
        return when {

            !email.matches(Regex(EMAIL_REGEX)) -> {
                "이메일 형식이 올바르지 않습니다."
            }

            else -> {
                ""
            }
        }
    }

    fun isPasswordValid(password: String): String {
        return when {
            password.length < 8 || password.length > 16 -> {
                "비밀번호는 8~16자여야 합니다."
            }

            !password.matches(Regex(PASSWORD_REGEX)) -> {
                "비밀번호는 영문과 숫자를 포함해야 합니다."
            }

            else -> {
                ""
            }
        }
    }

    fun isPasswordMatch(password: String, passwordConfirm: String): String {
        return when {
            password.isEmpty() || passwordConfirm.isEmpty() -> {
                ""
            }

            password != passwordConfirm -> {
                "비밀번호가 일치하지 않습니다."
            }

            else -> {
                ""
            }
        }
    }
}