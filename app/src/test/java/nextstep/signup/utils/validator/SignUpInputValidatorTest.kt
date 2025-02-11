package nextstep.signup.utils.validator


import junit.framework.TestCase.assertTrue
import nextstep.signup.ui.model.SignUpInputModel
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith


@RunWith(Enclosed::class)
class SignUpInputValidatorTest {

    class UsernameTest {
        @Test
        fun username_2자_이상_5자_이하_영문_한글_포함시_유효() {
            // given
            val username = "상아"
            val model = SignUpInputModel().copy(username = username)

            // then
            assertTrue(UsernameValidator.isValid(model))
        }

        @Test
        fun username_2자_미만_에러() {
            // given
            val username = "상"
            val model = SignUpInputModel().copy(username = username)

            // then
            assertTrue(UsernameValidator.hasError(model))
            assertTrue(UsernameValidator.errorType(model) == ValidationRule.ErrorType.OUT_OF_LENGTH_RANGE)
        }

        @Test
        fun username_5자_초과_에러() {
            // given
            val username = "상아야안녕하세요"
            val model = SignUpInputModel().copy(username = username)

            // then
            assertTrue(UsernameValidator.hasError(model))
            assertTrue(UsernameValidator.errorType(model) == ValidationRule.ErrorType.OUT_OF_LENGTH_RANGE)
        }

        @Test
        fun username_숫자_포함_에러() {
            // given
            val username = "상아34"
            val model = SignUpInputModel().copy(username = username)

            // then
            assertTrue(UsernameValidator.hasError(model))
            assertTrue(UsernameValidator.errorType(model) == ValidationRule.ErrorType.INVALID_CHARACTER_TYPE_INCLUDED)
        }
    }

    class EmailTest {
        @Test
        fun email_형식_유효() {
            // given
            val email = "123@abc.com"
            val model = SignUpInputModel().copy(email = email)

            // then
            assertTrue(EmailValidator.isValid(model))
        }

        @Test
        fun email_형식_에러() {
            // given
            val email = "12"
            val model = SignUpInputModel().copy(email = email)

            // then
            assertTrue(EmailValidator.hasError(model))
            assertTrue(EmailValidator.errorType(model) == ValidationRule.ErrorType.INVALID_FORM)
        }
    }

    class PasswordTest {
        @Test
        fun password_8자_이상_15자_이하_영문_숫자_포함() {
            // given
            val password = "password234"
            val model = SignUpInputModel().copy(
                password = password,
            )

            // then
            assertTrue(PasswordValidator.isValid(model))
        }

        @Test
        fun password_8자_미만_에러() {
            // given
            val password = "1234567"
            val model = SignUpInputModel().copy(
                password = password,
            )

            // then
            assertTrue(PasswordValidator.hasError(model))
            assertTrue(PasswordValidator.errorType(model) == ValidationRule.ErrorType.OUT_OF_LENGTH_RANGE)
        }

        @Test
        fun password_16자_초과_에러() {
            // given
            val password = "12345678901234567"
            val model = SignUpInputModel().copy(
                password = password,
            )

            // then
            assertTrue(PasswordValidator.hasError(model))
            assertTrue(PasswordValidator.errorType(model) == ValidationRule.ErrorType.OUT_OF_LENGTH_RANGE)
        }

        @Test
        fun password_글자수는_유효하지만_숫자만_포함_에러() {
            // given
            val password = "123456789012"
            val model = SignUpInputModel().copy(
                password = password,
            )

            // then
            assertTrue(PasswordValidator.hasError(model))
            assertTrue(PasswordValidator.errorType(model) == ValidationRule.ErrorType.LACK_OF_CHARACTER_TYPE)
        }

        @Test
        fun password_글자수_유효_영문만_포함_에러() {
            // given
            val password = "abcdefghjk"
            val model = SignUpInputModel().copy(
                password = password,
            )

            // then
            assertTrue(PasswordValidator.hasError(model))
            assertTrue(PasswordValidator.errorType(model) == ValidationRule.ErrorType.LACK_OF_CHARACTER_TYPE)
        }
    }

    class PasswordConfirmTest {
        @Test
        fun password_passwordConfirm_동일하면_유효() {
            // given
            val password = "password"
            val model = SignUpInputModel().copy(
                password = password,
                passwordConfirm = password,
            )

            // then
            assertTrue(PasswordConfirmValidator.isValid(model))
        }

        @Test
        fun password_passwordConfirm_다르면_에러() {
            // given
            val password = "password"
            val passwordConfirm = "passwordConfirm"
            val model = SignUpInputModel().copy(
                password = password,
                passwordConfirm = passwordConfirm,
            )

            // then
            assertTrue(PasswordConfirmValidator.hasError(model))
            assertTrue(PasswordConfirmValidator.errorType(model) == ValidationRule.ErrorType.NOT_EQUAL_VALUES)
        }
    }
}