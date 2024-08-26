package nextstep.signup.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.SignUpTextFieldErrorColor
import nextstep.signup.util.SignUpValidSate

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
