package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setContent {
            SignupTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = dimensionResource(id = R.dimen.top_padding),
                                start = dimensionResource(id = R.dimen.start_padding),
                                end = dimensionResource(id = R.dimen.end_padding)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Title()
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_padding)))
                        SignUpTextField(stringResource(id = R.string.username))
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_padding)))
                        SignUpTextField(stringResource(id = R.string.email))
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_padding)))
                        SignUpTextField(stringResource(id = R.string.password), isPassword = true)
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_padding)))
                        SignUpTextField(
                            stringResource(id = R.string.password_confirm),
                            isPassword = true
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_padding)))
                        SignUpButton()
                    }
                }
            }
        }
    }
}

@Composable
private fun Title() {
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
private fun GreetingPreview() {
    SignupTheme {
        Title()
    }
}

@Composable
private fun SignUpTextField(
    hint: String,
    isPassword: Boolean = false
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
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None

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
            .height(dimensionResource(id = R.dimen.btn_height))
            .width(dimensionResource(id = R.dimen.btn_width))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.btn_radius))),
        colors = ButtonDefaults.buttonColors(
            colorResource(id = R.color.btn_color)
        )
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
