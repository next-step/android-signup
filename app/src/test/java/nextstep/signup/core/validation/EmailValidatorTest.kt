@file:Suppress("NonAsciiCharacters")

package nextstep.signup.core.validation

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class EmailValidatorTest {

    private val validator = EmailValidator()

    @Test
    fun `유효한 이메일은 통과해야 한다`() {
        val result = validator.validate("john.doe@example.com")
        assertTrue(result.isValid)
    }

    @Test
    fun `@없이 유효하지 않은 이메일은 실패해야 한다`() {
        val result = validator.validate("johndoe.example.com")
        assertFalse(result.isValid)
        assertEquals("이메일 형식이 올바르지 않습니다.", result.message)
    }

    @Test
    fun `도메인 없이 유효하지 않은 이메일은 실패해야 한다`() {
        val result = validator.validate("john.doe@")
        assertFalse(result.isValid)
        assertEquals("이메일 형식이 올바르지 않습니다.", result.message)
    }
}
