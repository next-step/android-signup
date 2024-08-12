@file:Suppress("NonAsciiCharacters")

package nextstep.signup.core.validation

import junit.framework.TestCase.assertEquals
import org.junit.Test

class EmailValidatorTest {

    private val validator = EmailValidator()

    @Test
    fun `유효한 이메일은 통과해야 한다`() {
        val result = validator.validate("lee.x@example.com")
        assertEquals(result, EmailValidationResult.VALID)
    }

    @Test
    fun `@없이 유효하지 않은 이메일은 실패해야 한다`() {
        val result = validator.validate("lee.example.com")
        assertEquals(result, EmailValidationResult.INVALID_FORMAT)
    }

    @Test
    fun `도메인 없이 유효하지 않은 이메일은 실패해야 한다`() {
        val result = validator.validate("lee.doe@")
        assertEquals(result, EmailValidationResult.INVALID_FORMAT)
    }
}
