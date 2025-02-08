package nextstep.signup.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTextInput
import nextstep.signup.R
import nextstep.signup.ui.model.SignUpInputModel
import nextstep.signup.ui.model.SignUpInputType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpInputPasswordValidationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private var model by mutableStateOf(SignUpInputModel())

    private lateinit var lengthErrorMessage: String
    private lateinit var characterTypeErrorMessage: String
    private lateinit var isEqualErrorMessage: String

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpEditFields(
                inputModel = model,
                onUpdateModel = { model = it }
            )
            lengthErrorMessage = stringResource(R.string.password_length_error_message)
            characterTypeErrorMessage =
                stringResource(R.string.password_character_type_error_message)
            isEqualErrorMessage =
                stringResource(R.string.password_is_equal_confirm_error_message)
        }
    }

    @Test
    fun 비밀번호는_최소8자_최대16자_영문_숫자_포함() {
        // when
        val password = "password1234"
        composeTestRule
            .onRoot()
            .onChildAt(SignUpInputType.PASSWORD.ordinal)
            .performTextInput(password)

        //then
        composeTestRule
            .onNodeWithText(lengthErrorMessage)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8자_미만이면_에러메시지_노출() {
        // when
        val password = "pass"
        composeTestRule
            .onRoot()
            .onChildAt(SignUpInputType.PASSWORD.ordinal)
            .performTextInput(password)

        //then
        composeTestRule
            .onNodeWithText(lengthErrorMessage)
            .assertExists()
    }

    @Test
    fun 비밀번호가_16자_초과면_에러메시지_노출() {
        // when
        val password = "password123456789012"
        composeTestRule
            .onRoot()
            .onChildAt(SignUpInputType.PASSWORD.ordinal)
            .performTextInput(password)

        //then
        composeTestRule
            .onNodeWithText(lengthErrorMessage)
            .assertExists()
    }

    @Test
    fun 비밀번호가_최소8자_최대16자_이지만_영문_숫자_포함되지_않으면_에러메시지_노출() {
        // when
        val password = "passwordPass"
        composeTestRule
            .onRoot()
            .onChildAt(SignUpInputType.PASSWORD.ordinal)
            .performTextInput(password)

        //then
        composeTestRule
            .onNodeWithText(characterTypeErrorMessage)
            .assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호_확인값이_동일하면_유효() {
        // when
        val password = "passwordPass"
        model = model.copy(
            password = password,
        )
        composeTestRule
            .onRoot()
            .onChildAt(SignUpInputType.PASSWORD_CONFIRM.ordinal)
            .performTextInput(password)

        //then
        composeTestRule
            .onNodeWithText(isEqualErrorMessage)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호와_비밀번호_확인값이_다르면_에러메시지_노출() {
        // when
        val password = "passwordPass"
        val passwordConfirm = "passwordPassConfirm"
        model = model.copy(
            password = password,
        )
        composeTestRule
            .onRoot()
            .onChildAt(SignUpInputType.PASSWORD_CONFIRM.ordinal)
            .performTextInput(passwordConfirm)

        //then
        composeTestRule
            .onNodeWithText(isEqualErrorMessage)
            .assertExists()
    }
}