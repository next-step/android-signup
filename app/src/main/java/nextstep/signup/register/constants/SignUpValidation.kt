package nextstep.signup.register.constants

object SignUpValidation {

    const val USER_LENGTH_REGEX = "^.{2,5}$"
    const val PASSWORD_LENGTH_REGEX = "^.{8,16}$"

    const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    const val PASSWORD_REGEX = "^(?=.*[a-zA-Z가-힣])(?=.*[0-9]).*$"
}