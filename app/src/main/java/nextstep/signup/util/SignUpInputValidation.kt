package nextstep.signup.util

object SignUpInputValidation {
    const val USERNAME_LENGTH_REGEX = "^.{2,5}$"
    const val USERNAME_REGEX = "^[a-zA-Z가-힣]{2,5}$"
    const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    const val PASSWORD_LENGTH_REGEX = "^.{8,16}$"
    const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
}
