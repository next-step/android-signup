package nextstep.signup

import org.junit.Assert
import org.junit.Test

class PasswordConfirmationValidationTest {

    @Test
    fun 비밀번호와_비밀번호_확인에_입력한_값이_동일해야_한다() {
        val password = "compose12"
        val confirmPassword = "compose12"

        Assert.assertTrue(
            InputValidation.validateConfirmPassword(
                password,
                confirmPassword
            ) == ValidationResult.SUCCESS
        )
    }

    @Test
    fun 비밀번호와_비밀번호_확인에_입력한_값이_다르면_에러메시지가_노출된다() {
        val password = "compose12"
        val confirmPassword = "compase12"

        Assert.assertTrue(
            InputValidation.validateConfirmPassword(
                password,
                confirmPassword
            ) == ValidationResult.PASSWORD_MISMATCH
        )
    }
}