package nextstep.signup.validator.username

import nextstep.signup.validator.SignupInfoValidateResult
import nextstep.signup.validator.SignupInfoValidator
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class SignupInfoValidatorUsernameFormatTest(
    private val inputText: String,
    private val outputResult: SignupInfoValidateResult,
) {
    @Test
    fun `이름에는_숫자나_기호가_포함될_수_없다`() {
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
                arrayOf("가1", SignupInfoValidateResult.INVALID_FORMAT_USERNAME),
                arrayOf("가😀", SignupInfoValidateResult.INVALID_FORMAT_USERNAME),
                arrayOf("가나다", SignupInfoValidateResult.SUCCESS),
            )
        }
    }
}
