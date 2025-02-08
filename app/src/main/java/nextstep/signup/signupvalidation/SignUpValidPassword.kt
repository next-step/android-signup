package nextstep.signup.signupvalidation

class SignUpValidPassword : (String) -> SignUpInvalidType? {
    override fun invoke(input: String): SignUpInvalidType? {
        return when {
            input.length !in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH
                -> SignUpInvalidType.INVALID_PASSWORD_LENGTH

            !(input.any { it in 'A'..'Z' || it in 'a'..'z' } && input.any { it.isDigit() }) ->
                SignUpInvalidType.INVALID_PASSWORD_LETTER

            else -> null
        }
    }

    companion object {
        private const val MIN_PASSWORD_LENGTH = 8
        private const val MAX_PASSWORD_LENGTH = 16
    }
}
