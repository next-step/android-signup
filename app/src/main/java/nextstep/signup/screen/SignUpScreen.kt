package nextstep.signup.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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

    //state 최상위로 끌어올림 (state hoisting)
    val (userNameInputValue, setUserNameInputValue) = remember { mutableStateOf("") }//유저 이름
    val (emailInputValue, setEmailInputValue) = remember { mutableStateOf("") }//이메일 적용
    val (passWordInputValue, setPassWordInputValue) = remember { mutableStateOf("") }//패스워드 적용
    val (passWordConfirmValue, setPassWordConfirmValue) = remember { mutableStateOf("") }//패스워드 확인 적용

    Scaffold(
        Modifier.background(Color.White)
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            SignUpTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 70.dp,
                        bottom = 25.dp
                    )
            )
            UserNameSigneUpTextField(
                textContent = userNameInputValue,
                onTextValueChange = setUserNameInputValue
            )
            EmailSigneUpTextField(
                textContent = emailInputValue,
                onTextValueChange = setEmailInputValue
            )
            PassWordSigneUpTextField(
                textContent = passWordInputValue,
                onTextValueChange = setPassWordInputValue
            )
            PassWordConfirmSigneUpTextField(
                textContent = passWordConfirmValue,
                onTextValueChange = setPassWordConfirmValue
            )
            SignUpButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 115.dp)
                    .padding(30.dp),
                onClick = {
                    //회원가입 버튼 클릭시 로직
                }
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
 * 닉네임 입력 TextField
**/
@Composable
fun UserNameSigneUpTextField(
    textContent: String,
    onTextValueChange: (String) -> Unit,
){
    SigneUpTextField(
        keyboardType = KeyboardType.Text,
        label = { Text(text = stringResource(id = R.string.username)) },
        textContent = textContent,
        onTextValueChange = onTextValueChange,
    )
}

/**
 * 이메일 입력용 TextField
**/
@Composable
fun EmailSigneUpTextField(
    textContent: String,
    onTextValueChange: (String) -> Unit,
){
    SigneUpTextField(
        keyboardType = KeyboardType.Email,
        label = { Text(text = stringResource(id = R.string.email)) },
        textContent = textContent,
        onTextValueChange = onTextValueChange,
    )
}

/**
 * 패스워드 입력용 TextField
**/
@Composable
fun PassWordSigneUpTextField(
    textContent: String,
    onTextValueChange: (String) -> Unit,
){
    SigneUpTextField(
        keyboardType = KeyboardType.Password,
        label = { Text(text = stringResource(id = R.string.password)) },
        textContent = textContent,
        onTextValueChange = onTextValueChange,
        visualTransformation = PasswordVisualTransformation()
    )
}

/**
 * 패스워드 확인용 TextField
**/
@Composable
fun PassWordConfirmSigneUpTextField(
    textContent: String,
    onTextValueChange: (String) -> Unit,
) {
    SigneUpTextField(
        keyboardType = KeyboardType.Password,
        label = { Text(text = stringResource(id = R.string.password_confirm)) },
        textContent = textContent,
        onTextValueChange = onTextValueChange,
        visualTransformation = PasswordVisualTransformation()
    )
}


/**
 * 회원가입 화면에서 사용되는
 * 유저 회원가입 정보를 입력하는 TextField
 **/
@Composable
fun SigneUpTextField(
    keyboardType: KeyboardType,
    label: @Composable (() -> Unit)?,
    modifier: Modifier = Modifier,
    textContent: String,
    onTextValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    TextField(
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Blue50,
            focusedIndicatorColor = Blue50,
            cursorColor = Blue50,
            unfocusedContainerColor = BlueGrey20,
            focusedContainerColor = BlueGrey20
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 17.dp, bottom = 17.dp, start = 30.dp, end = 30.dp
            ),
        value = textContent,
        onValueChange = onTextValueChange,
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
    val (inputValue, setInputValue) = remember {
        mutableStateOf("")
    }
    SigneUpTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 17.dp, bottom = 17.dp, start = 30.dp, end = 30.dp
            ),
        label = {
            Text(stringResource(id = R.string.username))
        },
        keyboardType = KeyboardType.Text,
        visualTransformation = VisualTransformation.None,
        textContent = inputValue,
        onTextValueChange = setInputValue
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
