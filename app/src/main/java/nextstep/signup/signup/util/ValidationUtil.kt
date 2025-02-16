package nextstep.signup.signup.util

import nextstep.signup.signup.SignUpValidationType

object ValidationUtil {
    private val USERNAME_REGEX = "^[a-zA-Z가-힣]+$".toRegex()
    private val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
    private val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$".toRegex()

    fun isNameValid(username: String): SignUpValidationType {
        return when {
            username.length < 2 || username.length > 5 -> {
                SignUpValidationType.USERNAME_LENGTH_ERROR
            }

            !username.matches(USERNAME_REGEX) -> {
                SignUpValidationType.USERNAME_FORMAT_ERROR
            }

            else -> {
                SignUpValidationType.VALID
            }
        }
    }

    fun isEmailValid(email: String): SignUpValidationType {
        return when {

            !email.matches(EMAIL_REGEX) -> {
                SignUpValidationType.EMAIL_FORMAT_ERROR
            }

            else -> {
                SignUpValidationType.VALID
            }
        }
    }

    fun isPasswordValid(password: String): SignUpValidationType {
        return when {
            password.length < 8 || password.length > 16 -> {
                SignUpValidationType.PASSWORD_LENGTH_ERROR
            }

            !password.matches(PASSWORD_REGEX) -> {
                SignUpValidationType.PASSWORD_FORMAT_ERROR
            }

            else -> {
                SignUpValidationType.VALID
            }
        }
    }

    fun isPasswordMatch(password: String, passwordConfirm: String): SignUpValidationType {
        return when {
            password.isEmpty() || passwordConfirm.isEmpty() -> {
                SignUpValidationType.VALID
            }

            password != passwordConfirm -> {
                SignUpValidationType.PASSWORD_MISMATCH_ERROR
            }

            else -> {
                SignUpValidationType.VALID
            }
        }
    }
}