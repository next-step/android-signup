package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.domain.UsernameValidationResult
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")
    private val validationResult = mutableStateOf(UsernameValidationResult.SUCCESS)

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameTextField(
                username = username.value,
                validationResult = validationResult.value,
                onNameChange = { username.value = it }
            )
        }
    }

    @Test
    fun 유효성_검사가_정상이면_에러메시지가_노출되지_않는다() {
        // when
        username.value = "김컴포즈"
        validationResult.value = UsernameValidationResult.SUCCESS

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(USERNAME_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 유효성_검사가_길이_오류이면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"
        validationResult.value = UsernameValidationResult.INVALID_LENGTH

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 유효성_검사가_포맷_오류이면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈1"
        validationResult.value = UsernameValidationResult.INVALID_FORMAT

        // then
        composeTestRule
            .onNodeWithText(USERNAME_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름이_비어있으면_에러메시지가_노출되지_않는다() {
        // when
        username.value = ""

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_FORMAT_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }
}
