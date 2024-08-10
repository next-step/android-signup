package nextstep.signup.ui.component

import junit.framework.TestCase.assertEquals
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
        val result = PasswordConfirmValidation().isValid(passwordConfirm)

        // then
        assertEquals(ValidationResult.Success, result)
    }

    @Test
    fun `비밀번호가 일치하지 않으면 실패한다`() {
        // given
        val passwordConfirm =
            PasswordConfirmValidation.PasswordConfirm(
                password = "password",
                passwordConfirm = "password1",
            )

        // when
        val result = PasswordConfirmValidation().isValid(passwordConfirm)

        // then
        assertEquals(PasswordConfirmValidation.FailurePasswordNotMatch, result)
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
        val result = PasswordConfirmValidation().isValid(passwordConfirm)

        // then
        assertEquals(PasswordConfirmValidation.EmptyPassword, result)
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
        val result = PasswordConfirmValidation().isValid(passwordConfirm)

        // then
        assertEquals(PasswordConfirmValidation.EmptyPasswordConfirm, result)
    }
}
