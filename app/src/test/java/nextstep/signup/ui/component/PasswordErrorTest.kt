package nextstep.signup.ui.component

import org.junit.Test

class PasswordErrorTest {
    @Test
    fun 패스워드가_8자보다_작으면_에러가_발생한다() {
        // given
        val password = "1234567"

        // when
        val actual = PasswordError.checkBy(password = password)

        // then
        assert(value = actual == PasswordError.OUT_OF_RANGE_LENGTH)
    }

    @Test
    fun 패스워드가_16자보다_크면_에러가_발생한다() {
        // given
        val password = "12345678901234567"

        // when
        val actual = PasswordError.checkBy(password = password)

        // then
        assert(value = actual == PasswordError.OUT_OF_RANGE_LENGTH)
    }

    @Test
    fun 비밀번호는_영어와_숫자가_포함되지_않으면_에러가_발생한다() {
        // given
        val passwordList = listOf(
            "qwerqwer",
            "12341234"
        )

        // when
        val actualList = passwordList.map { password ->
            PasswordError.checkBy(password = password)
        }

        // then
        actualList.forEach { actual ->
            assert(value = actual == PasswordError.CANNOT_CONTAIN_ENGLISH_AND_NUMBERS)
        }
    }

    @Test
    fun 비밀번호가_유효하면_에러가_발생하지_않습니다() {
        // given
        val password = "wisemuji1234"

        // when
        val actual = PasswordError.checkBy(password = password)

        // then
        assert(value = actual == PasswordError.NONE)
    }
}
