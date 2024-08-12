package nextstep.signup

import nextstep.signup.ui.signup.SignupInvalidationState.PASSWORD_CONFIRM_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupValidationResult.Failure
import nextstep.signup.ui.signup.SignupValidationResult.Success
import nextstep.signup.ui.signup.SignupValidator.PasswordConfirm
import nextstep.signup.ui.signup.isValid
import org.junit.Assert.assertEquals
import org.junit.Test

class PasswordConfirmTest {

    @Test
    fun `비밀번호와 불일치할 경우 다음 문자열을 반환한다`() {
        // given:
        val password: String = "pass0123"
        val passwordConfirm: String = "pass012"

        // when:
        val actual = passwordConfirm.isValid<PasswordConfirm>(password)

        // then:
        assertEquals(PASSWORD_CONFIRM_RULE_INVALIDATION.message, (actual as Failure).result.message)
    }

    @Test
    fun `비밀번호와 일치할 경우 Success 객체를 반환한다`() {
        // given:
        val password: String = "pass0123"
        val passwordConfirm: String = "pass0123"

        // when:
        val actual = passwordConfirm.isValid<PasswordConfirm>(password)

        // then:
        assertEquals(true, actual is Success)
    }
}
