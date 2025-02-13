package nextstep.signup.ui.component.textfield

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.util.NameValidator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameTextFieldValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameTextField(
                inputValue = username.value,
                onInputChange = { username.value = it },
                validResult = NameValidator.validate(username.value),
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        username.value = "jieun"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        val invalidNames = listOf(
            "",
            "j",
            "jeonjieun",
        )

        invalidNames.forEach { invalidName ->
            // when
            username.value = invalidName

            // then
            composeTestRule
                .onNodeWithText(USERNAME_LENGTH_ERROR)
                .assertExists()
        }
    }

    @Test
    fun 사용자_이름에_숫자나_기호가_포함되면_에러메시지가_노출된다() {
        val invalidNames = listOf(
            "je1",
            "je!",
        )

        invalidNames.forEach { invalidName ->
            // when
            username.value = invalidName

            // then
            composeTestRule
                .onNodeWithText(USERNAME_INVALID_FORMAT_ERROR)
                .assertExists()

        }
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_INVALID_FORMAT_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }
}