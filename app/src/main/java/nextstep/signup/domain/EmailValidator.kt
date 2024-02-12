package nextstep.signup.domain

fun interface EmailValidator {
    fun validate(inputEmail: String): Result

    enum class Result {
        Valid,
        InValid;
    }
}

class DefaultEmailValidator : EmailValidator {

    override fun validate(inputEmail: String): EmailValidator.Result {
        return when {
            inputEmail.matches(EmailRegex) -> EmailValidator.Result.Valid
            else -> EmailValidator.Result.InValid
        }
    }

    companion object {
        private val EmailRegex: Regex =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
    }
}
