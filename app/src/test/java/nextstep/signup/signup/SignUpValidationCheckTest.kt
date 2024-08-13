package nextstep.signup.signup

import nextstep.signup.ui.signup.SignUpValidation
import nextstep.signup.ui.util.SignUpValidationCheck
import org.junit.Test
import org.junit.Assert.*


class SignUpValidationCheckTest {

    /**
     * Username Test
     */
    @Test
    fun `validateUsername은 2자 이상 5자 이하일 경우 NONE을 리턴`() {
        val result = SignUpValidationCheck.validateUsername("kyu")
        assertEquals(SignUpValidation.NONE, result)
    }

    @Test
    fun `validateUsername은 2자 미만이거나 5자 초과일 경우 INVALID_LENGTH 리턴`() {
        val resultUnderTwo = SignUpValidationCheck.validateUsername("k")
        val resultUpperFive = SignUpValidationCheck.validateUsername("kyudong")
        assertEquals(SignUpValidation.INVALID_LENGTH, resultUnderTwo)
        assertEquals(SignUpValidation.INVALID_LENGTH, resultUpperFive)
    }

    @Test
    fun `validateUsername은 2자 이상 5자 이하면서 숫자나 기호가 포함되면 INVALID_CHARACTER 리턴`() {
        val result = SignUpValidationCheck.validateUsername("kyu3")
        assertEquals(SignUpValidation.INVALID_CHARACTER, result)
    }

    /**
     * Email Test
     */
    @Test
    fun `validateEmail은 이메일 형식이 맞는 경우 NONE 리턴`() {
        val result = SignUpValidationCheck.validateEmail("kyudong3@gmail.com")
        assertEquals(SignUpValidation.NONE, result)
    }

    @Test
    fun `validateEmail은 이메일 형식이 맞지 않는 경우 INVALID_EMAIL 리턴`() {
        val result = SignUpValidationCheck.validateEmail("kyudong3@gmail")
        assertEquals(SignUpValidation.INVALID_EMAIL, result)
    }

    /**
     * Password Test
     */
    @Test
    fun `validatePassword는 8자 이상 16자 이하이면서 영문과 숫자가 포함되는 경우 NONE을 리턴`() {
        val result = SignUpValidationCheck.validatePassword("abcd123456789")
        assertEquals(SignUpValidation.NONE, result)
    }

    @Test
    fun `validatePassword는 8자 미만이거나 16자 초과일 경우 INVALID_LENGTH 리턴`() {
        val resultUnderEight = SignUpValidationCheck.validatePassword("1234")
        val resultUpperSixteen = SignUpValidationCheck.validatePassword("012345678901234567")
        assertEquals(SignUpValidation.INVALID_LENGTH, resultUnderEight)
        assertEquals(SignUpValidation.INVALID_LENGTH, resultUpperSixteen)
    }

    @Test
    fun `validatePassword는 8자 이상 16자 이하이면서 영문과 숫자가 포함되지 않으면 INVALID_PASSWORD 리턴`() {
        val result = SignUpValidationCheck.validatePassword("1234567890")
        assertEquals(SignUpValidation.INVALID_PASSWORD, result)
    }

    /**
     * PasswordConfirm Test
     */
    @Test
    fun `validatePasswordConfirm은 password와 passwordConfirm이 같으면 NONE 리턴`() {
        val result = SignUpValidationCheck.validatePasswordConfirm("abcd1234", "abcd1234")
        assertEquals(SignUpValidation.NONE, result)
    }

    @Test
    fun `validatePasswordConfirm은 password와 passwordConfirm이 같지 않으면 INVALID_PASSWORD_MATCH 리턴`() {
        val result = SignUpValidationCheck.validatePasswordConfirm("abcd1234", "abcd12")
        assertEquals(SignUpValidation.INVALID_PASSWORD_MATCH, result)
    }
}
