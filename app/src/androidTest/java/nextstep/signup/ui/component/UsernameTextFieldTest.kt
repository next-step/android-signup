package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")
    private val usernameValidation = UsernameValidation()

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameTextField(
                value = username.value,
                onValueChange = { username.value = it },
                validationResult = usernameValidation.isValid(username.value),
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(ERROR_USERNAME_LENGTH)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(ERROR_USERNAME_LENGTH)
            .assertExists()
    }

    @Test
    fun 사용자_이름이_비어있으면_에러메시지가_노출되지_않는다() {
        // when
        username.value = ""

        // then
        composeTestRule
            .onNodeWithText(ERROR_USERNAME_LENGTH)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름에_숫자나_기호가_포함되지_않는다() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(ERROR_USERNAME_FORMAT)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름에_숫자가_포함되면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈1"

        // then
        composeTestRule
            .onNodeWithText(ERROR_USERNAME_FORMAT)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_기호가_포함되면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈!"

        // then
        composeTestRule
            .onNodeWithText(ERROR_USERNAME_FORMAT)
            .assertExists()
    }

    companion object {
        private const val ERROR_USERNAME_LENGTH = "이름은 2~5자여야 합니다."
        private const val ERROR_USERNAME_FORMAT = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }
}
