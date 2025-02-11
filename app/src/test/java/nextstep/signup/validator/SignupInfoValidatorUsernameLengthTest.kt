package nextstep.signup.validator

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class SignupInfoValidatorUsernameLengthTest(
    private val inputText: String,
    private val outputResult: SignupInfoValidateResult,
) {
    @Test
    fun `이름은_2자이상_5자이하만_성공한다`() {
        // given
        val username = inputText

        // when
        val result = SignupInfoValidator.Username.checkCondition(username)

        //then
        assert(result == outputResult)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun params(): Collection<Array<Any>> {
            return listOf(
                arrayOf("가", SignupInfoValidateResult.Failure.Username.INVALID_LENGTH_USERNAME),
                arrayOf("가나", SignupInfoValidateResult.Success),
                arrayOf("가나다", SignupInfoValidateResult.Success),
                arrayOf("가나다라", SignupInfoValidateResult.Success),
                arrayOf("가나다라마", SignupInfoValidateResult.Success),
                arrayOf(
                    "가나다라마사",
                    SignupInfoValidateResult.Failure.Username.INVALID_LENGTH_USERNAME
                ),
            )
        }
    }
}
