package nextstep.signup.model

import org.junit.Assert.assertEquals
import org.junit.Test

class InputValidatorTest {

    @Test
    fun 이름이_공백이면_Default_상태이다() {
        val result = InputValidator.isValid("", TextFieldType.Username)
        assertEquals(TextFieldState.Default, result)
    }

    @Test
    fun 이름에_숫자나_기호가_없고_두_글자일_경우_Valid_상태이다() {
        val result = InputValidator.isValid("js", TextFieldType.Username)
        assertEquals(TextFieldState.Valid, result)
    }

    @Test
    fun 이름이_한_글자일_경우_LengthError_상태이다() {
        val result = InputValidator.isValid("j", TextFieldType.Username)
        assertEquals(TextFieldState.LengthError, result)
    }

    @Test
    fun 이름에_숫자나_기호가_없고_이름이_여섯_글자일_경우_LengthError_상태이다() {
        val result = InputValidator.isValid("korean", TextFieldType.Username)
        assertEquals(TextFieldState.LengthError, result)
    }

    @Test
    fun 이름에_숫자나_기호가_있는_경우_InValid_상태이다() {
        val result = InputValidator.isValid("js1", TextFieldType.Username)
        assertEquals(TextFieldState.InValid, result)
    }

    @Test
    fun 이름에_숫자나_기호가_있고_이름이_여섯_글자일_경우_InValid_상태이다() {
        val result = InputValidator.isValid("users1", TextFieldType.Username)
        assertEquals(TextFieldState.InValid, result)
    }

    @Test
    fun 이메일_형식이_공백인_경우_Default_상태이다() {
        val result = InputValidator.isValid("", TextFieldType.Email)
        assertEquals(TextFieldState.Default, result)
    }

    @Test
    fun 이메일_형식이_아닌_경우_InValid_상태이다() {
        val result = InputValidator.isValid("user@name", TextFieldType.Email)
        assertEquals(TextFieldState.InValid, result)
    }

    @Test
    fun 이메일_형식일_경우_Valid_상태이다() {
        val result = InputValidator.isValid("user@example.com", TextFieldType.Email)
        assertEquals(TextFieldState.Valid, result)
    }

    @Test
    fun 비밀번호가_공백인_경우_Default_상태이다() {
        val result = InputValidator.isValid("", TextFieldType.Password)
        assertEquals(TextFieldState.Default, result)
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하고_8글자인_경우_Valid_상태이다() {
        val result = InputValidator.isValid("q1w2e3r4", TextFieldType.Password)
        assertEquals(TextFieldState.Valid, result)
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하고_16글자인_경우_Valid_상태이다() {
        val result = InputValidator.isValid("q1w2e3r4q1w2e3r4", TextFieldType.Password)
        assertEquals(TextFieldState.Valid, result)
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하고_7글자인_경우_LengthError_상태이다() {
        val result = InputValidator.isValid("q1w2e3r", TextFieldType.Password)
        assertEquals(TextFieldState.LengthError, result)
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하고_17글자인_경우_LengthError_상태이다() {
        val result = InputValidator.isValid("q1w2e3r4q1w2e3r45", TextFieldType.Password)
        assertEquals(TextFieldState.LengthError, result)
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않은_경우_InValid_상태이다() {
        val result = InputValidator.isValid("qqqq", TextFieldType.Password)
        assertEquals(TextFieldState.InValid, result)
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않고_8글자인_경우_InValid_상태이다() {
        val result = InputValidator.isValid("qqqqwwww", TextFieldType.Password)
        assertEquals(TextFieldState.InValid, result)
    }
}