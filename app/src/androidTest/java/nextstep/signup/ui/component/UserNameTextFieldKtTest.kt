package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserNameTextFieldKtTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()
    private val userName = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            UserNameTextField(
                userName = userName.value,
                onValueChange = { value ->
                    userName.value = value
                }
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // given

        // when
        userName.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // given

        // when
        userName.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_숫자가_포함되면_에러메시지가_노출된다() {
        // given

        // when
        userName.value = "1234"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_CANNOT_CONTAIN_NUMBERS_OR_SYMBOLS)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_기호가_포함되면_에러메시지가_노출된다() {
        // given

        // when
        userName.value = "!@#$"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_CANNOT_CONTAIN_NUMBERS_OR_SYMBOLS)
            .assertExists()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_CANNOT_CONTAIN_NUMBERS_OR_SYMBOLS = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }
}
