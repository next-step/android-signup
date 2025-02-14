package nextstep.signup.ui

import org.junit.Assert
import org.junit.Test

class UsernameValidateTest {

    @Test
    fun `사용자 이름은 2자에서 5자이며, 문자로 이루어져야 한다`() {
        // given
        val username = "hyem"
        // when
        val result = validateUsername(username)
        // then
        Assert.assertTrue(result is ValidationState.None)
    }

    @Test
    fun `사용자 이름이 2자 미만이면 에러가 리턴된다`() {
        // given
        val username = "h"
        // when
        val result = validateUsername(username)
        // then
        Assert.assertTrue(result is ValidationState.Error)
    }

    @Test
    fun `사용자 이름이 5자 초과이면 에러가 리턴된다`() {
        // given
        val username = "hyemmm"
        // when
        val result = validateUsername(username)
        // then
        Assert.assertTrue(result is ValidationState.Error)
    }

    @Test
    fun `사용자 이름에 숫자가 포함되면 에러가 리턴된다`() {
        // given
        val username = "hy3m"
        // when
        val result = validateUsername(username)
        // then
        Assert.assertTrue(result is ValidationState.Error)
    }

    @Test
    fun `사용자 이름에 기호가 포함되면 에러가 리턴된다`() {
        // given
        val username = "hyem*"
        // when
        val result = validateUsername(username)
        // then
        Assert.assertTrue(result is ValidationState.Error)
    }
}
