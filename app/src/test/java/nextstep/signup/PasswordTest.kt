package nextstep.signup

import nextstep.signup.ui.signup.SignupInvalidationState.PASSWORD_LENGTH_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.PASSWORD_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success
import nextstep.signup.ui.signup.SignupValidator.Password
import nextstep.signup.ui.signup.isValid
import org.junit.Assert.assertEquals
import org.junit.Test

class PasswordTest {

    @Test
    fun `7글자 비밀번호는 불가능하다`() {
        // given:
        val password: String = "1234567"

        // when:
        val actual = password.isValid<Password>()

        // then:
        assertEquals(true, actual !is Success)
    }

    @Test
    fun `17글자 비밀번호는 불가능하다`() {
        // given:
        val password: String = "123456789" + "12345678"

        // when:
        val actual = password.isValid<Password>()

        // then:
        assertEquals(true, actual !is Success)
    }

    @Test
    fun `8~16글자 수 범위 외의 비밀번호는 다음 문자열을 반환한다`() {
        // given:
        val password: String = "123456789" + "12345678"

        // when:
        val actual = password.isValid<Password>()

        // then:
        assertEquals(PASSWORD_LENGTH_INVALIDATION.message, (actual as Failure).result.message)
    }

    @Test
    fun `8~16글자 수 범위 내의 비밀번호만 사용 가능하다`() {
        // given:
        val passwords: List<String> = List<String>(9) { "pass0123" + "12345678".take(it) }

        // when:
        val actual = passwords.map { it.isValid<Password>() }

        // then:
        assertEquals(9, actual.count { it is Success })
    }

    @Test
    fun `영어만 들어간 비밀번호는 사용할 수 없다`() {
        // given:
        val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
        val password: String = "password!"

        // when:
        val actual = password.isValid<Password>()

        // then:
        assertEquals(!password.matches(Regex(PASSWORD_REGEX)), actual !is Success)
    }

    @Test
    fun `숫자만 들어간 비밀번호는 사용할 수 없다`() {
        // given:
        val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
        val password: String = "12345678"

        // when:
        val actual = password.isValid<Password>()

        // then:
        assertEquals(!password.matches(Regex(PASSWORD_REGEX)), actual !is Success)
    }

    @Test
    fun `비밀번호 규약 외 비밀번호는 다음 문자열을 반환한다`() {
        // given:
        val passwords: List<String> = listOf("12345678", "password")

        // when:
        val actual = passwords.map { it.isValid<Password>() }

        // then:
        val expected = listOf(
            PASSWORD_RULE_INVALIDATION.message,
            PASSWORD_RULE_INVALIDATION.message,
        )

        assertEquals(expected, actual.map { (it as Failure).result.message })
    }
}
