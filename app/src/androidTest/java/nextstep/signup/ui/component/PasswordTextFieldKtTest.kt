package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldKtTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()
    private val password = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                password = password.value,
                onValueChange = { value ->
                    password.value = value
                }
            )
        }
    }

    @Test
    fun 패스워드가_8자보다_작으면_에러메시지가_노출됩니다() {
        // given

        // when
        password.value = "1234567"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 패스워드가_16자보다_크면_에러메시지가_노출됩니다() {
        // given

        // when
        password.value = "12345678901234567"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호는_영어와_숫자를_포함해야_합니다() {
        // given

        // when
        password.value = "1234qwer"

        // then
        composeTestRule
            .onNodeWithText(YOUR_PASSWORD_MUST_CONTAIN_ENGLISH_AND_NUMBERS)
            .assertDoesNotExist()
    }

    @Test
    fun 패스워드가_비어있으면_에러메시지가_노출되지_않는다() {
        // given

        // when
        password.value = ""

        // then
        composeTestRule
            .onNodeWithText(YOUR_PASSWORD_MUST_CONTAIN_ENGLISH_AND_NUMBERS)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val YOUR_PASSWORD_MUST_CONTAIN_ENGLISH_AND_NUMBERS = "비밀번호는 영어와 숫자를 포함해야 합니다."
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자이어야 합니다."
    }
}
