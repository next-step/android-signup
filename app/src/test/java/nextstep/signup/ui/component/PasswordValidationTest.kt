package nextstep.signup.ui.component

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PasswordValidationTest {
    // 8자리 이상 16자리 이하 비밀번호 검증
    @Test
    fun `비밀번호가 8자리 이상 16자리 이하이며 영문과 숫자가 반드시 포함되어 있으면 true를 반환한다`() {
        // given
        val password = "ab123456789"

        // when
        val result = PasswordValidation(password).isValid()

        // then
        assertTrue(result)
    }

    @Test
    fun `비밀번호가 16자리 초과이면 false를 반환한다`() {
        // given
        val password = "12345678901234567"

        // when
        val result = PasswordValidation(password).isValid()

        // then
        assertFalse(result)
    }

    @Test
    fun `비밀번호가 8자리 미만이면 false를 반환한다`() {
        // given
        val password = "12345"

        // when
        val result = PasswordValidation(password).isValid()

        // then
        assertFalse(result)
    }

    @Test
    fun `비밀번호가 8자리 이상 16자리 이하 이지만 영문과 숫자가 반드시 포함되어 있지 않으면 false를 반환한다`() {
        // given
        val password = "123456789"

        // when
        val result = PasswordValidation(password).isValid()

        // then
        assertFalse(result)
    }

    @Test
    fun `비밀번호가 영문과 숫자가 반드시 포함되어 있지만 8자리 미만이면 false를 반환한다`() {
        // given
        val password = "ab12345"

        // when
        val result = PasswordValidation(password).isValid()

        // then
        assertFalse(result)
    }

    @Test
    fun `비밀번호가 영문과 숫자가 반드시 포함되어 있지만 16자리 초과이면 false를 반환한다`() {
        // given
        val password = "ab123456789abcdefg"

        // when
        val result = PasswordValidation(password).isValid()

        // then
        assertFalse(result)
    }

    @Test
    fun `비밀번호가 비어있으면 true를 반환한다`() {
        // given
        val password = ""

        // when
        val result = PasswordValidation(password).isValid()

        // then
        assertTrue(result)
    }
}
