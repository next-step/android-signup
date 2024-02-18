package nextstep.signup.ui.component

import org.junit.Test

class ConfirmErrorTest {
    @Test
    fun 일치하지_않으면_에러가_발생한다() {
        // given
        val password = "qwer1234"
        val passwordConfirm = "qwer1235"

        // when
        val actual = ConfirmError.checkBy(src = password, dst = passwordConfirm)

        // then
        assert(value = actual == ConfirmError.NOT_MATCH)
    }

    @Test
    fun 일치하면_에러가_발생하지_않습니다() {
        // given
        val password = "wisemuji1234"

        // when
        val actual = ConfirmError.checkBy(src = password, dst = password)

        // then
        assert(value = actual == ConfirmError.NONE)
    }
}
