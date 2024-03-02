package nextstep.signup.ui.component

import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameTextInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val userName = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameTextInputField(
                userName = userName.value,
                onNameChange = { userName.value = it }
            )
        }
    }

    @Test
    fun 사용자_이름을_2_5자_사이의_문자를_입력하면_에러메세지가_안보인다() {
        // given
        val inputUserName = "남잭슨"

        // when
        userName.value = inputUserName

        //then
        composeTestRule
            .onNodeWithText(getString(id = R.string.signup_error_name_length))
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText(getString(id = R.string.signup_error_name_must_not_contain))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름을_2보다작거나_5보다크게_입력하면_에러메세지가_보인다() {
        // given
        val inputUserName = "남잭슨 초과된 문자열"

        // when
        userName.value = inputUserName

        //then
        composeTestRule
            .onNodeWithText(getString(id = R.string.signup_error_name_length))
            .assertExists()
    }

    @Test
    fun 사용자_이름에_숫자나_기호가_포함되면_에러메세지가_보인다() {
        // given
        val inputUserName = "남잭슨@2"

        // when
        userName.value = inputUserName

        //then
        composeTestRule
            .onNodeWithText(getString(id = R.string.signup_error_name_must_not_contain))
            .assertExists()
    }
}

internal fun getString(@StringRes id: Int): String {
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    return context.resources.getString(id)
}
