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

class SignUpInputEmailValidationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private var model by mutableStateOf(SignUpInputModel())

    private val errorMessage = "이메일 형식이 올바르지 않습니다."

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpEditFields(
                inputModel = model,
                onUpdateModel = { model = it }
            )
        }
    }

    @Test
    fun 이메일_형식이_유효() {
        // when
        val email = "123@gmail.com"
        model = model.copy(email = email)

        //then
        composeTestRule
            .onNodeWithText(errorMessage)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_유효하지_않으면_에러메시지_노출() {
        // when
        val email = "123om"
        model = model.copy(email = email)

        //then
        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }
}