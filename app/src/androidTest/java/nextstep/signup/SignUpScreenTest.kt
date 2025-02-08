package nextstep.signup

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 이름을_입력하면_입력된_이름이_보인다() {
        // given
        val userName = "ChoiSeongHoon"
        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(userName)

        // then
        composeTestRule
            .onNodeWithText(userName)
            .assertExists()
    }

    @Test
    fun 이메일을_입력하면_입력된_이름이_보인다() {
        // given
        val emailInput = "ver@test.com"
        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput(emailInput)

        // then
        composeTestRule
            .onNodeWithText(emailInput)
            .assertExists()
    }

    @Test
    fun 비밀번호를_입력해도_입력된_비밀번호가_보이지_않는다() {
        // given
        val passwordInput = "a12345678!"
        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(passwordInput)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_확인을_입력해도_입력된_비밀번호_확인이_보이지_않는다() {
        // given
        val passwordInput = "a12345678!"
        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(passwordInput)
            .assertDoesNotExist()
    }

    @Test
    fun 회원_이름_길이가_1이면_이름_유효성_검사_실패_메세지가_보인다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val username = "일" // 이름 길이 1
        val invalidSupportingText = context.getString(R.string.sign_up_invalid_user_name_length)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(username)

        // then
        composeTestRule
            .onNodeWithText(invalidSupportingText)
            .assertExists()
    }

    @Test
    fun 회원_이름_길이가_6이면_이름_유효성_검사_실패_메세지가_보인다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val username = "일이삼사오육" // 이름 길이 6
        val invalidSupportingText = context.getString(R.string.sign_up_invalid_user_name_length)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(username)

        // then
        composeTestRule
            .onNodeWithText(invalidSupportingText)
            .assertExists()
    }

    @Test
    fun 회원_이름_길이가_3이면_이름_유효성_검사_실패_메세지가_보이지_않는다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val username = "최성훈" // 이름 길이 3
        val invalidSupportingText = context.getString(R.string.sign_up_invalid_user_name_length)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(username)

        // then
        composeTestRule
            .onNodeWithText(invalidSupportingText)
            .assertDoesNotExist()
    }

    @Test
    fun 이름에는_숫자가_포함될_수_없다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val username = "최성2" // 이름에 숫자 포함
        val invalidSupportingText = context.getString(R.string.sign_up_invalid_user_name_letter)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(username)

        // then
        composeTestRule
            .onNodeWithText(invalidSupportingText)
            .assertExists()
    }

    @Test
    fun 이름에는_문자가_포함될_수_없다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val username = "최성^" // 이름에 문자 포함
        val invalidSupportingText = context.getString(R.string.sign_up_invalid_user_name_letter)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(username)

        // then
        composeTestRule
            .onNodeWithText(invalidSupportingText)
            .assertExists()
    }

    @Test
    fun 잘못된_이메일_입력시_유효성_검사_실패_메세지가_보인다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val emailInput = "invalidEmail" // 잘못된 이메일 형식
        val invalidEmailMessage = context.getString(R.string.sign_up_invalid_email_format)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput(emailInput)

        // then
        composeTestRule
            .onNodeWithText(invalidEmailMessage)
            .assertExists()
    }

    @Test
    fun 올바른_이메일_입력시_유효성_검사_실패_메세지가_보이지_않는다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val emailInput = "valid@test.com" // 올바른 이메일 형식
        val invalidEmailMessage = context.getString(R.string.sign_up_invalid_email_format)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Email")
            .performTextInput(emailInput)

        // then
        composeTestRule
            .onNodeWithText(invalidEmailMessage)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_길이가_8미만이면_비밀번호_유효성_검사_실패_메세지가_보인다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val passwordInput = "Abc12!" // 길이 6 (< 8)
        val invalidPasswordLengthMessage =
            context.getString(R.string.sign_up_invalid_password_length)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordLengthMessage)
            .assertExists()
    }

    @Test
    fun 비밀번호_길이가_16초과이면_비밀번호_유효성_검사_실패_메세지가_보인다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val passwordInput = "Abc12345Abc12345X" // 길이 17 (> 16)
        val invalidPasswordLengthMessage =
            context.getString(R.string.sign_up_invalid_password_length)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordLengthMessage)
            .assertExists()
    }

    @Test
    fun 비밀번호에_숫자가_없으면_비밀번호_유효성_검사_실패_메세지가_보인다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val passwordInput = "Abcdefgh" // 숫자 없음
        val invalidPasswordLetterMessage =
            context.getString(R.string.sign_up_invalid_password_letter)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordLetterMessage)
            .assertExists()
    }

    @Test
    fun 비밀번호에_영문이_없으면_비밀번호_유효성_검사_실패_메세지가_보인다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val passwordInput = "12345678" // 영문 없음
        val invalidPasswordLetterMessage =
            context.getString(R.string.sign_up_invalid_password_letter)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordLetterMessage)
            .assertExists()
    }

    @Test
    fun 올바른_비밀번호_입력시_유효성_검사_실패_메세지가_보이지_않는다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val passwordInput = "Abcd1234"
        val invalidPasswordLengthMessage =
            context.getString(R.string.sign_up_invalid_password_length)
        val invalidPasswordLetterMessage =
            context.getString(R.string.sign_up_invalid_password_letter)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordLengthMessage)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText(invalidPasswordLetterMessage)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_다르면_유효성_검사_실패_메세지가_보인다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val invalidPasswordConfirmMessage =
            context.getString(R.string.sign_up_invalid_password_confirm)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("ABCD1234")

        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput("abcd1234!")

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordConfirmMessage)
            .assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_같으면_유효성_검사_실패_메세지가_보이지_않는다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val invalidPasswordConfirmMessage =
            context.getString(R.string.sign_up_invalid_password_confirm)

        val passwordConfirm = "abcd1234"

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordConfirm)

        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput(passwordConfirm)

        // then
        composeTestRule
            .onNodeWithText(invalidPasswordConfirmMessage)
            .assertDoesNotExist()
    }
}
