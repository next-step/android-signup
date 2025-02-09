package nextstep.signup.textfield

object SignUpConst {
    const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
    const val USERNAME_MIN_LENGTH = 2
    const val USERNAME_MAX_LENGTH = 5
    const val PASSWORD_MIN_LENGTH = 8
    const val PASSWORD_MAX_LENGTH = 16
}