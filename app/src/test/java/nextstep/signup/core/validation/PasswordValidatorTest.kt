@file:Suppress("NonAsciiCharacters")

package nextstep.signup.core.validation

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

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
        assertEquals("비밀번호는 8~16자여야 합니다.", result.message)
    }

    @Test
    fun `숫자가 포함되지 않은 비밀번호는 실패해야 한다`() {
        val result = validator.validate("password")
        assertFalse(result.isValid)
        assertEquals("비밀번호는 영문과 숫자를 포함해야 합니다.", result.message)
    }

    @Test
    fun `문자가 포함되지 않은 비밀번호는 실패해야 한다`() {
        val result = validator.validate("12345678")
        assertFalse(result.isValid)
        assertEquals("비밀번호는 영문과 숫자를 포함해야 합니다.", result.message)
    }
}