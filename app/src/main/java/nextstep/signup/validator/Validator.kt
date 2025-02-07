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
}
