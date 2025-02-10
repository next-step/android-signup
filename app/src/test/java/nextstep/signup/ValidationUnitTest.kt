package nextstep.signup

import nextstep.signup.util.ValidationUtil.checkIsAllPassValidation
import nextstep.signup.util.ValidationUtil.setEmailErrorMessage
import nextstep.signup.util.ValidationUtil.setUsernameErrorMessage
import org.junit.Assert
import org.junit.Test

class ValidationUnitTest {

    @Test
    fun `유저_이름이_유효한_길이와_특수문자_미사용한_경우_유효성_검사`() {
        // given
        val userName = "abcde"

        // when
        val isValid = setUsernameErrorMessage(userName).isBlank()

        // then
        Assert.assertTrue(isValid)
    }

    @Test
    fun `유저_이름이_초과하는_경우_유효성_검사`() {
        // given
        val userName = "abcdefghijk"

        // when
        val isValid = setUsernameErrorMessage(userName).isBlank()

        // then
        Assert.assertFalse(isValid)
    }

    @Test
    fun `유저_이름에_특수문자를_사용하는_경우_유효성_검사`() {
        // given
        val userName = "abcd!"

        // when
        val isValid = setUsernameErrorMessage(userName).isBlank()

        // then
        Assert.assertFalse(isValid)
    }

    @Test
    fun `이메일이_유효한_형식일_경우_유효성_검사`() {
        // given
        val email = "abcde@naver.com"

        // when
        val isValid = setEmailErrorMessage(email).isBlank()

        // then
        Assert.assertTrue(isValid)
    }

    @Test
    fun `이메일이_유효한_형식이_아닐_경우_유효성_검사`() {
        // given
        val email = "abcde@naver"

        // when
        val isValid = setEmailErrorMessage(email).isBlank()

        // then
        Assert.assertFalse(isValid)
    }

    @Test
    fun `입력한_데이터가_모두_유효한_경우_유효성_검사_테스트`() {
        //given
        val userName = "abc"
        val email = "abcde@naver.com"
        val password = "abc12345"
        val passwordConfirm = "abc12345"

        // when
        val isPassed = checkIsAllPassValidation(userName, email, password, passwordConfirm)

        // then
        Assert.assertTrue(isPassed)
    }

    @Test
    fun `입력한_데이터가_하나라도_비어있는_경우_유효성_검사_테스트`() {
        //given
        val userName = ""
        val email = "abcde@naver.com"
        val password = "abc12345"
        val passwordConfirm = "abc12345"

        // when
        val isPassed = checkIsAllPassValidation(userName, email, password, passwordConfirm)

        // then
        Assert.assertFalse(isPassed)
    }

    @Test
    fun `입력한_데이터가_하나라도_유효하지_않은_경우_유효성_검사_테스트`() {
        //given
        val userName = "!!"
        val email = "abcde@naver.com"
        val password = "abc12345"
        val passwordConfirm = "abc12345"

        // when
        val isPassed = checkIsAllPassValidation(userName, email, password, passwordConfirm)

        // then
        Assert.assertFalse(isPassed)
    }
}
