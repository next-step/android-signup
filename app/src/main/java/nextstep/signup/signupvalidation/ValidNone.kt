package nextstep.signup.signupvalidation

class ValidNone : (String) -> SignUpInvalidType? {
    override fun invoke(input: String): SignUpInvalidType? {
        return null
    }
}
