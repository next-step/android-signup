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
    ),
    PASSWORD_LENGTH_INVALIDATION(
        "비밀번호는 8~16자여야 합니다."
    ),
    PASSWORD_RULE_INVALIDATION(
        "비밀번호는 영문과 숫자를 포함해야 합니다."
    ),
}
