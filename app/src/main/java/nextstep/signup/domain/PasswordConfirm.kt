package nextstep.signup.domain

import nextstep.signup.domain.PasswordConfirmValidationResult.NOT_SAME
import nextstep.signup.domain.PasswordConfirmValidationResult.VALID


@JvmInline
value class PasswordConfirm(val value: String) {
    fun validate(password: String) : PasswordConfirmValidationResult {
        return when {
            value != password -> NOT_SAME
            else -> VALID
        }
    }
}

enum class PasswordConfirmValidationResult {
    VALID,
    NOT_SAME,
}
