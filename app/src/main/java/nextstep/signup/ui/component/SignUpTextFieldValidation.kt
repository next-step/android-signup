package nextstep.signup.ui.component

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import nextstep.signup.R

@Immutable
interface SignUpTextFieldValidation {
    var errorType: ValidationErrorType?

    fun isValid(): Boolean

    interface ValidationErrorType
}

data class UsernameValidation(
    private val value: String,
    private val regex: String = USERNAME_REGEX,
    private val lengthRange: IntRange = USERNAME_LENGTH_RANGE,
) : SignUpTextFieldValidation {

    enum class ErrorType(
        @StringRes resId: Int,
    ) : SignUpTextFieldValidation.ValidationErrorType {
        LENGTH(resId = R.string.error_username_length),
        FORMAT(resId = R.string.error_username_format),
    }

    companion object {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]*\$"
        private val USERNAME_LENGTH_RANGE = 2..5
    }

    override var errorType: SignUpTextFieldValidation.ValidationErrorType?
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }
}

@Composable
fun rememberSignUpTextFieldValidation(
    vararg values: Any,
    validation: () -> SignUpTextFieldValidation,
): SignUpTextFieldValidation =
    remember(values) {
        validation()
    }
