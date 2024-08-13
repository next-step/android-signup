package nextstep.signup.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.ui.signup.UsernameField
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class UsernameFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private var username by mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameField(
                username = username,
                onUsernameChange = { username = it }
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        username = "박규동"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_invalid_username_length))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username = "박규동입니다"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_invalid_username_length))
            .assertExists()
    }

    @Test
    fun 사용자_이름이_2에서_5자_이면서_숫자나_기호가_포함될_수_없다() {
        // when
        username = "12박규동"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_invalid_username))
            .assertExists()
    }
}
