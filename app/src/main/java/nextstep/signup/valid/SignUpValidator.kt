package nextstep.signup.valid

import nextstep.signup.model.EmailState
import nextstep.signup.model.PasswordConfirmState
import nextstep.signup.model.PasswordState
import nextstep.signup.model.UserNameState

interface SignUpValidator {
    fun validateUsername(username: String): UserNameState
    fun validateEmail(email: String): EmailState
    fun validatePassword(password: String): PasswordState
    fun validatePasswordConfirm(password: String, passwordConfirm: String): PasswordConfirmState
}