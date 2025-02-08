package nextstep.signup.signupvalidation

import android.util.Patterns

class SignUpValidEmail : (String) -> SignUpInvalidType? {
    override fun invoke(input: String): SignUpInvalidType? {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(input).matches() -> SignUpInvalidType.INVALID_EMAIL_FORMAT
            else -> null
        }
    }
}
