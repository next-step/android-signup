package nextstep.signup

import nextstep.signup.ui.signin.SignupValidator
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidatorPasswordTest {

    @Test
    fun `빈_비밀번호_검증할_때_Empty_반환`() {
        // Given
        val password = ""

        // When
        val result = SignupValidator.validatePassword(password)

        // Then
        assertEquals(SignupValidator.ResultType.Empty, result)
    }

    @Test
    fun `유효한_비밀번호_검증할_때_Success_반환`() {
        // Given
        val password = "abcd1234"

        // When
        val result = SignupValidator.validatePassword(password)

        // Then
        assertEquals(SignupValidator.ResultType.Success, result)
    }

    @Test
    fun `너무_짧거나_긴_비밀번호_검증할_때_PasswordLength_반환`() {
        // Given
        val password1 = "a"
        val password2 = "abcd1234abcd1234abcd1234"

        // When
        val result1 = SignupValidator.validatePassword(password1)
        val result2 = SignupValidator.validatePassword(password2)

        // Then
        assertEquals(SignupValidator.ResultType.PasswordLength, result1)
        assertEquals(SignupValidator.ResultType.PasswordLength, result2)
    }

    @Test
    fun `영문_또는_숫자가_포함되지_않은_비밀번호_검증할_때_PasswordInvalidFormat_반환`() {
        // Given
        val password1 = "12341234"
        val password2 = "abcdabcd"

        // When
        val result1 = SignupValidator.validatePassword(password1)
        val result2 = SignupValidator.validatePassword(password2)

        // Then
        assertEquals(SignupValidator.ResultType.PasswordInvalidFormat, result1)
        assertEquals(SignupValidator.ResultType.PasswordInvalidFormat, result2)
    }

    @Test
    fun `서로 다른 비밀번호 검증할 때 PasswordMismatch 반환`() {
        // Given
        val password = "abcd1234"
        val confirmPassword = "abcd1235"

        // When
        val result = SignupValidator.validatePasswordMatch(password, confirmPassword)

        // Then
        assertEquals(SignupValidator.ResultType.PasswordMismatch, result)
    }

    @Test
    fun `동일한 비밀번호 검증할 때 Success 반환`() {
        // Given
        val password = "abcd1234"
        val confirmPassword = "abcd1234"

        // When
        val result = SignupValidator.validatePasswordMatch(password, confirmPassword)

        // Then
        assertEquals(SignupValidator.ResultType.Success, result)
    }
}