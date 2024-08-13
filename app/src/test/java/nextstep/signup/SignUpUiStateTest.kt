package nextstep.signup


import junit.framework.TestCase.assertTrue
import org.junit.Test

class SignUpUiStateTest {

    @Test
    fun 이름의_길이가_2자에서_5자_사이가_아니면_이름_오류_상태이다() {
        val signUpUiState = SignUpUiState(username = "asdfff")
        assertTrue(signUpUiState.isUsernameLengthError)
    }

    @Test
    fun 이름에_숫자나_기호가_포함되면_이름_오류_상태이다() {
        val signUpUiState = SignUpUiState(username = "asd1@")
        assertTrue(signUpUiState.isUsernameFormatError)
    }

    @Test
    fun 이메일이_잘못된_형식이면_이메일_오류_상태이다() {
        val signUpUiState = SignUpUiState(email = "asd1@")
        assertTrue(signUpUiState.isEmailFormatError)
    }

    @Test
    fun 비밀번호의_길이가_8자에서_16자_사이가_비밀번호_오류_상태이다() {
        val signUpUiState = SignUpUiState(password = "1234")
        assertTrue(signUpUiState.isPasswordValidationError)
    }

    @Test
    fun 비밀번호에_영문이나_숫자가_포함되어_있지_않으면_비밀번호_오류_상태이다() {
        val signUpUiState = SignUpUiState(password = "asdf")
        assertTrue(signUpUiState.isPasswordValidationError)
    }

    @Test
    fun 비밀번호와_비밀번호_확인값이_일치하지__않으면_비밀번호_확인값_오류_상태이다() {
        val signUpUiState = SignUpUiState(password = "asdf1234", passwordConfirm = "1234asdf")
        assertTrue(signUpUiState.isPasswordMismatchError)
    }

    @Test
    fun 모든_필드가_비어있지_않고_유효성_검사_성공시_버튼은_활성화_상태이다() {
        val signUpUiState = SignUpUiState(username = "asd", email = "123@gmail.com", password = "asdf1234", passwordConfirm = "asdf1234")
        assertTrue(signUpUiState.isSignUpButtonEnabled)
    }

}