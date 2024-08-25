package nextstep.signup.validation

sealed interface InputValidation {
    val regex: String

    fun checkValidation(inputText: String): String
    fun checkLength(inputText: String): Boolean

    data class UserNameValidation(
        val validationErrorText: String,
        val lengthErrorText: String
    ): InputValidation {
        override val regex: String
            get() = "^[a-zA-Z가-힣]+$"

        override fun checkValidation(inputText: String): String {
            return if (inputText.isEmpty()) {
                ""
            } else if (!inputText.matches(Regex(regex))) {
                validationErrorText
            } else if (checkLength(inputText)) {
                lengthErrorText
            } else {
                ""
            }
        }

        override fun checkLength(inputText: String): Boolean {
            return inputText.length !in 2..5
        }
    }

    data class EmailValidation(
        val validationErrorText: String,
    ): InputValidation {
        override val regex: String
            get() = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$"

        override fun checkValidation(inputText: String): String {
            return if (inputText.isEmpty()) {
                ""
            } else if (!inputText.matches(Regex(regex))) {
                validationErrorText
            } else {
                ""
            }
        }

        override fun checkLength(inputText: String): Boolean {
            return false
        }
    }

    data class PasswordValidation(
        val validationErrorText: String,
        val lengthErrorText: String
    ): InputValidation {
        override val regex: String
            get() = "^(?=.*[a-zA-Z])(?=.*[0-9]).*\$"

        override fun checkValidation(inputText: String): String {
            return if (inputText.isEmpty()) {
                ""
            } else if (!inputText.matches(Regex(regex))) {
                validationErrorText
            } else if (checkLength(inputText)) {
                lengthErrorText
            } else {
                ""
            }
        }

        override fun checkLength(inputText: String): Boolean {
            return inputText.length !in 8..16
        }
    }

    data class PasswordConfirmValidation(
        val passwordText: String,
        val validationErrorText: String,
    ): InputValidation {
        override val regex: String
            get() = ""

        override fun checkValidation(inputText: String): String {
            return if (inputText.isEmpty()) {
                ""
            } else {
                if (passwordText == inputText) {
                    ""
                } else {
                    validationErrorText
                }
            }
        }

        override fun checkLength(inputText: String): Boolean {
            return false
        }
    }
}
