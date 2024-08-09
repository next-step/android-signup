@file:Suppress("NonAsciiCharacters")

package nextstep.signup.core.validation

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import nextstep.signup.R

class PasswordValidatorTest {

    private val validator = PasswordValidator()

    @Test
    fun `유효한 비밀번호는 통과해야 한다`() {
        val result = validator.validate("password123")
        assertTrue(result.isValid)
    }

    @Test
    fun `비밀번호가 너무 짧으면 실패해야 한다`() {
        val result = validator.validate("pass1")
        assertFalse(result.isValid)
        assertEquals(R.string.signup_password_length_error, result.message)
    }

    @Test
    fun `숫자가 포함되지 않은 비밀번호는 실패해야 한다`() {
        val result = validator.validate("password")
        assertFalse(result.isValid)
        assertEquals(R.string.signup_password_complexity_error, result.message)
    }

    @Test
    fun `문자가 포함되지 않은 비밀번호는 실패해야 한다`() {
        val result = validator.validate("12345678")
        assertFalse(result.isValid)
        assertEquals(R.string.signup_password_complexity_error, result.message)
    }
}