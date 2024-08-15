package nextstep.signup

import org.junit.Assert.*
import nextstep.signup.model.EmailState
import nextstep.signup.model.PasswordConfirmState
import nextstep.signup.model.PasswordState
import nextstep.signup.model.UserNameState
import nextstep.signup.valid.RegexBasedSignUpValidator
import org.junit.Before
import org.junit.Test

class RegexBasedSignUpValidatorTest {
    private lateinit var validator: RegexBasedSignUpValidator

    @Before
    fun setup() {
        validator = RegexBasedSignUpValidator()
    }

    @Test
    fun `이름은 2~5자여야 합니다`() {
        assertTrue(validator.validateUsername("컴포즈") is UserNameState.Valid)
        assertTrue(validator.validateUsername("컴") is UserNameState.Invalid)
        assertTrue(validator.validateUsername("컴포즈컴") is UserNameState.Valid)
        assertTrue(validator.validateUsername("컴포즈컴포즈") is UserNameState.Invalid)
    }

    @Test
    fun `이름에는 숫자나 기호가 포함될 수 없습니다`() {
        assertTrue(validator.validateUsername("컴포즈1") is UserNameState.Invalid)
        assertTrue(validator.validateUsername("컴@포즈") is UserNameState.Invalid)
    }

    @Test
    fun `이메일 형식이 올바른지 검사합니다`() {
        assertTrue(validator.validateEmail("test@test.com") is EmailState.Valid)
        assertTrue(validator.validateEmail("invalid-email") is EmailState.Invalid)
        assertTrue(validator.validateEmail("test@test") is EmailState.Invalid)
    }

    @Test
    fun `비밀번호는 8~16자여야 합니다`() {
        assertTrue(validator.validatePassword("pass123") is PasswordState.Invalid)
        assertTrue(validator.validatePassword("password123") is PasswordState.Valid)
        assertTrue(validator.validatePassword("verylongpassword123456") is PasswordState.Invalid)
    }

    @Test
    fun `비밀번호는 영문과 숫자를 포함해야 합니다`() {
        assertTrue(validator.validatePassword("password") is PasswordState.Invalid)
        assertTrue(validator.validatePassword("12345678") is PasswordState.Invalid)
        assertTrue(validator.validatePassword("password123") is PasswordState.Valid)
    }

    @Test
    fun `비밀번호 확인이 일치하는지 검사합니다`() {
        assertTrue(validator.validatePasswordConfirm("password123", "password123") is PasswordConfirmState.Valid)
        assertTrue(validator.validatePasswordConfirm("password123", "password456") is PasswordConfirmState.Mismatch)
    }
}