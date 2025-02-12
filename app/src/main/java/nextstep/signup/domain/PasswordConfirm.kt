package nextstep.signup.domain

data class PasswordConfirm(val password: String = "", val value: String = ""): InputField {
    override fun validate(): ValidationResult {
        return when {
            value.isEmpty() -> ValidationResult.EmptyValue
            value != password -> ValidationResult.PasswordConfirm.NOT_SAME
            else -> ValidationResult.Valid
        }
    }
}
