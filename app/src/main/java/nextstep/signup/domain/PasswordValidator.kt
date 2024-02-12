package nextstep.signup.domain

fun interface PasswordValidator {

    fun validate(inputPassword: String): Result

    enum class Result {
        Valid,
        OutOfLength,
        HasNotAlphabetAndNumber;
    }
}

class DefaultPasswordValidator : PasswordValidator {

    override fun validate(inputPassword: String): PasswordValidator.Result {
        return when {
            inputPassword.length !in PasswordLengthRange -> PasswordValidator.Result.OutOfLength
            !inputPassword.matches(PasswordRegex) -> PasswordValidator.Result.HasNotAlphabetAndNumber
            else -> PasswordValidator.Result.Valid
        }
    }

    companion object {
        private val PasswordLengthRange: IntRange = 8..16
        private val PasswordRegex: Regex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$".toRegex()
    }
}
