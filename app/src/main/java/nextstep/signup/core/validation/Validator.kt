package nextstep.signup.core.validation

interface Validator<T> {
    fun validate(value: String): T
}
