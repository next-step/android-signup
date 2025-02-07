package nextstep.signup.validator

sealed interface Validator {
    fun checkCondition(value: String): ValidateResult

    data object Username : Validator {

        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private val regex by lazy { Regex(USERNAME_REGEX) }

        override fun checkCondition(value: String): ValidateResult {
            if (value.isEmpty()) return ValidateResult.SUCCESS
            if (value.length !in 2..5) return ValidateResult.INVALID_LENGTH_USERNAME

            return when (value.matches(regex)) {
                true -> ValidateResult.SUCCESS
                false -> ValidateResult.INVALID_FORMAT_USERNAME
            }
        }
    }

    data object Email : Validator {

        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private val regex by lazy { Regex(EMAIL_REGEX) }

        override fun checkCondition(value: String): ValidateResult {
            if (value.isEmpty()) return ValidateResult.SUCCESS
            return when (value.matches(regex)) {
                true -> ValidateResult.SUCCESS
                false -> ValidateResult.INVALID_FORMAT_EMAIL
            }
        }
    }

    data object Password : Validator {

        private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
        private val regex by lazy { Regex(PASSWORD_REGEX) }

        override fun checkCondition(value: String): ValidateResult {
            if (value.isEmpty()) return ValidateResult.SUCCESS
            if (value.length !in 8..16) return ValidateResult.INVALID_LENGTH_PASSWORD

            return when (value.matches(regex)) {
                true -> ValidateResult.SUCCESS
                false -> ValidateResult.INVALID_FORMAT_PASSWORD
            }
        }
    }

    class PasswordConfirm(
        private val passwordProvider: () -> String
    ) : Validator {
        override fun checkCondition(value: String): ValidateResult {
            if (value.isEmpty()) return ValidateResult.SUCCESS
            return when (passwordProvider() == value) {
                true -> ValidateResult.SUCCESS
                false -> ValidateResult.NOT_MATCH_PASSWORD
            }
        }
    }
}
