package nextstep.signup

import org.junit.Test

class SignUpTextFieldValidationTest {
    private val validation = SignUpTextFieldValidation()

    @Test
    fun `유저 이름이 2자 미만일 때 유효성 검사에 실패한다`() {
        // given
        val username = "a"

        // when
        val actualValue = validation.getUsernameValidationMessage(username)

        // then
        assert(actualValue == "이름은 2~5자여야 합니다.")
    }

    @Test
    fun `유저 이름이 5자를 초과할 때 유효성 검사에 실패한다`() {
        // given
        val username = "abcdef"

        // when
        val actualValue = validation.getUsernameValidationMessage(username)

        // then
        assert(actualValue == "이름은 2~5자여야 합니다.")
    }

    @Test
    fun `유저 이름에 숫자가 포함되어 있을 때 유효성 검사에 실패한다`() {
        // given
        val username = "a1"

        // when
        val actualValue = validation.getUsernameValidationMessage(username)

        // then
        assert(actualValue == "이름에는 숫자나 기호가 포함될 수 없습니다.")
    }

    @Test
    fun `유저 이름에 기호가 포함되어 있을 때 유효성 검사에 실패한다`() {
        // given
        val username = "a!"

        // when
        val actualValue = validation.getUsernameValidationMessage(username)

        // then
        assert(actualValue == "이름에는 숫자나 기호가 포함될 수 없습니다.")
    }

    @Test
    fun `유저 이름에 특수문자가 포함되어 있을 때 유효성 검사에 실패한다`() {
        // given
        val username = "a@"

        // when
        val actualValue = validation.getUsernameValidationMessage(username)

        // then
        assert(actualValue == "이름에는 숫자나 기호가 포함될 수 없습니다.")
    }

    @Test
    fun `이메일 형식이 올바르지 않을 때 유효성 검사에 실패한다`() {
        // given
        val email = "a"

        // when
        val actualValue = validation.getEmailValidationMessage(email)

        // then
        assert(actualValue == "이메일 형식이 올바르지 않습니다.")
    }

    @Test
    fun `비밀번호가 8자 미만일 때 유효성 검사에 실패한다`() {
        // given
        val password = "1234567"
        val passwordConfirm = "1234567"

        // when
        val actualValue = validation.getPasswordValidationMessage(password, passwordConfirm)

        // then
        assert(actualValue == "비밀번호는 8~16자여야 합니다.")
    }

    @Test
    fun `비밀번호가 16자를 초과할 때 유효성 검사에 실패한다`() {
        // given
        val password = "12345678901234567"
        val passwordConfirm = "12345678901234567"

        // when
        val actualValue = validation.getPasswordValidationMessage(password, passwordConfirm)

        // then
        assert(actualValue == "비밀번호는 8~16자여야 합니다.")
    }

    @Test
    fun `비밀번호에 영문과 숫자가 포함되어 있지 않을 때 유효성 검사에 실패한다`() {
        // given
        val password = "123456789"
        val passwordConfirm = "123456789"

        // when
        val actualValue = validation.getPasswordValidationMessage(password, passwordConfirm)

        // then
        assert(actualValue == "비밀번호는 영문과 숫자를 포함해야 합니다.")
    }

    @Test
    fun `비밀번호와 비밀번호 확인이 일치하지 않을 때 유효성 검사에 실패한다`() {
        // given
        val password = "12345678"
        val passwordConfirm = "123456789"

        // when
        val actualValue = validation.getPasswordConfirmValidationMessage(password, passwordConfirm)

        // then
        assert(actualValue == "비밀번호가 일치하지 않습니다.")
    }
}