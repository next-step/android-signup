package nextstep.signup.study

import nextstep.signup.util.SignUpEmailValidator
import nextstep.signup.util.SignUpPasswordConfirmValidator
import nextstep.signup.util.SignUpPasswordValidator
import nextstep.signup.util.SignUpUserNameValidator
import nextstep.signup.util.SignUpValidSate
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Create Date: 2024. 8. 17.
 *
 * Description: 회원가입 유효성 검사 테스트
 * @author LeeDongHun
 *
 * @see
 * */
class SignupValidatorTest {

    private val signUpUserNameValidator = SignUpUserNameValidator()
    private val signUpEmailValidator = SignUpEmailValidator()
    private val signUpPasswordValidator = SignUpPasswordValidator()
    private lateinit var signUpPasswordConfirmValidator: SignUpPasswordConfirmValidator

    @Before
    fun setUp() {
        //비밀번호 확인용 validator
        signUpPasswordConfirmValidator = SignUpPasswordConfirmValidator(password = "a12345678")

    }

    @Test
    fun `사용자_이름이_2에서_5자_사이가_아니면_ERROR_USER_NAME_LENGTH_상태_표출`() {
        val userNameValidState = signUpUserNameValidator.getValidState("a")
        Assert.assertTrue(userNameValidState == SignUpValidSate.ERROR_USER_NAME_LENGTH)
    }

    @Test
    fun `사용자_이름에_기호나_숫자가_포함되면_ERROR_USER_NAME_REGEX_상태_표출`() {
        val userNameValidState = signUpUserNameValidator.getValidState("ab123")
        Assert.assertTrue(userNameValidState == SignUpValidSate.ERROR_USER_NAME_REGEX)
    }

    @Test
    fun `이메일_형식이_아니면_ERROR_EMAIL_REGEX_상태_표출`() {
        val emailValidState = signUpEmailValidator.getValidState("test")
        Assert.assertTrue(emailValidState == SignUpValidSate.ERROR_EMAIL_REGEX)
    }


    @Test
    fun `비밀번호가_8에서_16자_사이가_아니면_ERROR_PASSWORD_LENGTH_상태_표출`() {
        val passwordValidState = signUpPasswordValidator.getValidState("a123456")
        Assert.assertTrue(passwordValidState == SignUpValidSate.ERROR_PASSWORD_LENGTH)
    }

    @Test
    fun `비밀번호가_영어_숫자_조합이_아니면_ERROR_PASSWORD_REGEX_상태_표출`() {
        val passwordValidState = signUpPasswordValidator.getValidState("12345678")
        Assert.assertTrue(passwordValidState == SignUpValidSate.ERROR_PASSWORD_REGEX)
    }

    @Test
    fun `비밀번호_확인이_비밀번호와_다르면_ERROR_PASSWORD_CONFIRM_상태_표출`() {
        val passwordConfirmValidState = signUpPasswordConfirmValidator.getValidState("a23456780")
        Assert.assertTrue(passwordConfirmValidState == SignUpValidSate.ERROR_PASSWORD_CONFIRM)
    }
}
