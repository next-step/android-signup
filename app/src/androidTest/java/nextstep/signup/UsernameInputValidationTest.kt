package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.util.ValidationUtil.USER_NAME_IN_SPECIAL_CHARS_ERROR
import nextstep.signup.util.ValidationUtil.USER_NAME_LENGTH_ERROR
import nextstep.signup.util.ValidationUtil.setUsernameErrorMessage
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameInputField(
                value = username.value,
                errorMessage = setUsernameErrorMessage(username.value),
                onValueChange = { username.value = it }
            )
        }
    }

    @Test
    fun `사용자_이름은_2에서_5자여야_한다`() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USER_NAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(USER_NAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun `사용자_이름에_특수문자가_없어야_한다`() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USER_NAME_IN_SPECIAL_CHARS_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun `사용자_이름에_특수문자가_있으면_에러메세지가_노출된다`() {
        // when
        username.value = "김컴포즈!"

        // then
        composeTestRule
            .onNodeWithText(USER_NAME_IN_SPECIAL_CHARS_ERROR)
            .assertExists()
    }
}
