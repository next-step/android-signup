package nextstep.signup.model

internal interface ValidatableError {
    val isNone: Boolean
    val isBlank: Boolean
}

internal enum class NameError : ValidatableError {
    None,
    Blank,
    Length,
    NumberOrSymbol;

    override val isNone: Boolean
        get() = this == None

    override val isBlank: Boolean
        get() = this == Blank
}

internal enum class EmailError : ValidatableError {
    None,
    Blank,
    EmailFormat;

    override val isNone: Boolean
        get() = this == None

    override val isBlank: Boolean
        get() = this == Blank
}

internal enum class PasswordError : ValidatableError {
    None,
    Blank,
    PasswordLength,
    PasswordFormat;

    override val isNone: Boolean
        get() = this == None

    override val isBlank: Boolean
        get() = this == Blank
}

internal enum class PasswordConfirmError : ValidatableError {
    None,
    Blank,
    PasswordEqual;

    override val isNone: Boolean
        get() = this == None

    override val isBlank: Boolean
        get() = this == Blank
}
