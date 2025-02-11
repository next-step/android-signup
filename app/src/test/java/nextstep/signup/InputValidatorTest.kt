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

    @ParameterizedTest
    @MethodSource("passwordLengthProvider")
    fun `패스워드 길이 테스트`(password: String, isInLength: Boolean) {
        // when
        val actual = inputValidator.checkPassword(password)

        // then
        assertEquals(isInLength, actual.isInLength)
    }

    @ParameterizedTest
    @MethodSource("passwordCharacterProvider")
    fun `패스워드 문자 포함 테스트`(password: String, hasCharacter: Boolean) {
        // when
        val actual = inputValidator.checkPassword(password)

        // then
        assertEquals(hasCharacter, actual.hasCharacter)
    }

    @ParameterizedTest
    @MethodSource("passwordNumberProvider")
    fun `패스워드 숫자 포함 테스트`(password: String, hasNumber: Boolean) {
        // when
        val actual = inputValidator.checkPassword(password)

        // then
        assertEquals(hasNumber, actual.hasNumber)
    }

    @ParameterizedTest
    @MethodSource("passwordAllProvider")
    fun `패스워드 Valid 테스트`(password: String, isValid: Boolean) {
        // when
        val actual = inputValidator.checkPassword(password)

        // then
        assertEquals(isValid, actual.isValidPassword)
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

        @JvmStatic
        fun passwordLengthProvider(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf("", false),
                arrayOf("1", false),
                arrayOf("1234567", false),
                arrayOf("12345678", true),
                arrayOf("1234567890123456", true),
                arrayOf("12345678901234567", false),
            )
        }

        @JvmStatic
        fun passwordNumberProvider(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf("", false),
                arrayOf("1", true),
                arrayOf("asdfqwer", false),
                arrayOf("q1w2e3r4", true),
            )
        }

        @JvmStatic
        fun passwordCharacterProvider(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf("", false),
                arrayOf("12345678", false),
                arrayOf("i2e45678", true),
                arrayOf("a", true),
                arrayOf("A", true),
            )
        }

        @JvmStatic
        fun passwordAllProvider(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf("", false),
                arrayOf("asdfqwer", false),
                arrayOf("12345678", false),
                arrayOf("12e45678", true),
                arrayOf("q1w2e3r4", true),
                arrayOf("q1w2e3r", false),
                arrayOf("qwerasdf12341234", true),
                arrayOf("qwerasdf123412345", false),
            )
        }
    }
}
