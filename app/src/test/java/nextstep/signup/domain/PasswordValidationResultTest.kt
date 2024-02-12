package nextstep.signup.domain

import org.junit.Test


class PasswordValidationResultTest {

    @Test
    fun 비밀번호가_8에서_16자가_아니면_길이가_유효하지_않다() {
        listOf("1234567", "12345678901234567").forEach { password ->
            // given & when
            val actual = PasswordValidationResult.match(password)

            // then
            assert(actual == PasswordValidationResult.INVALID_LENGTH)
        }
    }

    @Test
    fun 비밀번호에_영문과_숫자를_모두_포함하지_않으면_유효하지_않다() {
        listOf("12345678", "abcdefgh", "가나다라마바사아자차카타파하").forEach { password ->
            // given & when
            val actual = PasswordValidationResult.match(password)

            // then
            assert(actual == PasswordValidationResult.INVALID_FORMAT)
        }
    }

    @Test
    fun 비밀번호가_8에서_16자의_영문과_숫자를_모두_포함하면_유효하다() {
        listOf("1q2w3e4r!", "nextstep005!", "compose123@").forEach { password ->
            // given & when
            val actual = PasswordValidationResult.match(password)

            // then
            assert(actual == PasswordValidationResult.SUCCESS)
        }
    }
}
