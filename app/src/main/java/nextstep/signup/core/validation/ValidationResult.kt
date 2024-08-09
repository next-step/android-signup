package nextstep.signup.core.validation

import androidx.annotation.StringRes

data class ValidationResult(
    val isValid: Boolean,
    @StringRes val message: Int? = null
)
