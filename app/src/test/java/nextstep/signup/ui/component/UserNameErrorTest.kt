package nextstep.signup.ui.component

import org.junit.Test

class UserNameErrorTest {
    @Test
    fun 사용자_이름이_2글자_미만이면_에러가_발생한다() {
        // given
        val userName = "가"

        // when
        val actual = UserNameError.checkBy(userName = userName)

        // then
        assert(value = actual == UserNameError.OUT_OF_RANGE_LENGTH)
    }

    @Test
    fun 사용자_이름이_5글자_초과하면_에러가_발생한다() {
        // given
        val userName = "가나다라마바"

        // when
        val actual = UserNameError.checkBy(userName = userName)

        // then
        assert(value = actual == UserNameError.OUT_OF_RANGE_LENGTH)
    }

    @Test
    fun 이름에_숫자나_기호가_포함되면_에러가_발생한다() {
        // given
        val userNameList = listOf(
            "수현01",
            "수현)!"
        )

        // when
        val actualList = userNameList.map { userName ->
            UserNameError.checkBy(userName = userName)
        }

        // then
        actualList.forEach { actual ->
            assert(value = actual == UserNameError.CANNOT_CONTAIN_NUMBERS_OR_SYMBOLS)
        }
    }

    @Test
    fun 이름이_올바른_경우_에러가_발생하지_않습니다() {
        // given
        val userNameList = listOf(
            "김수현",
            "박재성",
            "서준수"
        )

        // when
        val actualList = userNameList.map { userName ->
            UserNameError.checkBy(userName = userName)
        }

        // then
        actualList.forEach { actual ->
            assert(value = actual == UserNameError.NONE)
        }
    }
}
