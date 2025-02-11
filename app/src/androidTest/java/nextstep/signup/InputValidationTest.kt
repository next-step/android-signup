package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import nextstep.signup.signup.component.SignUpInputForm
import nextstep.signup.signup.util.ValidationUtil.isNameValid
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private var nameFieldValue = mutableStateOf(TextFieldValue(""))
    private var nameErrorMessage = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_name),
                keyboardType = KeyboardType.Text,
                textFieldValue = nameFieldValue.value,
                onValueChange = { newTextFieldValue ->
                    nameFieldValue.value = newTextFieldValue
                    nameErrorMessage.value = isNameValid(newTextFieldValue.text)
                },
                errorMessage = nameErrorMessage.value
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        nameFieldValue.value = TextFieldValue("김컴포즈")
        nameErrorMessage.value = isNameValid(nameFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        nameFieldValue.value = TextFieldValue("김컴포즈입니다")
        nameErrorMessage.value = isNameValid(nameFieldValue.value.text)
        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
    }
}
