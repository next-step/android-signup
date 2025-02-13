package nextstep.signup.userregister.validation

sealed interface InputValueValidator {

    data object UserName : InputValueValidator {
        private const val USER_NAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USER_NAME_IN_SPECIAL_CHARS_ERROR = "이름에 특수문자가 들어갈 수 없습니다."

        private val withoutSpecialCharsRegex = "^[a-zA-Z가-힣0-9]+$".toRegex()
        private fun String.isWithoutSpecialChars() = matches(withoutSpecialCharsRegex)

        fun checkValue(userName: String) = when {
            userName.isBlank() -> InputValueValidationResult.Empty
            userName.length !in (2..5) -> InputValueValidationResult.Failure(USER_NAME_LENGTH_ERROR)
            userName.isWithoutSpecialChars().not() -> InputValueValidationResult.Failure(
                USER_NAME_IN_SPECIAL_CHARS_ERROR
            )

            else -> InputValueValidationResult.Success
        }
    }

    data object Email : InputValueValidator {
        private const val EMAIL_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."

        private val emailFormatRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
        private fun String.isEmailFormat() = matches(emailFormatRegex)

        fun checkValue(email: String) = when {
            email.isBlank() -> InputValueValidationResult.Empty
            email.isEmailFormat().not() -> InputValueValidationResult.Failure(EMAIL_FORMAT_ERROR)
            else -> InputValueValidationResult.Success
        }
    }

    data object Password : InputValueValidator {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."

        private val passwordFormatRegex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$".toRegex()
        private fun String.isPasswordFormat() = matches(passwordFormatRegex)

        fun checkValue(password: String) = when {
            password.isBlank() -> InputValueValidationResult.Empty
            password.length !in (8..16) -> InputValueValidationResult.Failure(PASSWORD_LENGTH_ERROR)
            password.isPasswordFormat().not() -> InputValueValidationResult.Failure(PASSWORD_FORMAT_ERROR)
            else -> InputValueValidationResult.Success
        }
    }

    data object PasswordConfirm : InputValueValidator {
        private const val PASSWORD_CONFIRM_NOT_SAME = "비밀번호가 일치하지 않습니다."

        fun checkValue(password: String, passwordConfirm: String) = when {
            passwordConfirm.isBlank() -> InputValueValidationResult.Empty
            password != passwordConfirm -> InputValueValidationResult.Failure(PASSWORD_CONFIRM_NOT_SAME)
            else -> InputValueValidationResult.Success
        }
    }
}
