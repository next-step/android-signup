package nextstep.signup.ui.signup

enum class SignupInvalidationState(val message: String) {
    USERNAME_LENGTH_INVALIDATION(
        "이름은 2~5자여야 합니다."
    ),
    USERNAME_RULE_INVALIDATION(
        "이름에는 숫자나 기호가 포함될 수 없습니다."
    ),
    EMAIL_RULE_INVALIDATION(
        "이메일 형식이 올바르지 않습니다."
    )
}
