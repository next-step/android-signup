package nextstep.signup.model

sealed interface UserNameState {
    data object Initial : UserNameState
    data object Valid : UserNameState
    data class Invalid(val error: UserNameError) : UserNameState
}

sealed interface EmailState {
    data object Initial : EmailState
    data object Valid : EmailState
    data object Invalid : EmailState
}

sealed interface PasswordState {
    data object Initial : PasswordState
    data object Valid : PasswordState
    data class Invalid(val error: PasswordError) : PasswordState
}

sealed interface PasswordConfirmState {
    data object Initial : PasswordConfirmState
    data object Valid : PasswordConfirmState
    data object Mismatch : PasswordConfirmState
}

sealed interface UserNameError {
    data object Length : UserNameError
    data object InvalidCharacters : UserNameError
}

sealed interface PasswordError {
    data object Length : PasswordError
    data object Complexity : PasswordError
}