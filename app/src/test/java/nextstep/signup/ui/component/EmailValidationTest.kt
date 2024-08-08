package nextstep.signup.ui.component

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class EmailValidationTest {
    @Test
    fun `이메일 형식이 올바르지 않으면 false를 반환한다`() {
        // given
        val email = "test"

        // when
        val result = EmailValidation(email).isValid()

        // then
        assertFalse(result)
    }

    @Test
    fun `이메일 형식이 올바르면 true를 반환한다`() {
        // given
        val email = "test@yopmail.com"

        // when
        val result = EmailValidation(email).isValid()

        // then
        assertTrue(result)
    }

    @Test
    fun `이메일 형식이 비어있으면 true를 반환한다`() {
        // given
        val email = ""

        // when
        val result = EmailValidation(email).isValid()

        // then
        assertTrue(result)
    }
}
