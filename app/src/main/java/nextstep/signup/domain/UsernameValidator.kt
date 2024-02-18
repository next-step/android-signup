package nextstep.signup.domain

fun interface UsernameValidator {

    fun validate(inputUsername: String): Result

    enum class Result {
        Valid,
        OutOfLength,
        HasNumberOrSymbol;

        fun isValid(): Boolean = this == Valid
    }
}

class DefaultUsernameValidator : UsernameValidator {

    override fun validate(inputUsername: String): UsernameValidator.Result {
        return when {
            inputUsername.length !in UsernameLengthRange -> UsernameValidator.Result.OutOfLength
            !inputUsername.matches(UsernameRegex) -> UsernameValidator.Result.HasNumberOrSymbol
            else -> UsernameValidator.Result.Valid
        }
    }

    companion object {
        private val UsernameLengthRange: IntRange = 2..5
        private val UsernameRegex: Regex = "^[a-zA-Z가-힣]+$".toRegex()
    }
}
