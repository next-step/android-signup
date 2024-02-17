package nextstep.signup.ui.component

import org.junit.Test

class EmailErrorTest {
    @Test
    fun 이메일_형식이_올바르지_않으면_에러가_발생한다() {
        // given
        val emailList = listOf(
            "wisemuji!woowahan.com",
            "wisemuji#woowahan.com",
            "wisemuji%woowahan.com"
        )

        // when
        val actualList = emailList.map { email ->
            EmailError.checkBy(email = email)
        }

        // then
        actualList.forEach { actual ->
            assert(value = actual == EmailError.NOT_MATCH_FORMAT)
        }
    }

    @Test
    fun 이메일_형식이_올바른_경우_에러가_발생하지_않습니다() {
        // given
        val emailList = listOf(
            "wisemuji@woowahan.com",
            "wotjd243@woowahan.com"
        )

        // when
        val actualList = emailList.map { email ->
            EmailError.checkBy(email = email)
        }

        // then
        actualList.forEach { actual ->
            assert(value = actual == EmailError.NONE)
        }
    }
}
