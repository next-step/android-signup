package nextstep.signup.ui.signup

sealed interface SignUpAction {

    data class OnUsernameChange(val value: String): SignUpAction
    data class OnEmailChange(val value: String): SignUpAction
    data class OnPasswordChange(val value: String): SignUpAction
    data class OnPasswordConfirmChange(val value: String): SignUpAction
    data object OnSignUpClick: SignUpAction
    data object OnSignUpSuccessConsumed: SignUpAction
}
