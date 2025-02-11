package nextstep.signup.validator

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class SignupInfoValidatorPasswordConfirmTest(
    private val inputPassword: String,
    private val inputPasswordConfirm: String,
    private val outputResult: SignupInfoValidateResult,
) {
    @Test
    fun `비밀번호가_일치해야한다`() {
        // given
        val passwordConfirm = inputPasswordConfirm

        // when
        val result =
            SignupInfoValidator.PasswordConfirm { inputPassword }.checkCondition(passwordConfirm)

        //then
        assert(result == outputResult)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun params(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    "1q2w3e4rt",
                    "1q2w3e4r",
                    SignupInfoValidateResult.Failure.PasswordConfirm.NOT_MATCH_PASSWORD
                ),
                arrayOf(
                    "1231231q",
                    "1231231w",
                    SignupInfoValidateResult.Failure.PasswordConfirm.NOT_MATCH_PASSWORD
                ),
                arrayOf("1231231w", "1231231w", SignupInfoValidateResult.Success),
            )
        }
    }
}
