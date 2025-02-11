package nextstep.signup

import nextstep.signup.register.SignUpValidation
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class InputValidationTest {

    @Test
    fun should_be_ture_when_username_length_2_5() {
        assertFalse(SignUpValidation.isValidUserName("z"))
        assertTrue(SignUpValidation.isValidUserName("aa"))
        assertTrue(SignUpValidation.isValidUserName("cde"))
        assertTrue(SignUpValidation.isValidUserName("fghi"))
        assertTrue(SignUpValidation.isValidUserName("jklmn"))
        assertFalse(SignUpValidation.isValidUserName("jklmnaf"))
    }

    @Test
    fun should_be_ture_when_username_not_contain_digit_or_symbol() {
        assertFalse(SignUpValidation.isValidUserName("aa21"))
        assertFalse(SignUpValidation.isValidUserName("cde!"))
        assertFalse(SignUpValidation.isValidUserName("fg5hi"))
        assertTrue(SignUpValidation.isValidUserName("jklmn"))
    }

    @Test
    fun should_be_ture_when_email_format() {
        assertFalse(SignUpValidation.isValidEmail("abew1@"))
        assertFalse(SignUpValidation.isValidEmail("abew1@naver."))
        assertTrue(SignUpValidation.isValidEmail("abew1@naver.com"))
        assertFalse(SignUpValidation.isValidEmail("abew1@naver.c"))
    }

    @Test
    fun should_be_ture_when_password_length_8_16(){
        assertFalse(SignUpValidation.isValidPassword("abe123!"))
        assertTrue(SignUpValidation.isValidPassword("e12@abce"))
        assertTrue(SignUpValidation.isValidPassword("qweftew124asdfxa"))
        assertFalse(SignUpValidation.isValidPassword("!qweftew124asdfxa"))
    }

    @Test
    fun should_be_ture_when_password_contain_english_and_digit() {
        assertFalse(SignUpValidation.isValidPassword("12341234"))
        assertFalse(SignUpValidation.isValidPassword("asdfqwerasdfqwer"))
        assertTrue(SignUpValidation.isValidPassword("2345asdf"))
        assertTrue(SignUpValidation.isValidPassword("fe!a124das"))
    }

    @Test
    fun should_be_ture_when_password_confirm_equals_password() {
        assertFalse(SignUpValidation.isValidPasswordConfirm("asdf1234", "asdf123"))
        assertTrue(SignUpValidation.isValidPasswordConfirm("asdf1234", "asdf1234"))
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