package nextstep.signup

sealed interface InputValidation {
    val regex: String

    fun checkValidation(inputText: String): Boolean
    fun checkLength(inputText: String): Boolean

    data object UserNameValidation: InputValidation {
        override val regex: String
            get() = "^[a-zA-Z가-힣]+$"

        override fun checkValidation(inputText: String): Boolean {
            return inputText.matches(Regex(regex))
//            return if (inputText.matches(Regex(regex))) {
//                "이름에는 숫자나 기호가 포함될 수 없습니다."
//            } else if (inputText.length !in 2..5) {
//                "이름은 2~5자여야 합니다."
//            } else {
//                ""
//            }
        }

        override fun checkLength(inputText: String): Boolean {
            return inputText.length !in 2..5
        }
    }

    data object EmailValidation: InputValidation {
        override val regex: String
            get() = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$"

        override fun checkValidation(inputText: String): Boolean {
            return inputText.matches(Regex(regex))
//            return if (inputText.matches(Regex(regex))) {
//                "이메일 형식이 올바르지 않습니다."
//            } else {
//                ""
//            }
        }

        override fun checkLength(inputText: String): Boolean {
            return false
        }
    }

    data object PasswordValidation: InputValidation {
        override val regex: String
            get() = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}\$"

        override fun checkValidation(inputText: String): Boolean {
            return inputText.matches(Regex(UserNameValidation.regex))
//            return if (inputText.matches(Regex(regex))) {
//                "비밀번호는 영문과 숫자를 포함해야 합니다."
//            } else if (inputText.length !in 8..16) {
//                "비밀번호는 8~16자여야 합니다."
//            } else {
//                ""
//            }
        }

        override fun checkLength(inputText: String): Boolean {
            return inputText.length !in 8..16
        }
    }
}