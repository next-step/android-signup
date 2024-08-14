package nextstep.signup.domain

import nextstep.signup.model.SignUpParams

object SignUpValidator {

    fun valid(params: SignUpParams): Boolean =
        UsernameValidator.match(params.username) == UsernameValidType.VALID &&
                EmailValidator.match(params.email) == EmailValidType.VALID &&
                PasswordValidator.match(params.password) == PasswordValidType.VALID &&
                PasswordConfirmValidator.match(params.password, params.passwordConfirm) == PasswordConfirmValidType.VALID

}