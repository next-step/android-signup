package nextstep.signup

import nextstep.signup.ui.signup.SignupValidator.Username
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_LENGTH_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success
import nextstep.signup.ui.signup.isValid
import org.junit.Assert.assertEquals
import org.junit.Test

class UsernameTest {

    @Test
    fun `1글자_유저_닉네임은_불가능하다`(){
        // given:
        val username: String = "산"

        // when:
        val actual = username.isValid<Username>()

        // then:
        assertEquals(true, actual !is Success)
    }

    @Test
    fun `6글자_유저_닉네임은_불가능하다`() {
        // given:
        val username: String = "산군산군산군"

        // when:
        val actual = username.isValid<Username>()

        // then:
        assertEquals(true, actual !is Success)
    }

    @Test
    fun `2_5글자_수_범위_외의_닉네임은_다음_문자열을_반환한다`() {
        // given:
        val username: String = "산군산군산군"

        // when:
        val actual = username.isValid<Username>()

        // then:
        assertEquals(USERNAME_LENGTH_INVALIDATION.message, (actual as Failure).result.message)
    }

    @Test
    fun `2_5글자_유저_닉네임만_가능하다`() {
        // given:
        val usernames: List<String> = List<String>(4) { "산군" + "산군산".take(it) }

        // when:
        val actual = usernames.map { it.isValid<Username>() }

        // then:
        assertEquals(4, actual.count { it is Success })
    }

    @Test
    fun `특수문자_닉네임은_사용할_수_없다`() {
        // given:
        val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        val username: String = "!%@$"

        // when:
        val actual = username.isValid<Username>()

        // then:
        assertEquals(!username.matches(Regex(USERNAME_REGEX)), actual !is Success)
    }

    @Test
    fun `특수문자를_사용한_닉네임은_다음_문자열을_반환한다`() {
        // given:
        val username: String = "!%@$"

        // when:
        val actual = username.isValid<Username>()

        // then:
        assertEquals(USERNAME_RULE_INVALIDATION.message, (actual as Failure).result.message)
    }
}
