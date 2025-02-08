package nextstep.signup.signupvalidation

class ValidUsername : (String) -> SignUpInvalidType? {
    override fun invoke(input: String): SignUpInvalidType? {
        return when {
            input.length !in MIN_NAME_LENGTH..MAX_NAME_LENGTH -> SignUpInvalidType.INVALID_USER_NAME_LENGTH
            input.all { it.isLetter() }.not() -> SignUpInvalidType.INVALID_USER_NAME_LETTER
            else -> null
        }
    }

    companion object {
        private const val MIN_NAME_LENGTH = 2
        private const val MAX_NAME_LENGTH = 5
    }
}
