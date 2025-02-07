package nextstep.signup.ui.signup

sealed interface SignUpAction {

    data class OnUsernameChanged(val value: String): SignUpAction
    data class OnEmailChanged(val value: String): SignUpAction
    data class OnPasswordChanged(val value: String): SignUpAction
    data class OnPasswordConfirmChanged(val value: String): SignUpAction
    data object OnSignUpClick: SignUpAction
    data object None: SignUpAction
}
