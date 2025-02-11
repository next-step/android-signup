package nextstep.signup

import nextstep.signup.ui.signup.InputValidator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class InputValidatorTest {

    private val inputValidator = InputValidator()

    @ParameterizedTest
    @MethodSource("usernameLengthProvider")
    fun `사용자 닉네임 길이 테스트`(username: String, isInLength: Boolean) {
        // when
        val actual = inputValidator.checkUserName(username)

        // then
        assertEquals(isInLength, actual.isInLength)
    }

    @ParameterizedTest
    @MethodSource("usernameNumberProvider")
    fun `사용자 닉네임 숫자 테스트`(username: String, hasNumber: Boolean) {
        // when
        val actual = inputValidator.checkUserName(username)

        // then
        assertEquals(hasNumber, actual.hasNumber)
    }

    @ParameterizedTest
    @MethodSource("usernameSpecialCharacterProvider")
    fun `사용자 닉네임 특수문자 테스트`(username: String, hasSpecialCharacter: Boolean) {
        // when
        val actual = inputValidator.checkUserName(username)

        // then
        assertEquals(hasSpecialCharacter, actual.hasSpecialCharacter)
    }

    @ParameterizedTest
    @MethodSource("usernameAllProvider")
    fun `사용자 닉네임 Valid 테스트`(username: String, isValid: Boolean) {
        // when
        val actual = inputValidator.checkUserName(username)

        // then
        assertEquals(isValid, actual.isValidUsername)
    }

    @ParameterizedTest
    @MethodSource("emailProvider")
    fun `이메일 테스트`(email: String, isValid: Boolean) {
        // when
        val actual = inputValidator.checkEmail(email)

        // then
        assertEquals(isValid, actual)
    }

    companion object {
        @JvmStatic
        fun usernameLengthProvider(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf("", false),
                arrayOf("a", false),
                arrayOf("ab", true),
                arrayOf("abc", true),
                arrayOf("abcd", true),
                arrayOf("abcde", true),
                arrayOf("abcdef", false),
                arrayOf("abcdefg", false),
            )
        }

        @JvmStatic
        fun usernameNumberProvider(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf("abc", false),
                arrayOf("abc12", true),
            )
        }

        @JvmStatic
        fun usernameSpecialCharacterProvider(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf("abc", false),
                arrayOf("abc!", true),
                arrayOf("abc😁", true),
            )
        }

        @JvmStatic
        fun usernameAllProvider(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf("", false),
                arrayOf("a", false),
                arrayOf("ab", true),
                arrayOf("12", false),
                arrayOf("abc", true),
                arrayOf("ab3", false),
                arrayOf("ab!", false),
                arrayOf("abcd", true),
                arrayOf("abcde", true),
                arrayOf("abcdef", false),
            )
        }

        @JvmStatic
        fun emailProvider(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf("", false),
                arrayOf("a@", false),
                arrayOf("a@a", false),
                arrayOf("a@a", false),
                arrayOf("a@a.", false),
                arrayOf("a@a.c", false),
                arrayOf("a@a.co", true),
                arrayOf("a@a.com", true),
                arrayOf("a@a,com", false),
                arrayOf("a@a.commmm", true),
                arrayOf("a@a.commmmm", false),
            )
        }
    }
}
