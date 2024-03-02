package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordInputField(
                password = password.value,
                onPasswordChange = { password.value = it }
            )
        }
    }

    @Test
    fun 숫자와_영문_으로_구성된_8_15내의_비밀번호_입력시_에러메세지가_보이지않는다() {
        // given
        val inputPassword = "englishAnd123"

        // when
        password.value = inputPassword

        //then
        composeTestRule
            .onNodeWithText(getString(id = R.string.signup_error_password_length))
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText(getString(id = R.string.signup_error_password_must_contain_text))
            .assertDoesNotExist()
        composeTestRule
            .onAllNodesWithText('\u2022'.toString().repeat(inputPassword.length))
            .onFirst()
    }

    @Test
    fun 비밀번호을_8보다작거나_16보다크게_입력하면_에러메세지가_보인다() {
        // given
        val inputPassword = "16자보다 큰 비밀번호 테스트 입니다. 비밀번호 몇자리"

        // when
        password.value = inputPassword

        //then
        composeTestRule
            .onNodeWithText(getString(id = R.string.signup_error_password_length))
            .assertExists()
    }

    @Test
    fun 비밀번호에_숫자나_기호와_포함되지않으_에러메세지가_보인다() {
        // given
        val inputPassword = "inputPassword"

        // when
        password.value = inputPassword

        //then
        composeTestRule
            .onNodeWithText(getString(id = R.string.signup_error_password_must_contain_text))
            .assertExists()
    }
}
