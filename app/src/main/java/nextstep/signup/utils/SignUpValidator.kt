package nextstep.signup.utils

enum class SignUpErrorType {
    NO_ERROR,
    USERNAME_LENGTH_ERROR,
    USERNAME_FORMAT_ERROR,
    EMAIL_ERROR,
    PASSWORD_LENGTH_ERROR,
    PASSWORD_FORMAT_ERROR,
    PASSWORD_CONFIRM_ERROR
}

private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"

private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

sealed interface SignUpValidator {

    val value: String

    fun validate(): SignUpErrorType

    data class Username(override val value: String) : SignUpValidator {
        override fun validate(): SignUpErrorType {
            return when {
                value.isEmpty() -> SignUpErrorType.NO_ERROR
                value.length < 2 || value.length > 5 -> SignUpErrorType.USERNAME_LENGTH_ERROR
                !value.matches(Regex(USERNAME_REGEX)) -> SignUpErrorType.USERNAME_FORMAT_ERROR
                else -> SignUpErrorType.NO_ERROR
            }
        }
    }

    data class Email(override val value: String) : SignUpValidator {
        override fun validate(): SignUpErrorType {
            return if (value.isEmpty() || value.matches(Regex(EMAIL_REGEX))) {
                SignUpErrorType.NO_ERROR
            } else {
                SignUpErrorType.EMAIL_ERROR
            }
        }
    }

    data class Password(override val value: String) : SignUpValidator {
        override fun validate(): SignUpErrorType {
            return when {
                value.isEmpty() -> SignUpErrorType.NO_ERROR
                value.length < 8 || value.length > 16 -> SignUpErrorType.PASSWORD_LENGTH_ERROR
                !value.matches(Regex(PASSWORD_REGEX)) -> SignUpErrorType.PASSWORD_FORMAT_ERROR
                else -> SignUpErrorType.NO_ERROR
            }
        }
    }

    data class PasswordConfirm(override val value: String, val password: String) : SignUpValidator {
        override fun validate(): SignUpErrorType {
            return if (value.isEmpty() || password == value) {
                SignUpErrorType.NO_ERROR
            } else {
                SignUpErrorType.PASSWORD_CONFIRM_ERROR
            }
        }
    }

}
