package nextstep.signup.ui.component

import junit.framework.TestCase.assertEquals
import nextstep.signup.ui.component.PasswordConfirmValidation.PasswordConfirmValidationResult
import org.junit.Test

class PasswordConfirmValidationTest {
    @Test
    fun `비밀번호가 일치하면 성공한다`() {
        // given
        val passwordConfirm =
            PasswordConfirmValidation.PasswordConfirm(
                password = "password",
                passwordConfirm = "password",
            )

        // when
        val result = PasswordConfirmValidation().validate(passwordConfirm)

        // then
        assertEquals(PasswordConfirmValidationResult.Success, result)
    }

    @Test
    fun `비밀번호가 일치하지 않으면 비밀번호 일치하지 않음을 반환한다`() {
        // given
        val passwordConfirm =
            PasswordConfirmValidation.PasswordConfirm(
                password = "password",
                passwordConfirm = "password1",
            )

        // when
        val result = PasswordConfirmValidation().validate(passwordConfirm)

        // then
        assertEquals(PasswordConfirmValidationResult.FailurePasswordNotMatch, result)
    }

    @Test
    fun `비밀번호가 비어있으면 비밀번호 비었음을 반환한다`() {
        // given
        val passwordConfirm =
            PasswordConfirmValidation.PasswordConfirm(
                "",
                "",
            )

        // when
        val result = PasswordConfirmValidation().validate(passwordConfirm)

        // then
        assertEquals(PasswordConfirmValidationResult.Empty, result)
    }

    @Test
    fun `비밀번호 확인이 비어있으면 비밀번호 비었을를 반환한다`() {
        // given
        val passwordConfirm =
            PasswordConfirmValidation.PasswordConfirm(
                password = "password",
                passwordConfirm = "",
            )

        // when
        val result = PasswordConfirmValidation().validate(passwordConfirm)

        // then
        assertEquals(PasswordConfirmValidationResult.Empty, result)
    }
}
