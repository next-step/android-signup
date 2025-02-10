package nextstep.signup

class SignUpTextFieldValidation {
    fun getUsernameValidationMessage(username: String): String {
        return if (username.length !in 2..6) {
            "이름은 2~5자여야 합니다."
        } else {
            if (!username.matches(Regex(USERNAME_REGEX))) {
                "이름에는 숫자나 기호가 포함될 수 없습니다."
            } else {
                ""
            }
        }
    }

    fun getEmailValidationMessage(email: String): String {
        return if (!email.matches(Regex(EMAIL_REGEX))) {
            "이메일 형식이 올바르지 않습니다."
        } else {
            ""
        }
    }

    fun getPasswordValidationMessage(password: String): String {
        return if (password.length !in 8..16) {
            "비밀번호는 8~16자여야 합니다."
        } else {
            if (!password.matches(Regex(PASSWORD_REGEX))) {
                "비밀번호는 영문과 숫자를 포함해야 합니다."
            } else {
                ""
            }
        }
    }

    fun getPasswordConfirmValidationMessage(password: String, passwordConfirm: String): String {
        return if (password == passwordConfirm) {
            ""
        } else {
            "비밀번호가 일치하지 않습니다."
        }
    }
}