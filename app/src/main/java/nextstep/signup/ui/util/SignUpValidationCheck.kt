package nextstep.signup.ui.util

import nextstep.signup.ui.signup.SignUpValidation


object SignUpValidationCheck {
    private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

    fun validateUsername(username: String): SignUpValidation {
        val lengthCondition = username.length in 2..5
        val regexCondition = username.matches(Regex(USERNAME_REGEX))

        return when {
            username.isNotBlank() && lengthCondition.not() -> SignUpValidation.INVALID_LENGTH
            lengthCondition && regexCondition.not() -> SignUpValidation.INVALID_CHARACTER
            else -> SignUpValidation.NONE
        }
    }

    fun validateEmail(email: String): SignUpValidation {
        val regexCondition = email.matches(Regex(EMAIL_REGEX))

        return when {
            email.isNotBlank() && regexCondition.not() -> SignUpValidation.INVALID_EMAIL
            else -> SignUpValidation.NONE
        }
    }

    fun validatePassword(password: String): SignUpValidation {
        val lengthCondition = password.length in 8..16
        val regexCondition = password.matches(Regex(PASSWORD_REGEX))

        return when {
            password.isNotBlank() && lengthCondition.not() -> SignUpValidation.INVALID_LENGTH
            lengthCondition && regexCondition.not() -> SignUpValidation.INVALID_PASSWORD
            else -> SignUpValidation.NONE
        }
    }

    fun validatePasswordConfirm(
        password: String,
        passwordConfirm: String
    ): SignUpValidation = if (password != passwordConfirm)
        SignUpValidation.INVALID_PASSWORD_MATCH
    else
        SignUpValidation.NONE
}
