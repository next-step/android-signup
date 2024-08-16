package nextstep.signup.domain

import org.junit.Assert
import org.junit.Test

class UsernameValidatorTest {

    @Test
    fun 사용자_이름이_유효하면_VALID를_반환한다() {
        // given
        val username = "jihoi"

        // when
        val result = UsernameValidator.match(username)

        // then
        Assert.assertEquals(result, UsernameValidType.VALID)
    }

    @Test
    fun 사용자_이름이_2글자_미만이면_INVALID_LENGTH를_반환한다() {
        // given
        val username = "j"

        // when
        val result = UsernameValidator.match(username)

        // then
        Assert.assertEquals(result, UsernameValidType.INVALID_USERNAME_LENGTH)
    }

    @Test
    fun 사용자_이름이_5글자_초과하면_INVALID_LENGTH를_반환한다() {
        // given
        val username = "jihoia"

        // when
        val result = UsernameValidator.match(username)

        // then
        Assert.assertEquals(result, UsernameValidType.INVALID_USERNAME_LENGTH)
    }

    @Test
    fun 사용자_이름에_숫자가_포함되면_INVALID_REGEX를_반환한다() {
        // given
        val username = "jay1"

        // when
        val result = UsernameValidator.match(username)

        // then
        Assert.assertEquals(result, UsernameValidType.INVALID_REGEX)
    }

    @Test
    fun 사용자_이름에_기호가_포함되면_INVALID_REGEX를_반환한다() {
        // given
        val username = "jay@"

        // when
        val result = UsernameValidator.match(username)

        // then
        Assert.assertEquals(result, UsernameValidType.INVALID_REGEX)
    }

}