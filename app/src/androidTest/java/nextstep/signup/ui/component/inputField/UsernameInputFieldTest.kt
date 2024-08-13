package nextstep.signup.ui.component.inputField

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameInputField(
                username = username.value,
                onValueChange = { username.value = it },
                onValidationSuccess = {}
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // given
        val valueSource = listOf("김컴", "김컴포", "김컴포즈", "김컴포즈다")

        valueSource.forEach {
            // when
            username.value = it

            // then
            composeTestRule
                .onNodeWithText("이름은 2~5자여야 합니다.")
                .assertDoesNotExist()
        }
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // given
        val valueSource = listOf("김", "김컴포즈다아")

        valueSource.forEach {
            // when
            username.value = it

            // then
            composeTestRule
                .onNodeWithText("이름은 2~5자여야 합니다.")
                .assertExists()
        }
    }

    @Test
    fun 사용자_이름이_특수문자를_포함하면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈!"

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertExists()
    }
}
