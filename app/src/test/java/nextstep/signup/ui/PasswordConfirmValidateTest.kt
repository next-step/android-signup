package nextstep.signup.ui

import org.junit.Assert
import org.junit.Test

class PasswordConfirmValidateTest {

    @Test
    fun `비밀번호와 비밀번호 확인 값은 같아야 한다`() {
        // given
        val password = "hyem1234"
        val passwordConfirm = "hyem1234"
        // when
        val result = validatePasswordConfirm(password, passwordConfirm)
        // then
        Assert.assertTrue(result is ValidationState.Success)
    }

    @Test
    fun `비밀번호와 비밀번호 확인 값이 같지 않으면 에러를 리턴한다`() {
        // given
        val password = "hyem1234"
        val passwordConfirm = "1234hyem"
        // when
        val result = validatePasswordConfirm(password, passwordConfirm)
        // then
        Assert.assertTrue(result is ValidationState.Error)
    }
}
