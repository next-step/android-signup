package nextstep.signup.screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserNameTextFieldTest {
    private val textFieldValue = mutableStateOf("")
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            NameTextField(
                userName = textFieldValue.value,
                onUserNameChange = { textFieldValue.value = it }
            )
        }
    }

    @Test
    fun 입력이_없다면_아무런에러가_노출되면안된다() {
        textFieldValue.value = ""

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_length_error))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_정상일_때_에러가_없다() {
        textFieldValue.value = "lee"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_length_error))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        textFieldValue.value = "lee-ji-hoon"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_length_error))
            .assertExists()
    }

    @Test
    fun 사용자_이름은_숫자나_기호가_포함될_수_없다() {
        textFieldValue.value = "lee1"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_character_error))
            .assertExists()
    }
}