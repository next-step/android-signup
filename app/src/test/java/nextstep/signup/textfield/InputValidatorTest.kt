package nextstep.signup.textfield

import org.junit.Assert.assertEquals
import org.junit.Test


class InputValidatorTest{

    @Test
    fun 사용자_이름이_2자미만이면_LENGTH_ERROR_에러가_발생한다() {
        val username = "수"

        val error = InputValidator.validateUsername(username)

        assertEquals(error, UsernameError.LENGTH_ERROR)
    }

    @Test
    fun 사용자_이름이_5자초과면_LENGTH_ERROR_에러가_발생한다() {
        val username = "수수수수퍼노바"

        val error = InputValidator.validateUsername(username)

        assertEquals(error, UsernameError.LENGTH_ERROR)
    }

    @Test
    fun 사용자_이름에_숫자가_포함되면_INVALID_CHARACTER_ERROR_에러가_발생한다() {
        val username = "양수진123"

        val error = InputValidator.validateUsername(username)

        assertEquals(error, UsernameError.INVALID_CHARACTER_ERROR)
    }

    @Test
    fun 사용자_이름에_기호가_포함되면_에러가_발생된다() {
        val username = "쌈@뽕수진"

        val error = InputValidator.validateUsername(username)

        assertEquals(error, UsernameError.INVALID_CHARACTER_ERROR)
    }

    @Test
    fun 사용자_이름이_2에서5자이고_숫자나_기호가_포함되지_않으면_에러가_발생하지_않는다() {
        val username = "양수진"

        val error = InputValidator.validateUsername(username)

        assertEquals(error, UsernameError.NONE)
    }

    @Test
    fun 이메일_형식이_맞으면_유효성_검사결과가_true다() {
        val email = "yangsooplus0@gmail.com"

        val isValid = InputValidator.isValidEmail(email)

        assertEquals(isValid, true)
    }

    @Test
    fun 이메일_형식에_골뱅이표가_없으면_유효성_검사결과가_false다() {
        val email = "yangsooplus0gmail.com"

        val isValid = InputValidator.isValidEmail(email)

        assertEquals(isValid, false)
    }

    @Test
    fun 이메일_형식에_점이_없으면_유효성_검사결과가_false다() {
        val email = "yangsooplus0@gmailcom"

        val isValid = InputValidator.isValidEmail(email)

        assertEquals(isValid, false)
    }

    @Test
    fun 이메일_형식에_골뱅이표와_점_사이에_문자가_없으면_유효성_검사결과가_false다() {
        val email = "yangsooplus0@.com"

        val isValid = InputValidator.isValidEmail(email)

        assertEquals(isValid, false)
    }

    @Test
    fun 비밀번호_형식이_맞으면_에러가_발생하지_않는다() {
        val password = "qwer1234"

        val error = InputValidator.validatePassword(password)

        assertEquals(error, PasswordError.NONE)
    }

    @Test
    fun 비밀번호_길이가_8자_미만이면_LENGTH_ERROR_에러가_발생한다() {
        val password = "q1w2"

        val error = InputValidator.validatePassword(password)

        assertEquals(error, PasswordError.LENGTH_ERROR)
    }

    @Test
    fun 비밀번호_길이가_16자_초과이면_LENGTH_ERROR_에러가_발생한다() {
        val password = "qwer1234qwer1234qwer1234"

        val error = InputValidator.validatePassword(password)

        assertEquals(error, PasswordError.LENGTH_ERROR)
    }

    @Test
    fun 비밀번호에_영문자가_없으면_REQUIRED_CHARACTER_NOT_INCLUDE_ERROR_에러가_발생한다() {
        val password = "12345678"

        val error = InputValidator.validatePassword(password)

        assertEquals(error, PasswordError.REQUIRED_CHARACTER_NOT_INCLUDE_ERROR)
    }

    @Test
    fun 비밀번호에_숫자가_없으면_REQUIRED_CHARACTER_NOT_INCLUDE_ERROR_에러가_발생한다() {
        val password = "qwerasdf"

        val error = InputValidator.validatePassword(password)

        assertEquals(error, PasswordError.REQUIRED_CHARACTER_NOT_INCLUDE_ERROR)
    }

    @Test
    fun 비밀번호_확인과_비밀번호가_일치하지_않으면_유효성_검사결과가_false다() {
        val password = "qwer1234"
        val confirmPassword = "asdf1234"

        val isMatched = InputValidator.isPasswordMatched(password, confirmPassword)

        assertEquals(isMatched, false)
    }

    @Test
    fun 비밀번호_확인과_비밀번호가_일치하면_유효성_검사결과가_true다() {
        val password = "qwer1234"
        val confirmPassword = "qwer1234"

        val isMatched = InputValidator.isPasswordMatched(password, confirmPassword)

        assertEquals(isMatched, true)
    }
}