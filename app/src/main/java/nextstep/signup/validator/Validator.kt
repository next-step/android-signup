package nextstep.signup.validator

sealed interface Validator {
    fun checkCondition(value: String): ValidateResult

    data object Username : Validator {

        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private val regex by lazy { Regex(USERNAME_REGEX) }

        override fun checkCondition(value: String): ValidateResult {

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
            return when (value.matches(regex)) {
                true -> ValidateResult.SUCCESS
                false -> ValidateResult.INVALID_FORMAT_EMAIL
            }
        }
    }

}
