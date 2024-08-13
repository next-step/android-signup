package nextstep.signup.util

/**
 * 유효성 검사관련 기능 정의 인터 페이스
**/
interface SignUpInputValidator {
    fun getValidState(inputString: String): SignUpValidSate
}

/**
 * 유효성 검사 결과 상태 정의
**/
enum class SignUpValidSate{
    ERROR_USER_NAME_LENGTH,
    ERROR_USER_NAME_REGEX,
    ERROR_EMAIL_REGEX,
    ERROR_PASSWORD_LENGTH,
    ERROR_PASSWORD_REGEX,
    ERROR_PASSWORD_CONFIRM,
    VALID,
    NOTHING
}

/**
 * Create Date: 2024. 8. 14.
 *
 * 사용자 이름 유효성 검사
 *
 * - 이름은 2~5자여야 합니다.
 * - 이름에는 숫자나 기호가 포함될 수 없습니다.
 *
 * @author LeeDongHun
 *
 *
 **/
class SignUpUserNameValidator : SignUpInputValidator {
    private val USERNAME_REGEX = Regex("^[a-zA-Z가-힣]+$")
    private val USER_NAME_MIN_LENGTH = 2
    private val USER_NAME_MXN_LENGTH = 5

    /**
     * 사용자 이름이 정의한 정규식에 맞는지 확인
     * @return 조건에 따라 [SignUpValidSate] 반환
     **/
    override fun getValidState(inputString: String): SignUpValidSate {
        return when {
            inputString.isEmpty()-> SignUpValidSate.NOTHING
            inputString.length !in USER_NAME_MIN_LENGTH..USER_NAME_MXN_LENGTH -> SignUpValidSate.ERROR_USER_NAME_LENGTH
            !inputString.matches(USERNAME_REGEX) -> SignUpValidSate.ERROR_USER_NAME_REGEX
            else -> SignUpValidSate.VALID
        }
    }
}

/**
 * Create Date: 2024. 8. 14.
 *
 * 이메일 유효성 검사
 * @author LeeDongHun
 *
 *
 **/
class SignUpEmailValidator : SignUpInputValidator {
    private val EMAIL_REGEX = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")

    /**
     * 이메일이 정의한 정규식에 맞는지 확인
     * @return 조건에 따라 [SignUpValidSate] 반환
     **/
    override fun getValidState(inputString: String): SignUpValidSate {
        return when {
            inputString.isEmpty()-> SignUpValidSate.NOTHING
            !inputString.matches(EMAIL_REGEX) -> SignUpValidSate.ERROR_EMAIL_REGEX
            else -> SignUpValidSate.VALID
        }
    }
}


/**
 * Create Date: 2024. 8. 14.
 *
 * 패스워드 유효성 검사
 * - 최소 8자리 이상  16자리 미만
 * - 영문 대소문자, 숫자, 특수문자 포함
 * - 패스워드 확인과 일치
 *
 * @author LeeDongHun
 *
 *
 **/
class SignUpPasswordValidator() : SignUpInputValidator {
    private val PASSWORD_REGEX = Regex("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$")
    private val PASSWORD_MIN_LENGTH = 8
    private val PASSWORD_MAN_LENGTH = 16

    /**
     * 패스워드가 정의한 정규식에 맞는지 확인
     * @return 조건에 따라 [SignUpValidSate] 반환
     **/
    override fun getValidState(inputString: String): SignUpValidSate {
        return when {
            inputString.isEmpty()-> SignUpValidSate.NOTHING
            inputString.length !in PASSWORD_MIN_LENGTH..PASSWORD_MAN_LENGTH -> SignUpValidSate.ERROR_PASSWORD_LENGTH
            !inputString.matches(PASSWORD_REGEX) -> SignUpValidSate.ERROR_PASSWORD_REGEX
            else -> SignUpValidSate.VALID
        }
    }
}

/**
 * Create Date: 2024. 8. 14.
 *
 * 패스워드 확인 유효성 검사
 * - 패스워드와 동일한지 확인
 *
 * @author LeeDongHun
 *
 *
 **/
class SignUpPasswordConfirmValidator(private val password: String) : SignUpInputValidator {

    /**
     * 패스워드 확인이 패스워드와 동일한지 확인
     * @return 조건에 따라 [SignUpValidSate] 반환
     **/
    override fun getValidState(inputString: String): SignUpValidSate {
        return when {
            inputString.isEmpty()-> SignUpValidSate.NOTHING
            inputString != password -> SignUpValidSate.ERROR_PASSWORD_CONFIRM
            else -> SignUpValidSate.VALID
        }
    }
    }