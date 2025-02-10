package nextstep.signup

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
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
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    SignUpScreen()
                }
            }
        }
    }
}


@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            SignUpTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 42.dp),
                title = "Welcome to Compose \uD83D\uDE80"
            )
        },
    ) { contentPadding ->
        SignUpContents(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 32.dp)
        )
    }
}

@Composable
fun SignUpContents(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { SignUpInputForm(placeHolderText = "UserName", keyboardType = KeyboardType.Text) }
        item { SignUpInputForm(placeHolderText = "Email", keyboardType = KeyboardType.Text) }
        item {
            SignUpInputForm(
                placeHolderText = "Password",
                keyboardType = KeyboardType.NumberPassword
            )
        }
        item {
            SignUpInputForm(
                placeHolderText = "Password Confirm",
                keyboardType = KeyboardType.NumberPassword
            )
        }
        item {
            SignUpButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                buttonTitle = "Sign UP",
                onClick = {})
        }
    }
}

@Composable
fun SignUpTitle(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 20.sp,
        letterSpacing = 1.sp,
        color = Color.Black
    )
}

@Composable
fun SignUpButton(modifier: Modifier = Modifier, buttonTitle: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(buttonTitle)
    }
}

@Composable
fun SignUpInputForm(
    modifier: Modifier = Modifier,
    placeHolderText: String,
    keyboardType: KeyboardType
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = textFieldValue,
        onValueChange = { newTextFieldValue ->
            textFieldValue = newTextFieldValue
        },
        label = { Text(placeHolderText) },
        visualTransformation = VisualTransformation.None,
        singleLine = true,
        placeholder = { Text(placeHolderText) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFE3E8F1),
            unfocusedContainerColor = Color(0xFFE3E8F1)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpContents()
}


@Preview(showBackground = true)
@Composable
fun SignUpTitlePreview() {
    SignUpTitle(title = "Welcome to Compose \uD83D\uDE80")
}

@Preview(showBackground = true)
@Composable
fun SignUpButtonPreview() {
    SignUpButton(buttonTitle = "Sign Up", onClick = {})
}

@Preview(showBackground = true)
@Composable
fun SignUpInputFormPreview() {
    SignUpInputForm(placeHolderText = "placeHolderText", keyboardType = KeyboardType.Text)
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScatTest() {
    SignUpScreen()
}