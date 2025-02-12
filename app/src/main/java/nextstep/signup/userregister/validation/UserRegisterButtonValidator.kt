package nextstep.signup.userregister.validation

import nextstep.signup.UserRegisterState

object UserRegisterButtonValidator {

    fun checkValidation(state: UserRegisterState): Boolean {
        return InputValueValidator.UserName.checkValue(state.userName) == InputValueValidationResult.Success &&
                InputValueValidator.Email.checkValue(state.email) == InputValueValidationResult.Success &&
                InputValueValidator.Password.checkValue(state.password) == InputValueValidationResult.Success &&
                InputValueValidator.PasswordConfirm.checkValue(state.password, state.passwordConfirm) == InputValueValidationResult.Success
    }
}
