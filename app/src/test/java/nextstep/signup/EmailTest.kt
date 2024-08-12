package nextstep.signup

import nextstep.signup.ui.signup.SignupInvalidationState.EMAIL_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success
import nextstep.signup.ui.signup.SignupValidator.Email
import nextstep.signup.ui.signup.isValid
import org.junit.Assert.assertEquals
import org.junit.Test

class EmailTest {

    @Test
    fun `형식에 맞지 않는 이메일은 사용할 수 없다`() {
        // given:
        val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        val email: List<String> = listOf(
            "s2ehun", "s2ehun@gmail", "s2ehun@gmail..."
        )

        // when:
        val actual = email.map { it.isValid<Email>() }

        // then:
        val expected = email.map { it.matches(Regex(EMAIL_REGEX)) }.count { it }
        assertEquals(expected, actual.count { it is Success })
    }

    @Test
    fun `형식에 맞지 않는 이메일은 다음 문자열을 반환한다`() {
        // given:
        val email: String = "s2ehun"

        // when:
        val actual = email.isValid<Email>()

        // then:
        assertEquals(EMAIL_RULE_INVALIDATION.message, (actual as Failure).result.message)
    }
}
