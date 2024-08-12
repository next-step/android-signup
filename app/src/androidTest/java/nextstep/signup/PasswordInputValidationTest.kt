package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.theme.screen.PasswordConfirmTextFieldScreen
import nextstep.signup.ui.theme.screen.PasswordTextFieldScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
    }

    @Test
    fun 비밀번호는_8_16_자여야_한다() {
        // when
        composeTestRule.setContent {
            PasswordTextFieldScreen("Password", "1q2w3e4r") {
            }
        }

        // then
        composeTestRule
            .onNodeWithText( "비밀번호는 8~16자여야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8자보다_작으면_에러노출() {
        // when
        composeTestRule.setContent {
            PasswordTextFieldScreen("Password", "1q2w3e4") {
            }
        }

        // then
        composeTestRule
            .onNodeWithText( "비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호가_16자보다_크면_에러노출() {
        // when
        composeTestRule.setContent {
            PasswordTextFieldScreen("Password", "1q2w3e4r5t6y7u8i9") {
            }
        }

        // then
        composeTestRule
            .onNodeWithText( "비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함해야한다() {
        // when
        composeTestRule.setContent {
            PasswordTextFieldScreen("Password", "1q2w3e4r") {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호는_영문이_없으면_에러메시지_노출() {
        // when
        composeTestRule.setContent {
            PasswordTextFieldScreen("Password", "12345678") {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호는_숫자가_없으면_에러메시지_노출() {
        // when
        composeTestRule.setContent {
            PasswordTextFieldScreen("Password", "abcdefghi") {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호와_확인비밀번호는_일치해야_한다() {
        // when
        val password = "1q2w3e4r"
        val passwordConfirm = "1q2w3e4r"
        composeTestRule.setContent {
            Column {
                PasswordTextFieldScreen("Password", password) {
                }
                PasswordConfirmTextFieldScreen("Password Confirm", passwordConfirm, password) {
                }
            }
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호와_확인비밀번호는_일치하지않으면_에러메시지_노출() {
        // when
        val password = "1q2w3e4r"
        val passwordConfirm = "1q2w3e4"
        composeTestRule.setContent {
            Column {
                PasswordTextFieldScreen("Password", password) {
                }
                PasswordConfirmTextFieldScreen("Password Confirm", passwordConfirm, password) {
                }
            }
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertExists()
    }
}
