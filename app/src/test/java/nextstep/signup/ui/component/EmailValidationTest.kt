package nextstep.signup.ui.component

import junit.framework.TestCase.assertEquals
import nextstep.signup.ui.component.EmailValidation.EmailValidationResult
import org.junit.Test

class EmailValidationTest {
    @Test
    fun `이메일 형식이 올바르지 않으면 이미지 실패 결과를 반환한다`() {
        // given
        val email = "test"

        // when
        val result = EmailValidation().isValid(email)

        // then
        assertEquals(EmailValidationResult.FailureEmailFormat, result)
    }

    @Test
    fun `이메일 형식이 올바르면 성공을 반환한다`() {
        // given
        val email = "test@yopmail.com"

        // when
        val result = EmailValidation().isValid(email)

        // then
        assertEquals(EmailValidationResult.Success, result)
    }

    @Test
    fun `이메일 형식이 비어있으면 Empty를 반환한다`() {
        // given
        val email = ""

        // when
        val result = EmailValidation().isValid(email)

        // then
        assertEquals(EmailValidationResult.Empty, result)
    }
}
