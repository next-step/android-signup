package nextstep.signup

import nextstep.signup.ui.signin.SignupValidator
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidatorUsernameTest {

    @Test
    fun `빈_사용자명_검증할_때_Empty_반환`() {
        // Given
        val username = ""

        // When
        val result = SignupValidator.validateUsername(username)

        // Then
        assertEquals(SignupValidator.ResultType.Empty, result)
    }

    @Test
    fun `유효한_사용자명_검증할_때_Success_반환`() {
        // Given
        val username = "컴포즈"

        // When
        val result = SignupValidator.validateUsername(username)

        // Then
        assertEquals(SignupValidator.ResultType.Success, result)
    }

    @Test
    fun `너무_짧거나_긴_사용자명_검증할_때_UsernameLength_반환`() {
        // Given
        val username1 = "A"
        val username2 = "컴포즈입니다"

        // When
        val result1 = SignupValidator.validateUsername(username1)
        val result2 = SignupValidator.validateUsername(username2)

        // Then
        assertEquals(SignupValidator.ResultType.UsernameLength, result1)
        assertEquals(SignupValidator.ResultType.UsernameLength, result2)
    }

    @Test
    fun `유효하지_않은_형식의_사용자명_검증할_때_UsernameInvalidFormat_반환`() {
        // Given
        val username = "컴포즈#"

        // When
        val result = SignupValidator.validateUsername(username)

        // Then
        assertEquals(SignupValidator.ResultType.UsernameInvalidFormat, result)
    }
}