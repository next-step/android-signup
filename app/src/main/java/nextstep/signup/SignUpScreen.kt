package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.Dimens.ButtonHeight
import nextstep.signup.ui.theme.Dimens.ButtonRadius
import nextstep.signup.ui.theme.Dimens.ButtonWidth
import nextstep.signup.ui.theme.Dimens.EndPadding
import nextstep.signup.ui.theme.Dimens.LargePadding
import nextstep.signup.ui.theme.Dimens.StartPadding
import nextstep.signup.ui.theme.Dimens.TopPadding
import nextstep.signup.ui.theme.SignupTheme

class SignUpScreen {
    @Composable
    fun InitViews() {
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
                    SignUpTextField(stringResource(id = R.string.username))
                    Spacer(modifier = Modifier.height(LargePadding))
                    SignUpTextField(stringResource(id = R.string.email))
                    Spacer(modifier = Modifier.height(LargePadding))
                    SignUpTextField(
                        stringResource(id = R.string.password),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(LargePadding))
                    SignUpTextField(
                        stringResource(id = R.string.password_confirm),
                        visualTransformation = PasswordVisualTransformation()
                    )
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
            fontSize = 26.sp,
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
        visualTransformation: VisualTransformation = VisualTransformation.None
    ) {
        var textState by remember { mutableStateOf("") }

        val onValueChange = { value: String ->
            textState = value
        }

        TextField(
            value = textState,
            onValueChange = onValueChange,
            label = {
                Text(text = hint)
            },
            visualTransformation = visualTransformation

        )
    }

    @Preview(showBackground = true)
    @Composable
    private fun SignUpTextFieldPreview() {
        SignupTheme {
            SignUpTextField(stringResource(id = R.string.username))
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
}