package nextstep.signup.screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.core.validation.NameValidationResult
import nextstep.signup.ui.component.NameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserNameTextFieldTest {
    private val textFieldValue = mutableStateOf("")
    private val userNameValidationResult = mutableStateOf(NameValidationResult.VALID)
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            NameTextField(
                userName = textFieldValue.value,
                onUserNameChange = { textFieldValue.value = it },
                nameValidationResult = userNameValidationResult.value
            )
        }
    }

    @Test
    fun 입력이_없다면_아무런에러가_노출되면안된다() {
        textFieldValue.value = ""
        userNameValidationResult.value = NameValidationResult.VALID

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_length_error))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_정상일_때_에러가_없다() {
        textFieldValue.value = "lee"
        userNameValidationResult.value = NameValidationResult.VALID

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_length_error))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        textFieldValue.value = "lee-ji-hoon"
        userNameValidationResult.value = NameValidationResult.LENGTH_ERROR

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_length_error))
            .assertExists()
    }

    @Test
    fun 사용자_이름은_숫자나_기호가_포함될_수_없다() {
        textFieldValue.value = "lee1"
        userNameValidationResult.value = NameValidationResult.CHARACTER_ERROR

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_character_error))
            .assertExists()
    }
}
