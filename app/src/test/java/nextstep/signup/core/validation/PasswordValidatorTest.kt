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
        assertEquals(result, PasswordValidationResult.VALID)
    }

    @Test
    fun `비밀번호가 8자 미만이면 실패해야 한다`() {
        val result = validator.validate("pass1")
        assertEquals(result, PasswordValidationResult.INVALID_LENGTH)
    }

    @Test
    fun `숫자가 포함되지 않은 비밀번호는 실패해야 한다`() {
        val result = validator.validate("password")
        assertEquals(result, PasswordValidationResult.INVALID_COMPLEXITY)
    }

    @Test
    fun `문자가 포함되지 않은 비밀번호는 실패해야 한다`() {
        val result = validator.validate("12345678")
        assertEquals(result, PasswordValidationResult.INVALID_COMPLEXITY)
    }
}
