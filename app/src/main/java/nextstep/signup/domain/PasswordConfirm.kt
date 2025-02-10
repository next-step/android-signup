package nextstep.signup.domain

data class PasswordConfirm(val password: Password, val value: String): InputField {
    override fun validate(): ValidationResult {
        return when {
            value != password.value -> ValidationResult.NOT_SAME
            else -> ValidationResult.VALID
        }
    }
}
