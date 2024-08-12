@file:Suppress("NonAsciiCharacters")

package nextstep.signup.core.validation

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import nextstep.signup.R

class PasswordMatchValidatorTest {

    private val originalPassword = "securePassword123"
    private val validator = PasswordMatchValidator()

    @Test
    fun `일치하는 비밀번호는 통과해야 한다`() {
        val result = validator.validate("securePassword123", originalPassword)
        assertEquals(result, PasswordMatchValidationResult.VALID)
    }

    @Test
    fun `일치하지 않는 비밀번호는 실패해야 한다`() {
        val result = validator.validate("differentPassword", originalPassword)
        assertEquals(result, PasswordMatchValidationResult.MISMATCH)
    }
}
