package nextstep.signup.study

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import nextstep.signup.screen.SignUpTitle
import nextstep.signup.screen.SignUpUserInputTextFieldType
import nextstep.signup.screen.SigneUpTextField
import org.junit.Rule
import org.junit.Test

/**
 * Create Date: 2024. 8. 10.
 *
 * 회원가입 화면 테스트
 *
 * @author LeeDongHun
 *
 *
**/

class SignupScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    /**
     * 회원가입 title에 figma에 정의된
     * title text가  존재하는지 확인
    **/
    @Test
    fun signUpTitleExist() {
        composeTestRule.setContent {
            SignUpTitle(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp))
        }
        composeTestRule
            .onNodeWithText("Welcome to Compose \uD83D\uDE80")
            .assertExists()
    }

    /**
     * 회원가입에 입력된 text가 일치하는지 체크
     * password인경우는 불일치 나와야함 ***처리 임으로
    **/
    @Test
    fun signUpTextFieldInputTextCheck(){
        val signUpType = SignUpUserInputTextFieldType.entries.toTypedArray().random()

        val testInputText = "test 입니다."

        composeTestRule.setContent {
            SigneUpTextField(
                modifier = Modifier
                    .testTag("signupTextField")
                    .padding(
                        top = 17.dp,
                        bottom = 17.dp,
                        start = 30.dp,
                        end = 30.dp
                    ),
                label = signUpType.label,
                keyboardType = signUpType.keyboardType,
                visualTransformation = signUpType.visualTransformation,
                textFiledColor = TextFieldDefaults.colors(
                    focusedLabelColor = signUpType.focusedLabelColor,
                    focusedIndicatorColor = signUpType.focusedIndicatorColor,
                    cursorColor = signUpType.cursorColor,
                    unfocusedContainerColor = signUpType.containerColor,
                    focusedContainerColor = signUpType.containerColor
                )
            )
        }

        composeTestRule
            .onNodeWithTag("signupTextField")
            .performTextInput(testInputText)

        composeTestRule
            .onNodeWithTag("signupTextField")
            .run {
                //비밀번호 타입인 경우 text 일치하면 안됨.
                if(signUpType.visualTransformation == PasswordVisualTransformation()) {
                    assert(!hasText(testInputText))
                }else{//비밀번호 타입 아닌 경우 text 일치해야함.
                    assert(hasText(testInputText))
                }
            }
    }
}