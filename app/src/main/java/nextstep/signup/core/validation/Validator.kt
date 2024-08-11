package nextstep.signup.core.validation

import androidx.compose.runtime.Stable

@Stable
interface Validator {
    fun validate(value: String): ValidationResult
}