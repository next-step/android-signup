package nextstep.signup.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SignUpUserInfoTest {
    @Test
    fun `모든_필드가_채워졌을_때_isNotContainBlank_반환값은_true`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "JohnDoe",
            email = "johndoe@example.com",
            password = "Password123",
            passwordConfirm = "Password123"
        )

        assertTrue(signUpUserInfo.isNotContainBlank)
    }

    @Test
    fun `어떤_필드라도_비어있을_때_isNotContainBlank_반환값은_false`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "JohnDoe",
            email = "",
            password = "Password123",
            passwordConfirm = "Password123"
        )

        assertFalse(signUpUserInfo.isNotContainBlank)
    }

    @Test
    fun `이름이_올바를_때_nameError_반환값은_None`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "John",
            email = "johndoe@example.com",
            password = "Password123",
            passwordConfirm = "Password123"
        )

        assertEquals(NameError.None, signUpUserInfo.nameError)
    }

    @Test
    fun `이름이_너무_짧거나_길_때_nameError_반환값은_LengthError`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "J",
            email = "johndoe@example.com",
            password = "Password123",
            passwordConfirm = "Password123"
        )

        assertEquals(NameError.Length, signUpUserInfo.nameError)
    }

    @Test
    fun `이름에_숫자나_기호가_포함될_때_nameError_반환값은_NumberOrSymbol`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "John1",
            email = "beom@example.com",
            password = "Password123",
            passwordConfirm = "Password123"
        )

        assertEquals(NameError.NumberOrSymbol, signUpUserInfo.nameError)
    }

    @Test
    fun `이름에_숫자나_기호가_포함되면서_길이가_5초과일_때_nameError_반환값은_Length`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "BeomSeok123",
            email = "beom@example.com",
            password = "Password123",
            passwordConfirm = "Password123"
        )

        assertEquals(NameError.Length, signUpUserInfo.nameError)
    }

    @Test
    fun `이메일_형식이_올바를_때_emailError_반환값은_None`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "JohnDoe",
            email = "beom@example.com",
            password = "Password123",
            passwordConfirm = "Password123"
        )

        assertEquals(EmailError.None, signUpUserInfo.emailError)
    }

    @Test
    fun `이메일_형식이_잘못됐을_때_emailError_반환값은_EmailFormat`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "Beom",
            email = "beom@example",
            password = "Password123",
            passwordConfirm = "Password123"
        )

        assertEquals(EmailError.EmailFormat, signUpUserInfo.emailError)
    }

    @Test
    fun `비밀번호_형식이_올바를_때_passwordError_반환값은_None`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "Beom",
            email = "beom@example.com",
            password = "Password123",
            passwordConfirm = "Password123"
        )

        assertEquals(PasswordError.None, signUpUserInfo.passwordError)
    }

    @Test
    fun `비밀번호_길이가_잘못됐을_때_passwordError_반환값은_PasswordLength`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "Beom",
            email = "beom@example.com",
            password = "Pass1",
            passwordConfirm = "Pass1"
        )

        assertEquals(PasswordError.PasswordLength, signUpUserInfo.passwordError)
    }

    @Test
    fun `비밀번호_형식이_잘못됐을_때_passwordError_반환값은_PasswordFormat`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "Beom",
            email = "beom@example.com",
            password = "password",
            passwordConfirm = "password"
        )

        assertEquals(PasswordError.PasswordFormat, signUpUserInfo.passwordError)
    }

    @Test
    fun `비밀번호와_비밀번호_확인이_일치하지_않을_때_passwordConfirmError_반환값은_PasswordEqual`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "Beom",
            email = "Beom@example.com",
            password = "Password123",
            passwordConfirm = "Password456"
        )

        assertEquals(PasswordConfirmError.PasswordEqual, signUpUserInfo.passwordConfirmError)
    }

    @Test
    fun `모든_필드가_유효할_때_isAllFieldsValid_반환값은_true`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "Beom",
            email = "Beom@example.com",
            password = "Password123",
            passwordConfirm = "Password123"
        )

        assertTrue(signUpUserInfo.isAllFieldsValid)
    }

    @Test
    fun `어떤_필드라도_유효하지_않을_때_isAllFieldsValid_반환값은_false`() {
        val signUpUserInfo = SignUpUserInfo(
            username = "J",
            email = "johndoe@example",
            password = "Pass1",
            passwordConfirm = "Pass1"
        )

        assertFalse(signUpUserInfo.isAllFieldsValid)
    }
}