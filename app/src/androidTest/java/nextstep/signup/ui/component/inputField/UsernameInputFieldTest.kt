package nextstep.signup.ui.component.inputField

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val username = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameInputField(username = username.value, { username.value = it })
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // given
        val errorMessage = context.getString(R.string.sign_up_username_input_validation_length_message)
        val valueSource = listOf("김컴", "김컴포", "김컴포즈", "김컴포즈다")

        valueSource.forEach {
            // when
            username.value = it

            // then
            composeTestRule
                .onNodeWithText(errorMessage)
                .assertDoesNotExist()
        }
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // given
        val errorMessage = context.getString(R.string.sign_up_username_input_validation_length_message)
        val valueSource = listOf("김", "김컴포즈다아")

        valueSource.forEach {
            // when
            username.value = it

            // then
            composeTestRule
                .onNodeWithText(errorMessage)
                .assertExists()
        }
    }

    @Test
    fun 사용자_이름이_특수문자를_포함하면_에러메시지가_노출된다() {
        // given
        val errorMessage = context.getString(R.string.sign_up_username_input_validation_message)

        // when
        username.value = "김컴포즈!"

        // then
        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }
}
