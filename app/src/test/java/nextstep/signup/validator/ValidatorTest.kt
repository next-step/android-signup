package nextstep.signup.validator

import junit.framework.Assert.assertEquals
import nextstep.signup.util.validation.ValidationErrorType
import nextstep.signup.util.validation.ValidationResult
import nextstep.signup.util.validation.Validator
import org.junit.Test

class ValidatorTest {

    @Test
    fun `사용자_이름이_빈_문자열이면_Pending를_반환한다`(){
        //GIVEN
        val userName = ""
        //WHEN
        val result = Validator.userNameValidate(userName)
        //THEN
        assertEquals(
            result,
            ValidationResult.Pending
        )
    }

    @Test
    fun `사용자_이름이_2에서_6자_사이가_아니면_LengthError를_반환한다`(){
        //GIVEN
        val userName = "1234567"
        //WHEN
        val result = Validator.userNameValidate(userName)
        //THEN
        assertEquals(
            result,
            ValidationResult.Error(ValidationErrorType.LengthError)
        )
    }

    @Test
    fun `사용자_이름에_2에서_6자_사이이고_기호가_있으면_RegexError를_반환한다`(){
        //GIVEN
        val userName = "@가나다"
        //WHEN
        val result = Validator.userNameValidate(userName)
        //THEN
        assertEquals(
            result,
            ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }

    @Test
    fun `사용자_이름에_2에서_6자_사이이고_숫자가_있으면_RegexError를_반환한다`(){
        //GIVEN
        val userName = "가나다12"
        //WHEN
        val result = Validator.userNameValidate(userName)
        //THEN
        assertEquals(
            result,
            ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }

    @Test
    fun `사용자_이름이_2에서_6자_사이이면tj_기호와_숫자가_없으면_Success를_반환한다`(){
        //GIVEN
        val userName = "김컴포즈"
        //WHEN
        val result = Validator.userNameValidate(userName)
        //THEN
        assertEquals(
            result,
            ValidationResult.Success
        )
    }

    @Test
    fun `이메일이_빈_문자열이면_Pending를_반환한다`(){
        //GIVEN
        val email = ""
        //WHEN
        val result = Validator.emailValidate(email)
        //THEN
        assertEquals(
            result,
            ValidationResult.Pending
        )
    }

    @Test
    fun `이메일에_도메인이_없으면_RegexError를_반환한다`(){
        //GIVEN
        val email = "compose"
        //WHEN
        val result = Validator.emailValidate(email)
        //THEN
        assertEquals(
            result,
            ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }

    @Test
    fun `이메일에_아이디가_없으면_RegexError를_반환한다`(){
        //GIVEN
        val email = "@compose.com"
        //WHEN
        val result = Validator.emailValidate(email)
        //THEN
        assertEquals(
            result,
            ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }

    @Test
    fun `이메일에_@가_없으면_RegexError를_반환한다`(){
        //GIVEN
        val email = "compose.com"
        //WHEN
        val result = Validator.emailValidate(email)
        //THEN
        assertEquals(
            result,
            ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }

    @Test
    fun `이메일이_이메일_조건에_부합하면_Success를_반환한다`(){
        //GIVEN
        val email = "android@compose.com"
        //WHEN
        val result = Validator.emailValidate(email)
        //THEN
        assertEquals(
            result,
            ValidationResult.Success
        )
    }

    @Test
    fun `비밀번호가_빈_문자열이면_Pending를_반환한다`(){
        //GIVEN
        val password = ""
        //WHEN
        val result = Validator.passwordValidate(password)
        //THEN
        assertEquals(
            result,
            ValidationResult.Pending
        )
    }

    @Test
    fun `비밀번호가_8에서_16자_사이가_아니면_LengthError를_반환한다`(){
        //GIVEN
        val password = "1234567"
        //WHEN
        val result = Validator.passwordValidate(password)
        //THEN
        assertEquals(
            result,
            ValidationResult.Error(ValidationErrorType.LengthError)
        )
    }

    @Test
    fun `비밀번호가_8에서_16자_사이이고_영문가_없으면_RegexError를_반환한다`(){
        //GIVEN
        val password = "12341234"
        //WHEN
        val result = Validator.passwordValidate(password)
        //THEN
        assertEquals(
            result,
            ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }

    @Test
    fun `비밀번호_8에서_16자_사이이고_숫자가_없으면_RegexError를_반환한다`(){
        //GIVEN
        val password = "가나다라asdf"
        //WHEN
        val result = Validator.passwordValidate(password)
        //THEN
        assertEquals(
            result,
            ValidationResult.Error(ValidationErrorType.RegexError)
        )
    }

    @Test
    fun `비밀번호가_8에서_16자_사이이면서_영어와_숫자가_있으면_Success를_반환한다`(){
        //GIVEN
        val password = "asdf1234"
        //WHEN
        val result = Validator.passwordValidate(password)
        //THEN
        assertEquals(
            result,
            ValidationResult.Success
        )
    }

    @Test
    fun `비밀번호_확인이_빈_문자열이면_Pending를_반환한다`(){
        //GIVEN
        val password = ""
        val password_confirm = ""
        //WHEN
        val result = Validator.passwordConfirmValidate(password,password_confirm)
        //THEN
        assertEquals(
            result,
            ValidationResult.Pending
        )
    }

    @Test
    fun `비밀번호_확인이_비밀번호와_다르면_EqualityError를_반환한다`(){
        //GIVEN
        val password = "asdf"
        val password_confirm = "asdff"
        //WHEN
        val result = Validator.passwordConfirmValidate(password,password_confirm)
        //THEN
        assertEquals(
            result,
            ValidationResult.Error(ValidationErrorType.EqualityError)
        )
    }


    @Test
    fun `비밀번호_확인이_비밀번호와_같은면_Success를_반환한다`(){
        //GIVEN
        val password = "qwerasdf1234"
        val password_confirm = "qwerasdf1234"
        //WHEN
        val result = Validator.passwordConfirmValidate(password,password_confirm)
        //THEN
        assertEquals(
            result,
            ValidationResult.Success
        )
    }
}