package nextstep.signup.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SignUpValidatorTest {

    @Test
    fun `Username_빈_문자열로_검증시_NO_ERROR_반환`() {
        val username = SignUpValidator.Username("")
        val result = username.validate()
        assertEquals(SignUpErrorType.NO_ERROR, result)
    }

    @Test
    fun `Username_2자_미만일_때USERNAME_NO_ERRORR_반환`() {
        val username = SignUpValidator.Username("a")
        val result = username.validate()
        assertEquals(SignUpErrorType.USERNAME_LENGTH_ERROR, result)
    }

    @Test
    fun `Username_5자_초과일_때_USERNAME_LENGTH_ERROR_반환`() {
        val username = SignUpValidator.Username("abcdefghijk")
        val result = username.validate()
        assertEquals(SignUpErrorType.USERNAME_LENGTH_ERROR, result)
    }

    @Test
    fun `Username_숫자가_포함될_떄_USERNAME_FORMAT_ERROR_반환`() {
        val username = SignUpValidator.Username("abc1")
        val result = username.validate()
        assertEquals(SignUpErrorType.USERNAME_FORMAT_ERROR, result)
    }

    @Test
    fun `Username_기호가_포함될_떄_USERNAME_FORMAT_ERROR_반환`() {
        val username = SignUpValidator.Username("abc-")
        val result = username.validate()
        assertEquals(SignUpErrorType.USERNAME_FORMAT_ERROR, result)
    }

    @Test
    fun `Username_올바른_형식의_문자열로_검증시_NO_ERROR_반환`() {
        val username = SignUpValidator.Username("abc")
        val result = username.validate()
        assertEquals(SignUpErrorType.NO_ERROR, result)
    }

    @Test
    fun `Email_빈_문자열로_검증시_NO_ERROR_반환`() {
        val email = SignUpValidator.Email("")
        val result = email.validate()
        assertEquals(SignUpErrorType.NO_ERROR, result)
    }

    @Test
    fun `Email_잘못된_형식의_문자열로_검증시_EMAIL_ERROR_반환`() {
        val email = SignUpValidator.Email("invalid-email")
        val result = email.validate()
        assertEquals(SignUpErrorType.EMAIL_ERROR, result)
    }

    @Test
    fun `Email_올바른_형식의_문자열로_검증시_NO_ERROR_반환`() {
        val email = SignUpValidator.Email("test@example.com")
        val result = email.validate()
        assertEquals(SignUpErrorType.NO_ERROR, result)
    }

    @Test
    fun `Password_빈_문자열로_검증시_NO_ERROR_반환`() {
        val password = SignUpValidator.Password("")
        val result = password.validate()
        assertEquals(SignUpErrorType.NO_ERROR, result)
    }

    @Test
    fun `Password_8자_미만일 때_PASSWORD_LENGTH_ERROR_반환`() {
        val password = SignUpValidator.Password("a1")
        val result = password.validate()
        assertEquals(SignUpErrorType.PASSWORD_LENGTH_ERROR, result)
    }

    @Test
    fun `Password_16자_초과일_때PASSWORD_LENGTH_ERROR_반환`() {
        val password = SignUpValidator.Password("a1b2c3d4e5f6g7h8i9j0")
        val result = password.validate()
        assertEquals(SignUpErrorType.PASSWORD_LENGTH_ERROR, result)
    }

    @Test
    fun `Password_영문과_숫자를_포함하지_않을_때PASSWORD_FORMAT_ERROR_반환`() {
        val password = SignUpValidator.Password("abcdefgh")
        val result = password.validate()
        assertEquals(SignUpErrorType.PASSWORD_FORMAT_ERROR, result)
    }

    @Test
    fun `Password_올바른_형식의_문자열로_검증시_NO_ERROR_반환`() {
        val password = SignUpValidator.Password("abc12345")
        val result = password.validate()
        assertEquals(SignUpErrorType.NO_ERROR, result)
    }

    @Test
    fun `PasswordConfirm_빈_문자열로_검증시_NO_ERROR_반환`() {
        val passwordConfirm = SignUpValidator.PasswordConfirm("", "abc12345")
        val result = passwordConfirm.validate()
        assertEquals(SignUpErrorType.NO_ERROR, result)
    }

    @Test
    fun `PasswordConfirm_비밀번호_불일치시_PASSWORD_CONFIRM_ERROR_반환`() {
        val passwordConfirm = SignUpValidator.PasswordConfirm("abc12346", "abc12345")
        val result = passwordConfirm.validate()
        assertEquals(SignUpErrorType.PASSWORD_CONFIRM_ERROR, result)
    }

    @Test
    fun `PasswordConfirm_비밀번호_일치시_NO_ERROR_반환`() {
        val passwordConfirm = SignUpValidator.PasswordConfirm("abc12345", "abc12345")
        val result = passwordConfirm.validate()
        assertEquals(SignUpErrorType.NO_ERROR, result)
    }
}