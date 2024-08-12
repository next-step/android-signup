@file:Suppress("NonAsciiCharacters")

package nextstep.signup.core.validation

import junit.framework.TestCase.assertEquals
import org.junit.Test

class NameValidatorTest {

    private val validator = NameValidator()

    @Test
    fun `유효한 이름은 통과해야 한다`() {
        val result = validator.validate("John")
        assertEquals(result, NameValidationResult.VALID)
    }

    @Test
    fun `이름이 2자 이하이면 실패해야 한다`() {
        val result = validator.validate("J")
        assertEquals(result, NameValidationResult.LENGTH_ERROR)
    }

    @Test
    fun `이름이 6자 이상이면 실패해야 한다`() {
        val result = validator.validate("JohnDoe")
        assertEquals(result, NameValidationResult.LENGTH_ERROR)
    }

    @Test
    fun `이름에 숫자가 포함되면 실패해야 한다`() {
        val result = validator.validate("John1")
        assertEquals(result, NameValidationResult.CHARACTER_ERROR)
    }
}
