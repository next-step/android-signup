package nextstep.signup.util

import org.junit.Assert.*
import org.junit.Test

class SignUpInputValidationTest {

    @Test
    fun `이름은 2~5자 사이`() {
        assertTrue("석준".matches(Regex(SignUpInputValidation.USERNAME_LENGTH_REGEX)))
        assertTrue("정석준".matches(Regex(SignUpInputValidation.USERNAME_LENGTH_REGEX)))
        assertTrue("정석준디노".matches(Regex(SignUpInputValidation.USERNAME_LENGTH_REGEX)))
        assertFalse("정".matches(Regex(SignUpInputValidation.USERNAME_LENGTH_REGEX)))
        assertFalse("정석준정석준".matches(Regex(SignUpInputValidation.USERNAME_LENGTH_REGEX)))
    }

    @Test
    fun `이름은 영어대소문자와 한글로 구성되고 길이는 2~5자 사이`() {
        assertTrue("정석준".matches(Regex(SignUpInputValidation.USERNAME_REGEX)))
        assertTrue("dino".matches(Regex(SignUpInputValidation.USERNAME_REGEX)))
        assertTrue("dino정".matches(Regex(SignUpInputValidation.USERNAME_REGEX)))
        assertFalse("dino1".matches(Regex(SignUpInputValidation.USERNAME_REGEX)))
        assertFalse("dino123".matches(Regex(SignUpInputValidation.USERNAME_REGEX)))
        assertFalse("dino_jeong".matches(Regex(SignUpInputValidation.USERNAME_REGEX)))
        assertFalse("정1석2준".matches(Regex(SignUpInputValidation.USERNAME_REGEX)))
    }

    @Test
    fun `email validation`() {
        assertTrue("test@example.com".matches(Regex(SignUpInputValidation.EMAIL_REGEX)))
        assertTrue("sjjeong1225@gmail.com".matches(Regex(SignUpInputValidation.EMAIL_REGEX)))
        assertFalse("testexample.com".matches(Regex(SignUpInputValidation.EMAIL_REGEX)))
    }

    @Test
    fun `비밀번호 길이는 8~16자 사이`() {
        assertTrue("password".matches(Regex(SignUpInputValidation.PASSWORD_LENGTH_REGEX)))
        assertTrue("passwordpassword".matches(Regex(SignUpInputValidation.PASSWORD_LENGTH_REGEX)))
        assertFalse("passwor".matches(Regex(SignUpInputValidation.PASSWORD_LENGTH_REGEX)))
        assertFalse("".matches(Regex(SignUpInputValidation.PASSWORD_LENGTH_REGEX)))
        assertFalse("password123456789".matches(Regex(SignUpInputValidation.PASSWORD_LENGTH_REGEX)))
    }

    @Test
    fun `비밀번호는 영문과 숫자로 구성되고 길이는 8~16자 사이`() {
        assertTrue("password1".matches(Regex(SignUpInputValidation.PASSWORD_REGEX)))
        assertFalse("password".matches(Regex(SignUpInputValidation.PASSWORD_REGEX)))
    }
}
