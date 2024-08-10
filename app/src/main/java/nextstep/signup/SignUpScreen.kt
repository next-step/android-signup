package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.Validator.isValid
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.Dimens.ButtonHeight
import nextstep.signup.ui.theme.Dimens.ButtonRadius
import nextstep.signup.ui.theme.Dimens.ButtonWidth
import nextstep.signup.ui.theme.Dimens.EndPadding
import nextstep.signup.ui.theme.Dimens.LargePadding
import nextstep.signup.ui.theme.Dimens.StartPadding
import nextstep.signup.ui.theme.Dimens.TextHelper
import nextstep.signup.ui.theme.Dimens.TextTitle
import nextstep.signup.ui.theme.Dimens.TopPadding
import nextstep.signup.ui.theme.Red50
import nextstep.signup.ui.theme.SignupTheme

class SignUpScreen {
    @Composable
    fun InitViews() {
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var isMatchPassword by remember { mutableStateOf(true) }

        SignupTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = TopPadding,
                            start = StartPadding,
                            end = EndPadding
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SignUpTitle()
                    Spacer(modifier = Modifier.height(LargePadding))
                    SignUpTextField(stringResource(id = R.string.username), TextFieldType.Username)
                    Spacer(modifier = Modifier.height(LargePadding))
                    SignUpTextField(stringResource(id = R.string.email), TextFieldType.Email)
                    Spacer(modifier = Modifier.height(LargePadding))
                    SignUpTextField(
                        stringResource(id = R.string.password),
                        visualTransformation = PasswordVisualTransformation(),
                        type = TextFieldType.Password,
                        onTextChange = {
                            password = it
                            isMatchPassword = password == confirmPassword
                        }
                    )
                    Spacer(modifier = Modifier.height(LargePadding))
                    SignUpTextField(
                        stringResource(id = R.string.password_confirm),
                        visualTransformation = PasswordVisualTransformation(),
                        type = TextFieldType.Password,
                        onTextChange = {
                            confirmPassword = it
                            isMatchPassword = password == confirmPassword
                        }
                    )
                    if (!isMatchPassword) {
                        SignUpHelperText()
                    }
                    Spacer(modifier = Modifier.height(LargePadding))
                    SignUpButton()
                }
            }
        }
    }

    @Composable
    private fun SignUpTitle() {
        Text(
            text = stringResource(id = R.string.welcome),
            textAlign = TextAlign.Center,
            fontSize = TextTitle,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
        )
    }

    @Preview(showBackground = true)
    @Composable
    private fun SignUpTitlePreview() {
        SignupTheme {
            SignUpTitle()
        }
    }

    @Composable
    private fun SignUpTextField(
        hint: String,
        type: TextFieldType,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        onTextChange: (String) -> Unit = {}
    ) {
        var textState by remember { mutableStateOf("") }
        var isValid: TextFieldState by remember { mutableStateOf(TextFieldState.InValid) }

        val onValueChange = { value: String ->
            textState = value
            isValid = isValid(value, type)
            onTextChange(value)
        }

        TextField(
            value = textState,
            onValueChange = onValueChange,
            label = {
                Text(text = hint)
            },
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.colors(
                focusedLabelColor = if (isValid == TextFieldState.Valid) MaterialTheme.colorScheme.primary else Red50
            )
        )
    }

    @Preview(showBackground = true)
    @Composable
    private fun SignUpTextFieldPreview() {
        SignupTheme {
            SignUpTextField(stringResource(id = R.string.username), TextFieldType.Username)
        }
    }

    @Composable
    private fun SignUpButton() {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(ButtonHeight)
                .width(ButtonWidth)
                .clip(RoundedCornerShape(ButtonRadius)),
            colors = ButtonDefaults.buttonColors(Blue50),
        ) {
            Text(text = stringResource(id = R.string.sign_up))
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun SignUpButtonPreview() {
        SignupTheme {
            SignUpButton()
        }
    }

    @Composable
    private fun SignUpHelperText() {
        Text(
            text = stringResource(id = R.string.password_do_not_match),
            fontSize = TextHelper,
            color = Red50,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = StartPadding),
        )
    }

    @Preview(showBackground = true)
    @Composable
    private fun SignUpHelperTextPreview() {
        SignupTheme {
            SignUpHelperText()
        }
    }
}