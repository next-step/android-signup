package nextstep.signup.validator.username

import nextstep.signup.validator.SignupInfoValidateResult
import nextstep.signup.validator.SignupInfoValidator
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class SignupInfoValidatorPasswordFormatTest(
    private val inputText: String,
    private val outputResult: SignupInfoValidateResult,
) {
    @Test
    fun `비밀번호는_영문과_숫자를_포함해야한다`() {
        // given
        val password = inputText

        // when
        val result = SignupInfoValidator.Password.checkCondition(password)

        //then
        assert(result == outputResult)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun params(): Collection<Array<Any>> {
            return listOf(
                arrayOf("11111111", SignupInfoValidateResult.INVALID_FORMAT_PASSWORD),
                arrayOf("qqqqqqqq", SignupInfoValidateResult.INVALID_FORMAT_PASSWORD),
                arrayOf("1q2w3e4r", SignupInfoValidateResult.SUCCESS),
            )
        }
    }
}
