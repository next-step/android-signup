package nextstep.signup.ui

import org.junit.Assert
import org.junit.Test

class EmailValidateTest {

    @Test
    fun `이메일은 이메일 형식으로 이루어져야한다`() {
        // given
        val email = "hyemdooly@gmail.com"
        // when
        val result = validateEmail(email)
        // then
        Assert.assertTrue(result is ValidationState.None)
    }

    @Test
    fun `이메일이 형식에 맞지 않으면 에러를 리턴한다`() {
        // given
        val email = "h"
        // when
        val result = validateEmail(email)
        // then
        Assert.assertTrue(result is ValidationState.Error)
    }
}
