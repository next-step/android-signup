package nextstep.signup.model

sealed class TextFieldState {
    data object Valid : TextFieldState()
    data object InValid : TextFieldState()
    data object LengthError : TextFieldState()
    data object Default : TextFieldState()
}