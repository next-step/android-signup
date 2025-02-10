package nextstep.signup.signupvalidation

class SignUpValidNone : (String) -> SignUpInvalidType? {
    override fun invoke(input: String): SignUpInvalidType? {
        return null
    }
}
