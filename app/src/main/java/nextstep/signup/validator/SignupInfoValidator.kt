package nextstep.signup.validator

sealed interface SignupInfoValidator {
    fun checkCondition(value: String): SignupInfoValidateResult

    data object Username : SignupInfoValidator {

        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private val regex by lazy { Regex(USERNAME_REGEX) }

        override fun checkCondition(value: String): SignupInfoValidateResult {
            if (value.isEmpty()) return SignupInfoValidateResult.Empty
            if (value.length !in 2..5) return SignupInfoValidateResult.Failure.Username.INVALID_LENGTH_USERNAME

            return when (value.matches(regex)) {
                true -> SignupInfoValidateResult.Success
                false -> SignupInfoValidateResult.Failure.Username.INVALID_FORMAT_USERNAME
            }
        }
    }

    data object Email : SignupInfoValidator {

        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private val regex by lazy { Regex(EMAIL_REGEX) }

        override fun checkCondition(value: String): SignupInfoValidateResult {
            if (value.isEmpty()) return SignupInfoValidateResult.Empty
            return when (value.matches(regex)) {
                true -> SignupInfoValidateResult.Success
                false -> SignupInfoValidateResult.Failure.Email.INVALID_FORMAT_EMAIL
            }
        }
    }

    data object Password : SignupInfoValidator {

        private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
        private val regex by lazy { Regex(PASSWORD_REGEX) }

        override fun checkCondition(value: String): SignupInfoValidateResult {
            if (value.isEmpty()) return SignupInfoValidateResult.Empty
            if (value.length !in 8..16) return SignupInfoValidateResult.Failure.Password.INVALID_LENGTH_PASSWORD

            return when (value.matches(regex)) {
                true -> SignupInfoValidateResult.Success
                false -> SignupInfoValidateResult.Failure.Password.INVALID_FORMAT_PASSWORD
            }
        }
    }

    class PasswordConfirm(
        private val passwordProvider: () -> String,
    ) : SignupInfoValidator {
        override fun checkCondition(value: String): SignupInfoValidateResult {
            if (value.isEmpty()) return SignupInfoValidateResult.Empty
            return when (passwordProvider() == value) {
                true -> SignupInfoValidateResult.Success
                false -> SignupInfoValidateResult.Failure.PasswordConfirm.NOT_MATCH_PASSWORD
            }
        }
    }
}
