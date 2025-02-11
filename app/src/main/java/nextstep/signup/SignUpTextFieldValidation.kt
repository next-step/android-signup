package nextstep.signup

class SignUpTextFieldValidation {
    private val usernameRegex = "^[a-zA-Z가-힣]+$"
    private val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    private val passwordRegex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

    fun getUsernameValidationMessage(username: String): String {
        if (username.isEmpty()) {
            return ""
        }

        return if (username.length !in 2..5) {
            "이름은 2~5자여야 합니다."
        } else {
            if (!username.matches(Regex(usernameRegex))) {
                "이름에는 숫자나 기호가 포함될 수 없습니다."
            } else {
                ""
            }
        }
    }

    fun getEmailValidationMessage(email: String): String {
        if (email.isEmpty()) {
            return ""
        }

        return if (!email.matches(Regex(emailRegex))) {
            "이메일 형식이 올바르지 않습니다."
        } else {
            ""
        }
    }

    fun getPasswordValidationMessage(password: String): String {
        if (password.isEmpty()) {
            return ""
        }

        return if (password.length !in 8..16) {
            "비밀번호는 8~16자여야 합니다."
        } else {
            if (!password.matches(Regex(passwordRegex))) {
                "비밀번호는 영문과 숫자를 포함해야 합니다."
            } else {
                ""
            }
        }
    }

    fun getPasswordConfirmValidationMessage(password: String, passwordConfirm: String): String {
        if (passwordConfirm.isEmpty()) {
            return ""
        }

        return if (password != passwordConfirm) {
            "비밀번호가 일치하지 않습니다."
        } else {
            ""
        }
    }
}