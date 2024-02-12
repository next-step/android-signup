package nextstep.signup.domain

import org.junit.Assert.assertTrue
import org.junit.Test

class PasswordConfirmValidationResultTest {

    @Test
    fun 비밀번호_두개가_일치하면_유효하다() {
        // given
        val password = "1q2w3e4r!"
        val confirmPassword = "1q2w3e4r!"

        // when
        val actual = PasswordConfirmValidationResult.match(password, confirmPassword)

        // then
        assertTrue(actual == PasswordConfirmValidationResult.SUCCESS)
    }

    @Test
    fun 비밀번호_두개가_다르면_유효하지_않다() {
        // given
        val password = "12345678"
        val confirmPassword = "1234"

        // when
        val actual = PasswordConfirmValidationResult.match(password, confirmPassword)

        // then
        assertTrue(actual == PasswordConfirmValidationResult.INVALID)
    }
}
