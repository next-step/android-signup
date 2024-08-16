package nextstep.signup.domain

import org.junit.Assert
import org.junit.Test

class PasswordValidatorTest {

    @Test
    fun 비밀번호가_유효하면_VALID를_반환한다() {
        // given
        val password = "aa120000"

        // when
        val result = PasswordValidator.match(password)

        // then
        Assert.assertEquals(result, PasswordValidType.VALID)
    }

    @Test
    fun 비밀번호가_8자리_미만이면_INVALID_LENGTH를_반환한다() {
        // given
        val password = "aa12000"

        // when
        val result = PasswordValidator.match(password)

        // then
        Assert.assertEquals(result, PasswordValidType.INVALID_PASSWORD_LENGTH)
    }

    @Test
    fun 비밀번호가_16자리_초과하면_INVALID_LENGTH를_반환한다() {
        // given
        val password = "aa120000aa120000a"

        // when
        val result = PasswordValidator.match(password)

        // then
        Assert.assertEquals(result, PasswordValidType.INVALID_PASSWORD_LENGTH)
    }

    @Test
    fun 비밀번호가_숫자로만_되어있으면_INVALID_REGEX를_반환한다() {
        // given
        val password = "11223344"

        // when
        val result = PasswordValidator.match(password)

        // then
        Assert.assertEquals(result, PasswordValidType.INVALID_PASSWORD_FORMAT)
    }

    @Test
    fun 비밀번호가_영문으로만_되어있으면_INVALID_REGEX를_반환한다() {
        // given
        val password = "aabbccdd"

        // when
        val result = PasswordValidator.match(password)

        // then
        Assert.assertEquals(result, PasswordValidType.INVALID_PASSWORD_FORMAT)
    }

}