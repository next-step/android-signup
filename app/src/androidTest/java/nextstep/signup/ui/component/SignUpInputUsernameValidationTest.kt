package nextstep.signup.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.R
import nextstep.signup.ui.model.SignUpInputModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpInputUsernameValidationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private var model by mutableStateOf(SignUpInputModel())

    private lateinit var lengthErrorMessage: String
    private lateinit var characterTypeErrorMessage: String

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpEditFields(
                inputModel = model,
                onUpdateModel = { model = model.copy(it.username) }
            )

            lengthErrorMessage = stringResource(R.string.username_length_error_message)
            characterTypeErrorMessage =
                stringResource(R.string.username_character_type_error_message)
        }
    }

    @Test
    fun 사용자_이름은_최소2자_최대5자() {
        // when
        val username = "상아당"
        model = model.copy(username = username)

        //then
        composeTestRule
            .onNodeWithText(lengthErrorMessage)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2자_미만이면_에러메시지_노출() {
        // when
        val username = "상"
        model = model.copy(username = username)

        //then
        composeTestRule
            .onNodeWithText(lengthErrorMessage)
            .assertExists()
    }

    @Test
    fun 사용자_이름이_5자_초과하면_에러메시지_노출() {
        // when
        val username = "상아이름이길어요"
        model = model.copy(username = username)

        //then
        composeTestRule
            .onNodeWithText(lengthErrorMessage)
            .assertExists()
    }

    @Test
    fun 사용자_이름엔_숫자_기호는_제외() {
        // when
        val username = "숫자4"
        model = model.copy(username = username)

        //then
        composeTestRule
            .onNodeWithText(characterTypeErrorMessage)
            .assertExists()
    }

}