package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import nextstep.signup.core.validation.NameValidationResult
import nextstep.signup.utils.assertExists
import nextstep.signup.utils.assertDoesNotExist
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserNameTextFieldTest {
    private val textFieldValue = mutableStateOf("")
    private val userNameValidationResult = mutableStateOf(NameValidationResult.VALID)

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

        composeTestRule.assertDoesNotExist("이름은 2~5자여야 합니다.")
        composeTestRule.assertDoesNotExist("이름에는 숫자나 기호가 포함될 수 없습니다.")
    }

    @Test
    fun 사용자_이름이_정상일_때_에러가_없다() {
        textFieldValue.value = "lee"
        userNameValidationResult.value = NameValidationResult.VALID

        composeTestRule.assertDoesNotExist("이름은 2~5자여야 합니다.")
        composeTestRule.assertDoesNotExist("이름에는 숫자나 기호가 포함될 수 없습니다.")
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        textFieldValue.value = "lee-ji-hoon"
        userNameValidationResult.value = NameValidationResult.LENGTH_ERROR

        composeTestRule.assertExists("이름은 2~5자여야 합니다.")
    }

    @Test
    fun 사용자_이름은_숫자나_기호가_포함될_수_없다() {
        textFieldValue.value = "lee1"
        userNameValidationResult.value = NameValidationResult.CHARACTER_ERROR

        composeTestRule.assertExists("이름에는 숫자나 기호가 포함될 수 없습니다.")
    }
}
