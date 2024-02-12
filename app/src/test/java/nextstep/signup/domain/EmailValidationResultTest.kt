package nextstep.signup.domain

import org.junit.Test

class EmailValidationResultTest {

    @Test
    fun 이메일_형식이_올바르면_유효하다() {
        // given
        val email = "test@test.com"

        // when
        val actual = EmailValidationResult.match(email)

        // then
        assert(actual == EmailValidationResult.SUCCESS)
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_유효하지_않다() {
        // given
        val email = "이메일"

        // when
        val actual = EmailValidationResult.match(email)

        // then
        assert(actual == EmailValidationResult.INVALID_FORMAT)
    }
}
