package nextstep.signup.domain

data class PasswordConfirm(val password: String = "", val value: String = ""): InputField {
    override fun validate(): ValidationResult {
        return when {
            value.isEmpty() -> ValidationResult.EMPTY_VALUE
            value != password -> ValidationResult.NOT_SAME
            else -> ValidationResult.VALID
        }
    }
}
