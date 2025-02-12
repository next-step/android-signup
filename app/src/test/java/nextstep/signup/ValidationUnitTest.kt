package nextstep.signup

import nextstep.signup.userregister.validation.InputValueValidationResult
import nextstep.signup.userregister.validation.InputValueValidator
import nextstep.signup.userregister.validation.UserRegisterButtonValidator
import org.junit.Assert
import org.junit.Test

class ValidationUnitTest {

    @Test
    fun `유저_이름이_유효한_길이와_특수문자_미사용한_경우_유효성_검사`() {
        // given
        val userName = "abcde"

        // when
        val result = InputValueValidator.UserName.checkValue(userName)

        // then
        Assert.assertTrue(result == InputValueValidationResult.Success)
    }

    @Test
    fun `유저_이름이_초과하는_경우_유효성_검사`() {
        // given
        val userName = "abcdefghijk"

        // when
        val result = InputValueValidator.UserName.checkValue(userName)

        // then
        Assert.assertTrue(result == InputValueValidationResult.Failure("이름은 2~5자여야 합니다."))
    }

    @Test
    fun `유저_이름에_특수문자를_사용하는_경우_유효성_검사`() {
        // given
        val userName = "abcd!"

        // when
        val result = InputValueValidator.UserName.checkValue(userName)

        // then
        Assert.assertTrue(result == InputValueValidationResult.Failure("이름에 특수문자가 들어갈 수 없습니다."))
    }

    @Test
    fun `이메일이_유효한_형식일_경우_유효성_검사`() {
        // given
        val email = "abcde@naver.com"

        // when
        val result = InputValueValidator.Email.checkValue(email)

        // then
        Assert.assertTrue(result == InputValueValidationResult.Success)
    }

    @Test
    fun `이메일이_유효한_형식이_아닐_경우_유효성_검사`() {
        // given
        val email = "abcde@naver"

        // when
        val result = InputValueValidator.Email.checkValue(email)

        // then
        Assert.assertTrue(result == InputValueValidationResult.Failure("이메일 형식이 올바르지 않습니다."))
    }

    @Test
    fun `입력한_데이터가_모두_유효한_경우_유효성_검사_테스트`() {
        //given
        val state = UserRegisterState(
            userName = "abc",
            email = "abcde@naver.com",
            password = "abc12345",
            passwordConfirm = "abc12345",
        )

        // when
        val isPassed = UserRegisterButtonValidator.checkValidation(state)

        // then
        Assert.assertTrue(isPassed)
    }

    @Test
    fun `입력한_데이터가_하나라도_비어있는_경우_유효성_검사_테스트`() {
        //given
        val state = UserRegisterState(
            userName = "",
            email = "abcde@naver.com",
            password = "abc12345",
            passwordConfirm = "abc12345",
        )

        // when
        val isPassed = UserRegisterButtonValidator.checkValidation(state)

        // then
        Assert.assertFalse(isPassed)
    }

    @Test
    fun `입력한_데이터가_하나라도_유효하지_않은_경우_유효성_검사_테스트`() {
        //given
        val state = UserRegisterState(
            userName = "!!",
            email = "abcde@naver.com",
            password = "abc12345",
            passwordConfirm = "abc12345",
        )

        // when
        val isPassed = UserRegisterButtonValidator.checkValidation(state)

        // then
        Assert.assertFalse(isPassed)
    }
}
