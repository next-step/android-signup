package nextstep.signup

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import nextstep.signup.ui.utils.ValidateUtils
import org.junit.Test

class ValidationLogicTest {

    @Test
    fun 사용자_이름이_2에서_5자_사이면_유효하다() {
        assertTrue(ValidateUtils.isValidUsernameLength("김컴"))
        assertTrue(ValidateUtils.isValidUsernameLength("김컴포즈"))
    }

    @Test
    fun 사용자_이름이_2에서_5자_외이면_유효하지_않다() {
        assertFalse(ValidateUtils.isValidUsernameLength("김"))
        assertFalse(ValidateUtils.isValidUsernameLength("김컴포즈임당"))
    }

    @Test
    fun 사용자_이름에_숫자나_기호가_포함되지_않으면_유효하다() {
        assertTrue(ValidateUtils.isValidUsername("김컴포즈"))
    }

    @Test
    fun 사용자_이름에_숫자나_기호가_포함되면_유효하지_않다() {
        assertFalse(ValidateUtils.isValidUsername("김123"))
        assertFalse(ValidateUtils.isValidUsername("김!@#"))
        assertFalse(ValidateUtils.isValidUsername("김12#"))
    }

    @Test
    fun 이메일_형식이_올바르면_유효하다() {
        assertTrue(ValidateUtils.isValidEmail("test@test.com"))
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_유효하지_않다() {
        assertFalse(ValidateUtils.isValidEmail("test@com"))
        assertFalse(ValidateUtils.isValidEmail("test.com"))
        assertFalse(ValidateUtils.isValidEmail("test@.com"))
    }

    @Test
    fun 비밀번호가_8자_이상_16자_이하이면_유효하다() {
        assertTrue(ValidateUtils.isValidPasswordLength("qwer1234"))
        assertTrue(ValidateUtils.isValidPasswordLength("qwer123412341234"))
    }

    @Test
    fun 비밀번호가_8자_미만_또는_16자_초과이면_유효하지_않다() {
        assertFalse(ValidateUtils.isValidPasswordLength("qwer123"))
        assertFalse(ValidateUtils.isValidPasswordLength("qwer1234123412341"))
    }

    @Test
    fun 비밀번호에_영문과_숫자가_포함되면_유효하다() {
        assertTrue(ValidateUtils.isValidPassword("abcd1234"))
    }

    @Test
    fun 비밀번호에_영문과_숫자가_포함되지_않으면_유효하지_않다() {
        assertFalse(ValidateUtils.isValidPassword("qwerqwer"))
        assertFalse(ValidateUtils.isValidPassword("12341234"))
    }

    @Test
    fun 비밀번호_확인이_일치하면_유효하다() {
        assertTrue(ValidateUtils.isValidatePasswordConfirm("qwer1234", "qwer1234"))
    }

    @Test
    fun 비밀번호_확인이_일치하지_않으면_유효하지_않다() {
        assertFalse(ValidateUtils.isValidatePasswordConfirm("qwer12345", "qwer1234"))
        assertFalse(ValidateUtils.isValidatePasswordConfirm("qwer1234", "qwer12345"))
    }

    @Test
    fun 모든_유효성을_만족하면_유효하다() {
        assertTrue(
            ValidateUtils.isValidAll(
                username = "김컴포즈",
                email = "test@test.com",
                password = "qwer1234",
                confirmPassword = "qwer1234"
            )
        )
    }

    @Test
    fun 모든_유효성을_만족하지_않으면_유효하지_않다() {
        assertFalse(
            ValidateUtils.isValidAll(
                username = "김컴포즈",
                email = "test@testcom",
                password = "qwer1234",
                confirmPassword = "qwer1234"
            )
        )
    }
}