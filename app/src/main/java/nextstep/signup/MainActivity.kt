package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                TextFieldSetting(R.string.signup_username)
            }
        }
    }
}

@Composable
private fun TitleTextComponent() {
    Text(
        text = stringResource(id = R.string.signup_title),
        style = TextStyle(
            fontSize = 26.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(700),
            color = Color.Black,
            letterSpacing = 0.26.sp
        ),
        modifier = Modifier
            .fillMaxWidth(1.0F)
            .padding(bottom = 42.dp),
        textAlign = TextAlign.Center,
    )
}

@Preview(showBackground = true)
@Composable
fun TitleTextComponentPreview() {
    SignupTheme {
        TitleTextComponent()
    }
}

@Composable
private fun TextFieldComponent(
    placeholder: Int,
    input: MutableState<String>,
    inputEntered: MutableState<Boolean>
) {
    Row(modifier = Modifier.padding(top = 0.dp, bottom = 32.dp)) {
        TextField(
            value = input.value,
            onValueChange = {
                input.value = it
            },
            label = {
                Text(text = stringResource(id = placeholder))
            },
            placeholder = {
                Text(text = stringResource(id = placeholder))
            },
            modifier = Modifier
                .alignByBaseline()
                .weight(1.0F),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                capitalization = KeyboardCapitalization.Words
            ),
            keyboardActions = KeyboardActions(onAny = {
                inputEntered.value = true
            })
        )
    }
}

@Composable
fun TextFieldSetting(placeholder: Int) {
    val input = remember {
        mutableStateOf("")
    }
    val inputEntered = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TitleTextComponent()
            TextFieldComponent(
                placeholder = placeholder,
                input = input,
                inputEntered = inputEntered
            )
            TextFieldComponent(
                placeholder = placeholder,
                input = input,
                inputEntered = inputEntered
            )
            TextFieldComponent(
                placeholder = placeholder,
                input = input,
                inputEntered = inputEntered
            )
            TextFieldComponent(
                placeholder = placeholder,
                input = input,
                inputEntered = inputEntered
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldSettingPreview() {
    SignupTheme {
        TextFieldSetting(R.string.signup_username)
    }
}