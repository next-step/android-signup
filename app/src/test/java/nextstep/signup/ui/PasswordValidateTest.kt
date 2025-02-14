package nextstep.signup.ui

import org.junit.Assert
import org.junit.Test

class PasswordValidateTest {

    @Test
    fun `비밀번호는 영문과 숫자를 포함하고 8자에서 16자여야 한다`() {
        // given
        val password = "hyem1234"
        // when
        val result = validatePassword(password)
        // then
        Assert.assertTrue(result is ValidationState.None)
    }

    @Test
    fun `비밀번호에 영문이 포함되어 있지 않으면 에러를 리턴한다`() {
        // given
        val password = "12345678"
        // when
        val result = validatePassword(password)
        // then
        Assert.assertTrue(result is ValidationState.Error)
    }

    @Test
    fun `비밀번호에 숫자가 포함되어 있지 않으면 에러를 리턴한다`() {
        // given
        val password = "hyemdool"
        // when
        val result = validatePassword(password)
        // then
        Assert.assertTrue(result is ValidationState.Error)
    }

    @Test
    fun `비밀번호가 8자 미만이면 에러를 리턴한다`() {
        // given
        val password = "hyem123"
        // when
        val result = validatePassword(password)
        // then
        Assert.assertTrue(result is ValidationState.Error)
    }

    @Test
    fun `비밀번호가 16자 초과이면 에러를 리턴한다`() {
        // given
        val password = "hyemdooly01234567"
        // when
        val result = validatePassword(password)
        // then
        Assert.assertTrue(result is ValidationState.Error)
    }
}
