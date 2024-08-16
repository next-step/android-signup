package nextstep.signup.domain

import org.junit.Assert
import org.junit.Test

class PasswordConfirmValidatorTest {

    @Test
    fun 비밀번호_더블체크용_비밀번호가_일치하면_VALID를_반환한다() {
        // given
        val password = "aa120000"
        val passwordConfirm = "aa120000"

        // when
        val result = PasswordConfirmValidator.match(password, passwordConfirm)

        // then
        Assert.assertEquals(result, PasswordConfirmValidType.VALID)
    }

    @Test
    fun 비밀번호_더블체크용_비밀번호가_일치하지_않으면_INVALID_EQUAL을_반환한다() {
        // given
        val password = "aa120000"
        val passwordConfirm = "aa120001"

        // when
        val result = PasswordConfirmValidator.match(password, passwordConfirm)

        // then
        Assert.assertEquals(result, PasswordConfirmValidType.INVALID_NOT_EQUAL)
    }

}