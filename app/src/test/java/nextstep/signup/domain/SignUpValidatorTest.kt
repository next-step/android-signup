package nextstep.signup.domain

import nextstep.signup.model.SignUpParams
import org.junit.Assert
import org.junit.Test

class SignUpValidatorTest {

    @Test
    fun 모든값이_유효하면_true를_반환한다() {
        // given
        val params = SignUpParams(
            "jihoi",
            "jihoi.kang@gmail.com",
            "aa120000",
            "aa120000",
        )

        // when
        val condition = SignUpValidator.valid(params)

        // then
        Assert.assertEquals(condition, true)
    }


    @Test
    fun 사용자_이름이_유효하지_않으면_false를_반환한다() {
        // given
        val params = SignUpParams(
            "j",
            "jihoi.kang@gmail.com",
            "aa120000",
            "aa120000",
        )

        // when
        val condition = SignUpValidator.valid(params)

        // then
        Assert.assertEquals(condition, false)
    }

    @Test
    fun 이메일이_유효하지_않으면_false를_반환한다() {
        // given
        val params = SignUpParams(
            "jihoi",
            "jihoi.kang@",
            "aa120000",
            "aa120000",
        )

        // when
        val condition = SignUpValidator.valid(params)

        // then
        Assert.assertEquals(condition, false)
    }

    @Test
    fun 비밀번호가_유효하지_않으면_false를_반환한다() {
        // given
        val params = SignUpParams(
            "jihoi",
            "jihoi.kang@gmail.com",
            "11223344",
            "11223344",
        )

        // when
        val condition = SignUpValidator.valid(params)

        // then
        Assert.assertEquals(condition, false)
    }

    @Test
    fun 비밀번호가_일치하지_않으면_false를_반환한다() {
        // given
        val params = SignUpParams(
            "jihoi",
            "jihoi.kang@gmail.com",
            "aa120000",
            "aa120001",
        )

        // when
        val condition = SignUpValidator.valid(params)

        // then
        Assert.assertEquals(condition, false)
    }

}