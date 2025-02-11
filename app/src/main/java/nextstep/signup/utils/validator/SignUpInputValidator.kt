package nextstep.signup.utils.validator

import nextstep.signup.ui.model.SignUpInputModel

interface SignUpInputValidator {

    val validationRules: List<ValidationRule>

    fun value(inputModel: SignUpInputModel): String

    fun hasError(model: SignUpInputModel): Boolean {
        return state(model) == State.INVALID
    }

    fun isValid(model: SignUpInputModel): Boolean {
        return state(model) == State.VALID
    }

    fun state(model: SignUpInputModel): State {
        val value = value(model)

        if (value.isEmpty()) return State.NO_VALUE

        return if (validate(value) == null) State.VALID else State.INVALID
    }

    fun errorMessageResId(model: SignUpInputModel): Int? {
        val value = value(model)

        return validate(value)?.errorMessageResId
    }

    private fun validate(value: String) = validationRules.firstOrNull { !it.regex.matches(value) }

    fun errorType(model: SignUpInputModel) = validate(value(model))?.errorType
}

enum class State {
    VALID,
    INVALID,
    NO_VALUE,
}
