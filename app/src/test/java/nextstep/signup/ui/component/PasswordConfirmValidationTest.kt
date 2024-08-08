package nextstep.signup.ui.component

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PasswordConfirmValidationTest {
    @Test
    fun `비밀번호가 일치하면 true를 반환한다`() {
        // given
        val password = "password"
        val passwordConfirm = "password"

        // when
        val result = PasswordConfirmValidation(password, passwordConfirm).isValid()

        // then
        assertTrue(result)
    }

    @Test
    fun `비밀번호가 일치하지 않으면 false를 반환한다`() {
        // given
        val password = "password"
        val passwordConfirm = "password1"

        // when
        val result = PasswordConfirmValidation(password, passwordConfirm).isValid()

        // then
        assertFalse(result)
    }

    @Test
    fun `비밀번호가 비어있으면 false를 반환한다`() {
        // given
        val password = ""
        val passwordConfirm = ""

        // when
        val result = PasswordConfirmValidation(password, passwordConfirm).isValid()

        // then
        assertFalse(result)
    }

    @Test
    fun `비밀번호 확인이 비어있으면 false를 반환한다`() {
        // given
        val password = "password"
        val passwordConfirm = ""

        // when
        val result = PasswordConfirmValidation(password, passwordConfirm).isValid()

        // then
        assertFalse(result)
    }
}
