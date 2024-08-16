package nextstep.signup.domain

import org.junit.Assert
import org.junit.Test

class EmailValidatorTest {

    @Test
    fun 이메일값이_유효하면_VALID를_반환한다() {
        // given
        val email = "jihoi.kang@gmail.com"

        // when
        val result = EmailValidator.match(email)

        // then
        Assert.assertEquals(result, EmailValidType.VALID)
    }

    @Test
    fun 이메일값이_유효하지않으면_INVALID를_반환한다_CASE1() {
        // given
        val email = "jihoi.kang"

        // when
        val result = EmailValidator.match(email)

        // then
        Assert.assertEquals(result, EmailValidType.INVALID_EMAIL_FORMAT)
    }

    @Test
    fun 이메일값이_유효하지않으면_INVALID를_반환한다_CASE2() {
        // given
        val email = "jihoi.kang@gmail"

        // when
        val result = EmailValidator.match(email)

        // then
        Assert.assertEquals(result, EmailValidType.INVALID_EMAIL_FORMAT)
    }

}