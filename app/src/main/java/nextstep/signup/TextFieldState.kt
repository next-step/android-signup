package nextstep.signup

sealed class TextFieldState {
    data object Valid : TextFieldState()
    data object InValid : TextFieldState()
    data object LengthError : TextFieldState()
}