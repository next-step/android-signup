package nextstep.signup

import nextstep.signup.register.SignUpValidation
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class InputValidationTest {

    @Test
    fun should_be_ture_when_username_length_2_5_and_not_contain_digit_or_symbol(){
        assertFalse(SignUpValidation.isValidUserName("z"))
        assertTrue(SignUpValidation.isValidUserName("aa"))
        assertFalse(SignUpValidation.isValidUserName("!!"))
        assertFalse(SignUpValidation.isValidUserName("a2"))
        assertFalse(SignUpValidation.isValidUserName("a%"))
        assertTrue(SignUpValidation.isValidUserName("cde"))
        assertTrue(SignUpValidation.isValidUserName("fghi"))
        assertTrue(SignUpValidation.isValidUserName("jklmn"))
        assertFalse(SignUpValidation.isValidUserName("jk!mn"))
        assertFalse(SignUpValidation.isValidUserName("qwerqw"))
    }

    @Test
    fun should_be_ture_when_email_format(){
        assertFalse(SignUpValidation.isValidEmail("abew1@"))
        assertFalse(SignUpValidation.isValidEmail("abew1@naver."))
        assertTrue(SignUpValidation.isValidEmail("abew1@naver.com"))
        assertFalse(SignUpValidation.isValidEmail("abew1@naver.c"))
    }

    @Test
    fun should_be_ture_when_password_length_8_16_and_contain_english_and_digit(){
        assertFalse(SignUpValidation.isValidPassword("1234abc"))
        assertFalse(SignUpValidation.isValidPassword("1234567891234abcd"))
        assertFalse(SignUpValidation.isValidPassword("asdf123"))
        assertFalse(SignUpValidation.isValidPassword("asdfqwerasdfq1234"))
        assertTrue(SignUpValidation.isValidPassword("2345asdf"))
        assertTrue(SignUpValidation.isValidPassword("234567891234abcd"))
    }

    @Test
    fun should_be_ture_when_password_confirm_equals_password() {
        assertFalse(SignUpValidation.isValidPasswordConfirm("asdf1234","asdf123"))
        assertTrue(SignUpValidation.isValidPasswordConfirm("asdf1234","asdf1234"))
    }


    @Test
    fun should_be_ture_when_all_valid() {
        assertTrue(
            SignUpValidation.isAllValid(
                userName = "abc",
                email = "duksung1234@naver.com",
                password = "asdf1234",
                passwordConfirm = "asdf1234"
            )
        )
        assertFalse(
            SignUpValidation.isAllValid(
                userName = "a",
                email = "duksung1234@naver.com",
                password = "asdf1234",
                passwordConfirm = "asdf1234"
            )
        )

        assertFalse(
            SignUpValidation.isAllValid(
                userName = "abac",
                email = "duksung1234@",
                password = "asdf1234",
                passwordConfirm = "asdf1234"
            )
        )

        assertFalse(
            SignUpValidation.isAllValid(
                userName = "abac",
                email = "duksung1234@naver.com",
                password = "asdf123",
                passwordConfirm = "asdf1234"
            )
        )

        assertFalse(
            SignUpValidation.isAllValid(
                userName = "abac",
                email = "duksung1234@naver.com",
                password = "asdf123asdf123asdf123asdf123",
                passwordConfirm = "asdf123asdf123asdf123asdf123"
            )
        )
    }
}