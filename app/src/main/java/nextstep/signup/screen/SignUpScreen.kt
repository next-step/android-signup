package nextstep.signup.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.SignUpDisableBtnContainerColor
import nextstep.signup.ui.theme.SignUpDisableBtnContentColor
import nextstep.signup.ui.theme.SignUpTextFieldErrorColor
import nextstep.signup.util.SignUpEmailValidator
import nextstep.signup.util.SignUpPasswordConfirmValidator
import nextstep.signup.util.SignUpPasswordValidator
import nextstep.signup.util.SignUpUserNameValidator
import nextstep.signup.util.SignUpValidSate

/**
 * Create Date: 2024. 8. 11.
 *
 * 회원가입 화면
 * @author LeeDongHun
 *
 *
 **/
@Composable
fun SignUpScreen() {

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    //state 최상위로 끌어올림 (state hoisting)
    val (userNameInputValue, setUserNameInputValue) = rememberSaveable { mutableStateOf("") }//유저 이름
    val userNameValidator = SignUpUserNameValidator()
    val (userNameInputValidState, setUserNameInputValidState) = rememberSaveable {
        mutableStateOf(
            SignUpValidSate.NOTHING
        )
    }//유저 이름 유효성 상태
    setUserNameInputValidState(userNameValidator.getValidState(userNameInputValue))

    val (emailInputValue, setEmailInputValue) = rememberSaveable { mutableStateOf("") }//이메일 적용
    val emailValidator = SignUpEmailValidator()
    val (emailInputValidState, setEmailInputValidState) = rememberSaveable {
        mutableStateOf(
            SignUpValidSate.NOTHING
        )
    }//유저 이메일 유효성 상태
    setEmailInputValidState(emailValidator.getValidState(emailInputValue))

    val (passWordInputValue, setPassWordInputValue) = rememberSaveable { mutableStateOf("") }//패스워드 적용
    val passWordValidator = SignUpPasswordValidator()
    val (passWordInputValidState, setPassWordInputValidState) = rememberSaveable {
        mutableStateOf(
            SignUpValidSate.NOTHING
        )
    } //유저 패스워드 유효성 상태
    setPassWordInputValidState(passWordValidator.getValidState(passWordInputValue))

    val (passWordConfirmValue, setPassWordConfirmValue) = rememberSaveable { mutableStateOf("") }//패스워드 확인 적용
    val passWordConfirmValidator = SignUpPasswordConfirmValidator(password = passWordInputValue)
    val (passWordConfirmInputValidState, setPasswordConfirmInputValidState) = rememberSaveable {
        mutableStateOf(
            SignUpValidSate.NOTHING
        )
    }//유저 패스워드 확인 유효성 상태
    setPasswordConfirmInputValidState(passWordConfirmValidator.getValidState(passWordConfirmValue))

    //회원가입 버튼 활성화 상태
    var signUpBtnEnable by rememberSaveable { mutableStateOf(false) }
    signUpBtnEnable = (userNameInputValidState == SignUpValidSate.VALID
            && emailInputValidState == SignUpValidSate.VALID
            && passWordInputValidState == SignUpValidSate.VALID
            && passWordConfirmInputValidState == SignUpValidSate.VALID)

    SignUpScreen(
        snackBarHostState = snackBarHostState,
        coroutineScope = coroutineScope,
        context = context,
        userNameInputValue = userNameInputValue,
        setUserNameInputValue = setUserNameInputValue,
        userNameInputValidState = userNameInputValidState,
        emailInputValue = emailInputValue,
        setEmailInputValue = setEmailInputValue,
        emailInputValidState = emailInputValidState,
        passWordInputValue = passWordInputValue,
        setPassWordInputValue = setPassWordInputValue,
        passWordInputValidState = passWordInputValidState,
        passWordConfirmValue = passWordConfirmValue,
        setPassWordConfirmValue = setPassWordConfirmValue,
        passWordConfirmInputValidState = passWordConfirmInputValidState,
        signUpBtnEnable = signUpBtnEnable
    )
}

