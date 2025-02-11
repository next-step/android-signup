package nextstep.signup.validator

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class SignupInfoValidatorEmailFormatTest(
    private val inputText: String,
    private val outputResult: SignupInfoValidateResult,
) {
    @Test
    fun `이메일은_형식이_맞아야한다`() {
        // given
        val email = inputText

        // when
        val result = SignupInfoValidator.Email.checkCondition(email)

        //then
        assert(result == outputResult)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun params(): Collection<Array<Any>> {
            return listOf(
                arrayOf("gmail.com", SignupInfoValidateResult.Failure.Email.INVALID_FORMAT_EMAIL),
                arrayOf("raindragonn", SignupInfoValidateResult.Failure.Email.INVALID_FORMAT_EMAIL),
                arrayOf(
                    "raindragonn@gmail",
                    SignupInfoValidateResult.Failure.Email.INVALID_FORMAT_EMAIL
                ),
                arrayOf("raindragonn@gmail.com", SignupInfoValidateResult.Success),
            )
        }
    }
}
