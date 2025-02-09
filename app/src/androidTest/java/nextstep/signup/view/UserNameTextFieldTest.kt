package nextstep.signup.view

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserNameTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val username = mutableStateOf("")

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        composeTestRule.setContent {
            UserNameTextField(
                userName = username.value,
                onValueChange = {
                    username.value = it
                }
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_username_length_error))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_username_length_error))
            .assertExists()
    }

    @Test
    fun 사용자_이름에는_숫자나_기호가_포함될_수_없다() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_username_format_error))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름에는_숫자나_기호가_포함되면_에러메시지가_노출된다() {
        // when
        username.value = "김123"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_username_format_error))
            .assertExists()
    }


}