@Composable
fun SignUpScreen(
    snackBarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    context: Context,
    userNameInputValue: String,
    setUserNameInputValue: (String) -> Unit,
    userNameInputValidState: SignUpValidSate,
    emailInputValue: String,
    setEmailInputValue: (String) -> Unit,
    emailInputValidState: SignUpValidSate,
    passWordInputValue: String,
    setPassWordInputValue: (String) -> Unit,
    passWordInputValidState: SignUpValidSate,
    passWordConfirmValue: String,
    setPassWordConfirmValue: (String) -> Unit,
    passWordConfirmInputValidState: SignUpValidSate,
    signUpBtnEnable: Boolean
) {
    Scaffold(
        modifier = Modifier.background(Color.White),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            SignUpTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 70.dp, bottom = 25.dp
                    )
            )
            UserNameSigneUpTextField(
                textContent = userNameInputValue,
                onTextValueChange = setUserNameInputValue,
                signUpInputValidState = userNameInputValidState,
            )
            EmailSigneUpTextField(
                textContent = emailInputValue,
                onTextValueChange = setEmailInputValue,
                signUpInputValidState = emailInputValidState,
            )
            PassWordSigneUpTextField(
                textContent = passWordInputValue,
                onTextValueChange = setPassWordInputValue,
                signUpInputValidState = passWordInputValidState,
            )
            PassWordConfirmSigneUpTextField(
                textContent = passWordConfirmValue,
                onTextValueChange = setPassWordConfirmValue,
                signUpInputValidState = passWordConfirmInputValidState,
            )
            SignUpButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 115.dp)
                    .padding(30.dp),
                isBtnEnable = signUpBtnEnable,
                onClick = {
                    coroutineScope.launch {
                        snackBarHostState
                            .showSnackbar(
                                message = context.getString(R.string.finish_sign_up),
                            )
                    }
                }
            )
        }
    }
}

/**
 * 회원가입 화면 composeable 함수 미리보기
**/
@Preview
@Composable
private fun PreviewSignUpScreen() {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    SignUpScreen(
        snackBarHostState = snackBarHostState,
        coroutineScope = coroutineScope,
        context = context,
        userNameInputValue = "sss",
        setUserNameInputValue = {},
        userNameInputValidState = SignUpValidSate.ERROR_USER_NAME_LENGTH,
        emailInputValue = "nadadhhl12@",
        setEmailInputValue = {},
        emailInputValidState = SignUpValidSate.ERROR_EMAIL_REGEX,
        passWordInputValue = "sdsdsd",
        setPassWordInputValue = {},
        passWordInputValidState = SignUpValidSate.ERROR_PASSWORD_REGEX,
        passWordConfirmValue = "sdsdsd",
        setPassWordConfirmValue = {},
        passWordConfirmInputValidState = SignUpValidSate.VALID,
        signUpBtnEnable = true
    )
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
    signUpInputValidState: SignUpValidSate,
) {
    val errorMessage = when (signUpInputValidState) {
        SignUpValidSate.ERROR_USER_NAME_LENGTH -> stringResource(id = R.string.error_length_username)
        SignUpValidSate.ERROR_USER_NAME_REGEX -> stringResource(id = R.string.error_regex_username)
        else -> stringResource(id = R.string.empty_string)
    }
    SigneUpTextField(
        keyboardType = KeyboardType.Text,
        label = { Text(text = stringResource(id = R.string.username)) },
        textContent = textContent,
        onTextValueChange = onTextValueChange,
        signUpInputValidState = signUpInputValidState,
        errorMessage = errorMessage
    )
}

/**
 * 이메일 입력용 TextField
 **/
@Composable
fun EmailSigneUpTextField(
    textContent: String,
    onTextValueChange: (String) -> Unit,
    signUpInputValidState: SignUpValidSate,
) {
    val errorMessage = when (signUpInputValidState) {
        SignUpValidSate.ERROR_EMAIL_REGEX -> stringResource(id = R.string.error_regex_email)
        else -> stringResource(id = R.string.empty_string)
    }
    SigneUpTextField(
        keyboardType = KeyboardType.Email,
        label = { Text(text = stringResource(id = R.string.email)) },
        textContent = textContent,
        onTextValueChange = onTextValueChange,
        signUpInputValidState = signUpInputValidState,
        errorMessage = errorMessage
    )
}

