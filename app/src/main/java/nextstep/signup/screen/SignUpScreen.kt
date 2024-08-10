package nextstep.signup.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20

/**
 * Create Date: 2024. 8. 11.
 *
 * 회원가입 화면
 * @author LeeDongHun
 *
 *
 **/
@Preview
@Composable
fun SignUpScreen() {
    Scaffold(
        Modifier.background(Color.White)
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding),
        ) {
            SignUpTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp)
            )
        }
    }
}

/**
 * 회원가입 제목용 composeable 함수
 **/
@Composable
fun SignUpTitle(
    modifier: Modifier,
) {
    Text(
        text = stringResource(id = R.string.signe_up_title), modifier = modifier, style = TextStyle(
            fontSize = 26.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    )
}

/**
 * 회원가입 화면  input textfiled의 type 뷰 구성의 필요 값들을 정의
 **/
enum class SignUpUserInputTextFieldType(
    val label: @Composable (() -> Unit)?,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val focusedLabelColor: Color = Blue50,
    val focusedIndicatorColor: Color = Blue50,
    val cursorColor: Color = Blue50,
    val containerColor: Color = BlueGrey20
) {
    USERNAME(
        label = {
            Text(text = stringResource(id = R.string.username))
        },
    ),
    EMAIL(
        label = {
            Text(text = stringResource(id = R.string.email))
        },
        keyboardType = KeyboardType.Email,
    ),
    PASSWORD(
        label = {
            Text(text = stringResource(id = R.string.password))
        },
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation(),
    ),
    PASSWORD_CONFIRM(
        label = {
            Text(text = stringResource(id = R.string.password_confirm))
        },
        visualTransformation = PasswordVisualTransformation(),
    )
}

/**
 * 회원가입 화면에서 사용되는
 * 유저 회원가입 정보를 입력하는 TextField
 **/
@Composable
fun SigneUpTextField(
    modifier: Modifier,
    keyboardType: KeyboardType,
    textFiledColor: TextFieldColors,
    label: @Composable (() -> Unit)?,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val (inputValue, setInputValue) = remember {
        mutableStateOf(TextFieldValue())
    }
    TextField(
        colors = textFiledColor,
        modifier = modifier,
        value = inputValue,
        onValueChange = setInputValue,
        label = label,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        visualTransformation = visualTransformation
    )
}

/**
 * 회원가입 버튼 composeable 함수
 **/
@Composable
fun SignUpButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(Blue50),
        modifier = modifier,
        onClick = { onClick() }
    ) {
        Text(text = stringResource(id = R.string.sign_up))
    }
}


/**
 * 회원가입 제목용 composeable 함수 미리보기
 * (@preview 사용위해 parameter없는 composable 생성)
 **/
@Preview(
    backgroundColor = 0xFFFFFFFFL,
    showBackground = true,
)
@Composable
fun PreviewSignUpTitle() {
    SignUpTitle(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp)
    )
}

/**
 * 회원가입 화면에서 유저가 정보 입력하는 TextField 함수 미리보기
 **/
@Preview
@Composable
fun PreviewSigneUpTextField() {
    val signUpType = SignUpUserInputTextFieldType.entries.toTypedArray().random()
    SigneUpTextField(
        modifier = Modifier
            .fillMaxWidth()
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

/**
 * 회원가입 버튼 composeable 함수 미리보기
 **/
@Preview
@Composable
fun PreviewSignUpButton() {
    SignUpButton(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 115.dp)
            .padding(30.dp),
        onClick = {
            // 회원가입 버튼 클릭시 로직
        }
    )
}
