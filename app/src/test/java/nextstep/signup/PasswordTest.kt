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
    fun `7글자_비밀번호는_불가능하다`() {
        // given:
        val password: String = "1234567"

        // when:
        val actual = password.isValid<Password>()

        // then:
        assertEquals(true, actual !is Success)
    }

    @Test
    fun `17글자_비밀번호는_불가능하다`(){
        // given:
        val password: String = "123456789" + "12345678"

        // when:
        val actual = password.isValid<Password>()

        // then:
        assertEquals(true, actual !is Success)
    }

    @Test
    fun `8_16글자_수_범위_외의_비밀번호는_다음_문자열을_반환한다`() {
        // given:
        val password: String = "123456789" + "12345678"

        // when:
        val actual = password.isValid<Password>()

        // then:
        assertEquals(PASSWORD_LENGTH_INVALIDATION.message, (actual as Failure).result.message)
    }

    @Test
    fun `8_16글자_수_범위_내의_비밀번호만_사용_가능하다`() {
        // given:
        val passwords: List<String> = List<String>(9) { "pass0123" + "12345678".take(it) }

        // when:
        val actual = passwords.map { it.isValid<Password>() }

        // then:
        assertEquals(9, actual.count { it is Success })
    }

    @Test
    fun `영어만_들어간_비밀번호는_사용할_수_없다`() {
        // given:
        val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
        val password: String = "password!"

        // when:
        val actual = password.isValid<Password>()

        // then:
        assertEquals(!password.matches(Regex(PASSWORD_REGEX)), actual !is Success)
    }

    @Test
    fun `숫자만_들어간_비밀번호는_사용할_수_없다`() {
        // given:
        val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
        val password: String = "12345678"

        // when:
        val actual = password.isValid<Password>()

        // then:
        assertEquals(!password.matches(Regex(PASSWORD_REGEX)), actual !is Success)
    }

    @Test
    fun `비밀번호_규약_외_비밀번호는_다음_문자열을_반환한다`() {
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
