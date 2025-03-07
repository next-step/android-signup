package nextstep.signup

import org.junit.Assert
import org.junit.Test

class UserNameValidationTest {

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        val username = "김컴포즈"

        Assert.assertTrue(InputValidation.validateUserName(username) == ValidationResult.SUCCESS)
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        val username = "김컴포즈입니다"

        Assert.assertTrue(InputValidation.validateUserName(username) == ValidationResult.USERNAME_INVALID_LENGTH)
    }

    @Test
    fun 사용자_이름에는_숫자나_기호가_포함될_수_없습니다() {
        val username = "김컴포즈"

        Assert.assertTrue(InputValidation.validateUserName(username) == ValidationResult.SUCCESS)
    }

    @Test
    fun 사용자_이름에_숫자가_포함되면_에러메시지가_노출된다() {
        val username = "12김3"

        Assert.assertTrue(InputValidation.validateUserName(username) == ValidationResult.USERNAME_INVALID_CHARACTERS)
    }

    @Test
    fun 사용자_이름에_기호가_포함되면_에러메시지가_노출된다() {
        val username = "김@#3"

        Assert.assertTrue(InputValidation.validateUserName(username) == ValidationResult.USERNAME_INVALID_CHARACTERS)
    }
}