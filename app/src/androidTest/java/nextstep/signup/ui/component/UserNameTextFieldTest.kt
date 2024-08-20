package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserNameTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")
    private val validationResult = mutableStateOf(UserNameValidationResult.Valid)

    @Before
    fun setup() {
        composeTestRule.setContent {
            UserNameTextField(
                username = username.value,
                onNameChange = { username.value = it },
                validationResult = validationResult.value
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // given
        val userNameList = listOf("김은", "김은혜", "김은혜다", "김은혜다아")

        userNameList.forEach {
            // when
            username.value = it
            validationResult.value = UserNameValidationResult.Valid

            // then
            composeTestRule
                .onNodeWithText(USERNAME_LENGTH_ERROR)
                .assertDoesNotExist()
        }
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // given
        val userNameList = listOf("김", "김은혜다아아")

        userNameList.forEach {
            // when
            username.value = it
            validationResult.value = UserNameValidationResult.InvalidSize

            // then
            composeTestRule
                .onNodeWithText(USERNAME_LENGTH_ERROR)
                .assertExists()
        }
    }

    @Test
    fun 사용자_이름이_숫자나_기호가_포함되면_에러메시지가_노출된다() {
        // given
        val inputUserName = "김은혜!"

        // when
        username.value = inputUserName
        validationResult.value = UserNameValidationResult.InvalidFormat

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_CHARACTER_ERROR)
            .assertExists()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_INVALID_CHARACTER_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }
}
