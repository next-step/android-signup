package nextstep.signup.ui.component

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import nextstep.signup.core.validation.NameValidator
import nextstep.signup.utils.assertDoesNotExist
import nextstep.signup.utils.assertExists
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserNameTextFieldTest {
    private val textFieldValue = mutableStateOf("")
    private val nameValidationResult by derivedStateOf {
        NameValidator().validate(textFieldValue.value)
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            NameTextField(
                userName = textFieldValue.value,
                nameValidationResult = nameValidationResult,
                onUserNameChange = { textFieldValue.value = it },
            )
        }
    }

    @Test
    fun 입력이_없다면_아무런에러가_노출되면안된다() {
        textFieldValue.value = ""

        composeTestRule.assertDoesNotExist("이름은 2~5자여야 합니다.")
        composeTestRule.assertDoesNotExist("이름에는 숫자나 기호가 포함될 수 없습니다.")
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        textFieldValue.value = "lee-ji-hoon"

        composeTestRule.assertExists("이름은 2~5자여야 합니다.")
    }
}
