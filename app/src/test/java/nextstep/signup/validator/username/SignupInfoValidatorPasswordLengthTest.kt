package nextstep.signup.validator.username

import nextstep.signup.validator.SignupInfoValidateResult
import nextstep.signup.validator.SignupInfoValidator
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class SignupInfoValidatorPasswordLengthTest(
    private val inputText: String,
    private val outputResult: SignupInfoValidateResult,
) {
    @Test
    fun `비밀번호는_8자이상_16자이하만_성공한다`() {
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
                arrayOf("1q2w3e", SignupInfoValidateResult.INVALID_LENGTH_PASSWORD),
                arrayOf("1q2w3e4r", SignupInfoValidateResult.SUCCESS),
                arrayOf("1q2w3e4r5t", SignupInfoValidateResult.SUCCESS),
                arrayOf("1q2w3e4r5t6y", SignupInfoValidateResult.SUCCESS),
                arrayOf("1q2w3e4r5t6y7u", SignupInfoValidateResult.SUCCESS),
                arrayOf("1q2w3e4r5t6y7u8i", SignupInfoValidateResult.SUCCESS),
                arrayOf("1q2w3e4r5t6y7u8i9o", SignupInfoValidateResult.INVALID_LENGTH_PASSWORD),
            )
        }
    }
}
