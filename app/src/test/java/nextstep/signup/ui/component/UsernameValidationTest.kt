package nextstep.signup.ui.component

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class UsernameValidationTest {
    @Test
    fun 유저_이름이_2자_미만일_때_유효성_검사에_실패한다() {
        // given
        val usernameValidation = UsernameValidation("a")

        // when
        val result = usernameValidation.isValid()

        // then
        assertFalse(result)
    }

    @Test
    fun 유저_이름이_6자_이상일_때_유효성_검사에_실패한다() {
        // given
        val usernameValidation = UsernameValidation("abcdef")

        // when
        val result = usernameValidation.isValid()

        // then
        assertFalse(result)
    }

    @Test
    fun 유저_이름에_특수문자가_포함되어_있을_때_유효성_검사에_실패한다() {
        // given
        val usernameValidation = UsernameValidation("a!@#")

        // when
        val result = usernameValidation.isValid()

        // then
        assertFalse(result)
    }

    @Test
    fun 유저_이름이_2자_이상_5자_이하이고_특수문자가_포함되어_있지_않을_때_유효성_검사에_성공한다() {
        // given
        val usernameValidation = UsernameValidation("abcde")

        // when
        val result = usernameValidation.isValid()

        // then
        assertTrue(result)
    }
}
