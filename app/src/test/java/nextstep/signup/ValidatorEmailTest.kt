package nextstep.signup

import nextstep.signup.ui.signin.SignupValidator
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidatorEmailTest {

    @Test
    fun `빈_이메일_검증할_때_Empty_반환`() {
        // Given
        val email = ""

        // When
        val result = SignupValidator.validateEmail(email)

        // Then
        assertEquals(SignupValidator.ResultType.Empty, result)
    }

    @Test
    fun `유효한_이메일_검증할_때_Success_반환`() {
        // Given
        val email = "abc@abc.com"

        // When
        val result = SignupValidator.validateEmail(email)

        // Then
        assertEquals(SignupValidator.ResultType.Success, result)
    }

    @Test
    fun `유효하지_않은_이메일_형식_검증할 때_EmailInvalidFormat_반환`() {
        // Given
        val email = "test@"

        // When
        val result = SignupValidator.validateEmail(email)

        // Then
        assertEquals(SignupValidator.ResultType.EmailInvalidFormat, result)
    }
}