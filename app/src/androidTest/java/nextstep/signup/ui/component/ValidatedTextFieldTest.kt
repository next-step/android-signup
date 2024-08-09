package nextstep.signup.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.core.validation.EmailValidator
import nextstep.signup.core.validation.NameValidator
import nextstep.signup.core.validation.PasswordMatchValidator
import nextstep.signup.core.validation.PasswordValidator
import nextstep.signup.core.validation.Validator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ValidatedTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val textFieldValue = mutableStateOf("")
    private val validator = mutableStateOf<Validator>(NameValidator())

    @Before
    fun setup() {
        composeTestRule.setContent {
            ValidatedTextField(
                field = InputFieldModel(
                    value = textFieldValue.value,
                    onValueChange = { textFieldValue.value = it },
                    validator = validator.value,
                    label = { Text("라벨") }
                )
            )
        }
    }

    @Test
    fun 사용자_이름이_정상일_때_에러가_없다() {
        validator.value = NameValidator()
        textFieldValue.value = "lee"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_length_error))
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_character_error))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        validator.value = NameValidator()
        textFieldValue.value = "lee-ji-hoon"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_length_error))
            .assertExists()
    }

    @Test
    fun 사용자_이름은_숫자나_기호가_포함될_수_없다() {
        validator.value = NameValidator()
        textFieldValue.value = "lee1"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_character_error))
            .assertExists()
    }

    @Test
    fun 비밀번호가_정상_길이_내에_있을_때_에러가_없다() {
        validator.value = PasswordValidator()
        textFieldValue.value = "Valid1234"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_length_error))
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함할_때_에러가_없다() {
        validator.value = PasswordValidator()
        textFieldValue.value = "Password123"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_complexity_error))
            .assertDoesNotExist()
    }


    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        validator.value = PasswordValidator()
        textFieldValue.value = "short1"


        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_length_error))
            .assertExists()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함해야_한다() {
        validator.value = PasswordValidator()
        textFieldValue.value = "Password"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_complexity_error))
            .assertExists()
    }

    @Test
    fun 비밀번호가_일치할_때_에러가_없다() {
        val originalPassword = "CorrectPassword123"
        validator.value = PasswordMatchValidator(originalPassword)
        textFieldValue.value = "CorrectPassword123"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_mismatch_error))
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_일치해야_한다() {
        val originalPassword = "CorrectPassword123"
        validator.value = PasswordMatchValidator(originalPassword)
        textFieldValue.value = "WrongPassword"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_mismatch_error))
            .assertExists()
    }

    @Test
    fun 이메일은_올바른_형식이어야_한다() {
        validator.value = EmailValidator()
        textFieldValue.value = "invalid_email"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_invalid_email))
            .assertExists()
    }


    @Test
    fun 이메일이_올바른_형식일_때_에러가_없다() {
        validator.value = EmailValidator()
        textFieldValue.value = "dlwlgns1240@gmail.com"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_invalid_email))
            .assertDoesNotExist()
    }
}