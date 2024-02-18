package nextstep.signup.ui.component

import org.junit.Test

class SignUpEnableTest {
    @Test
    fun 값이_하나라도_비어있으면_비활성화됩니다() {
        // given

        // when
        val actualList = listOf(
            SignUpEnable.checkBy(
                userName = "",
                email = "wisemuji@woowahan.com",
                password = "qwer1234",
                confirm = "qwer1234"
            ),
            SignUpEnable.checkBy(
                userName = "wisem",
                email = "",
                password = "qwer1234",
                confirm = "qwer1234"
            ),
            SignUpEnable.checkBy(
                userName = "wisem",
                email = "wisemuji@woowahan.com",
                password = "",
                confirm = "qwer1234"
            ),
        )

        // then
        actualList.forEach { actual ->
            assert(value = actual.isEnabled.not())
        }
    }

    @Test
    fun 입력값이_유효하면_활성화됩니다() {
        // given

        // when
        val actual = SignUpEnable.checkBy(
            userName = "wisem",
            email = "wisemuji@woowahan.com",
            password = "qwer1234",
            confirm = "qwer1234"
        )

        // then
        assert(value = actual.isEnabled)
    }
}
