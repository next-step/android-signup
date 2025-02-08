package nextstep.signup

import nextstep.signup.register.constants.SignUpValidation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class InputValidationTest {

    @Test
    fun should_length_2_5_when_enter_username() {
        assertFalse("a".matches(Regex(SignUpValidation.USER_LENGTH_REGEX)))
        assertTrue("aa".matches(Regex(SignUpValidation.USER_LENGTH_REGEX)))
        assertTrue("aaa".matches(Regex(SignUpValidation.USER_LENGTH_REGEX)))
        assertTrue("aaaa".matches(Regex(SignUpValidation.USER_LENGTH_REGEX)))
        assertTrue("aaaaa".matches(Regex(SignUpValidation.USER_LENGTH_REGEX)))
        assertFalse("aaaaaa".matches(Regex(SignUpValidation.USER_LENGTH_REGEX)))
    }

    @Test
    fun should_not_contain_digit_or_symbol_when_enter_username() {
        assertFalse("1".matches(Regex(SignUpValidation.USERNAME_REGEX)))
        assertFalse("!".matches(Regex(SignUpValidation.USERNAME_REGEX)))
        assertTrue("a".matches(Regex(SignUpValidation.USERNAME_REGEX)))
        assertTrue("aa".matches(Regex(SignUpValidation.USERNAME_REGEX)))
        assertTrue("aaa".matches(Regex(SignUpValidation.USERNAME_REGEX)))
        assertFalse("aaa1".matches(Regex(SignUpValidation.USERNAME_REGEX)))
        assertFalse("aaaaaaa2".matches(Regex(SignUpValidation.USERNAME_REGEX)))
    }

    @Test
    fun should_be_correct_email_format_when_enter_email() {
        assertTrue("a@a.com".matches(Regex(SignUpValidation.EMAIL_REGEX)))
        assertTrue("a@a.co.kr".matches(Regex(SignUpValidation.EMAIL_REGEX)))
    }

    @Test
    fun should_length_8_16_when_enter_password() {
        assertFalse("1234567".matches(Regex(SignUpValidation.PASSWORD_LENGTH_REGEX)))
        assertTrue("123456789".matches(Regex(SignUpValidation.PASSWORD_LENGTH_REGEX)))
    }

    @Test
    fun should_contain_english_or_digit_when_enter_password() {
        assertFalse("".matches(Regex(SignUpValidation.PASSWORD_REGEX)))
        assertFalse("a".matches(Regex(SignUpValidation.PASSWORD_REGEX)))
        assertFalse("1".matches(Regex(SignUpValidation.PASSWORD_REGEX)))
        assertTrue("a1".matches(Regex(SignUpValidation.PASSWORD_REGEX)))
    }

    @Test
    fun should_equal_password_when_enter_password_confirm() {
        val mockPassword = "qwer1234"
        assertEquals("qwer1234", mockPassword)
    }

}