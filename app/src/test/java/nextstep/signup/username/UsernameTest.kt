package nextstep.signup.username

import nextstep.signup.ui.signup.SignupInputType.Username.isValid
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_LENGTH_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success
import org.junit.Assert.assertEquals
import org.junit.Test

class UsernameTest {

    @Test
    fun `1글자 유저 닉네임은 불가능하다`() {
        // given:
        val username: String = "산"

        // when:
        val actual = username.isValid()

        // then:
        assertEquals(true, actual !is Success)
    }

    @Test
    fun `6글자 유저 닉네임은 불가능하다`() {
        // given:
        val username: String = "산군산군산군"

        // when:
        val actual = username.isValid()

        // then:
        assertEquals(true, actual !is Success)
    }

    @Test
    fun `2~5글자 수 범위 외의 닉네임은 다음 문자열을 반환한다`() {
        // given:
        val username: String = "산군산군산군"

        // when:
        val actual = username.isValid()

        // then:
        assertEquals(USERNAME_LENGTH_INVALIDATION.message, (actual as Failure).result.message)
    }

    @Test
    fun `2~5글자 유저 닉네임만 가능하다`() {
        // given:
        val usernames: List<String> = List<String>(4) { "산군" + "산군산".take(it) }

        // when:
        val actual = usernames.map { it.isValid() }

        // then:
        assertEquals(4, actual.count { it is Success })
    }

    @Test
    fun `특수문자 닉네임은 사용할 수 없다`() {
        // given:
        val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        val username: String = "!%@$"

        // when:
        val actual = username.isValid()

        // then:
        assertEquals(!username.matches(Regex(USERNAME_REGEX)), actual !is Success)
    }

    @Test
    fun `특수문자를 사용한 닉네임은 다음 문자열을 반환한다`() {
        // given:
        val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        val username: String = "!%@$"

        // when:
        val actual = username.isValid()

        // then:
        assertEquals(USERNAME_RULE_INVALIDATION.message, (actual as Failure).result.message)
    }
}
