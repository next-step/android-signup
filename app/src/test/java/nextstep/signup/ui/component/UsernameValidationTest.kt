package nextstep.signup.ui.component

import junit.framework.TestCase.assertEquals
import nextstep.signup.ui.component.UsernameValidation.UsernameValidationResult
import org.junit.Test

class UsernameValidationTest {
    @Test
    fun 유저_이름이_2자_미만일_때_길이_유효성_에러를_반환한다() {
        // given
        val usernameValidation = UsernameValidation()

        // when
        val result = usernameValidation.validate("a")

        // thena
        assertEquals(UsernameValidationResult.UsernameLengthError, result)
    }

    @Test
    fun 유저_이름이_6자_이상일_때_길이_유효성_에러를_반환한다() {
        // given
        val usernameValidation = UsernameValidation()

        // when
        val result = usernameValidation.validate("abcdef")

        // then
        assertEquals(UsernameValidationResult.UsernameLengthError, result)
    }

    @Test
    fun 유저_이름에_특수문자가_포함되어_있을_때_포맷_유효성_에러를_반환한다() {
        // given
        val usernameValidation = UsernameValidation()

        // when
        val result = usernameValidation.validate("a!@#")

        // then
        assertEquals(UsernameValidationResult.UsernameFormatError, result)
    }

    @Test
    fun 유저_이름이_2자_이상_5자_이하이고_특수문자가_포함되어_있지_않을_때_포맷_유효성_검사에_성공한다() {
        // given
        val usernameValidation = UsernameValidation()

        // when
        val result = usernameValidation.validate("abcde")

        // then
        assertEquals(UsernameValidationResult.Success, result)
    }
}