/**
 * 패스워드 입력용 TextField
 **/
@Composable
fun PassWordSigneUpTextField(
    textContent: String,
    onTextValueChange: (String) -> Unit,
    signUpInputValidState: SignUpValidSate,
) {
    val errorMessage = when (signUpInputValidState) {
        SignUpValidSate.ERROR_PASSWORD_LENGTH -> stringResource(id = R.string.error_length_pwd)
        SignUpValidSate.ERROR_PASSWORD_REGEX -> stringResource(id = R.string.error_regex_pwd)
        else -> stringResource(id = R.string.empty_string)
    }
    SigneUpTextField(
        keyboardType = KeyboardType.Password,
        label = { Text(text = stringResource(id = R.string.password)) },
        textContent = textContent,
        onTextValueChange = onTextValueChange,
        visualTransformation = PasswordVisualTransformation(),
        signUpInputValidState = signUpInputValidState,
        errorMessage = errorMessage
    )
}

/**
 * 패스워드 확인용 TextField
 **/
@Composable
fun PassWordConfirmSigneUpTextField(
    textContent: String,
    onTextValueChange: (String) -> Unit,
    signUpInputValidState: SignUpValidSate,
) {
    val errorMessage = when (signUpInputValidState) {
        SignUpValidSate.ERROR_PASSWORD_CONFIRM -> stringResource(id = R.string.error_not_matched_pwd)
        else -> stringResource(id = R.string.empty_string)
    }
    SigneUpTextField(
        keyboardType = KeyboardType.Password,
        label = { Text(text = stringResource(id = R.string.password_confirm)) },
        textContent = textContent,
        onTextValueChange = onTextValueChange,
        visualTransformation = PasswordVisualTransformation(),
        signUpInputValidState = signUpInputValidState,
        errorMessage = errorMessage
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
    errorMessage: String,
    onTextValueChange: (String) -> Unit,
    signUpInputValidState: SignUpValidSate,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    TextField(
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Blue50,
            focusedIndicatorColor = Blue50,
            cursorColor = Blue50,
            unfocusedContainerColor = BlueGrey20,
            focusedContainerColor = BlueGrey20,
            errorLabelColor = SignUpTextFieldErrorColor,
            errorCursorColor = SignUpTextFieldErrorColor,
        ),
        isError = signUpInputValidState != SignUpValidSate.VALID
                && signUpInputValidState != SignUpValidSate.NOTHING,
        supportingText = {
            if (signUpInputValidState != SignUpValidSate.VALID
                && signUpInputValidState != SignUpValidSate.NOTHING
            ) {
                Text(
                    text = errorMessage,
                    color = SignUpTextFieldErrorColor
                )
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 15.dp, start = 30.dp, end = 30.dp
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
    onClick: () -> Unit,
    isBtnEnable: Boolean
) {
    Button(
        colors = ButtonDefaults
            .buttonColors(
                containerColor = Blue50,
                contentColor = Color.White,
                disabledContentColor = SignUpDisableBtnContentColor,
                disabledContainerColor = SignUpDisableBtnContainerColor
            ),
        modifier = modifier,
        onClick = { onClick() },
        enabled = isBtnEnable,
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
    val (inputValue, setInputValue) = remember { mutableStateOf("@j") }
    val (userNameInputValidState, _) = remember { mutableStateOf(SignUpValidSate.NOTHING) }
    val errorMessage = when (userNameInputValidState) {
        SignUpValidSate.ERROR_USER_NAME_LENGTH -> stringResource(id = R.string.error_length_username)
        SignUpValidSate.ERROR_USER_NAME_REGEX -> stringResource(id = R.string.error_regex_username)
        else -> stringResource(id = R.string.empty_string)
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
        onTextValueChange = setInputValue,
        signUpInputValidState = userNameInputValidState,
        errorMessage = errorMessage,
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
        isBtnEnable = true,
        onClick = {
            // 회원가입 버튼 클릭시 로직
        }
    )
}
