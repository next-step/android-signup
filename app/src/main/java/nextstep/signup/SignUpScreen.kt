package nextstep.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.roboto

@Composable
fun SignUpScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Welcome to Compose \uD83D\uDE80",
            fontSize = 26.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(36.dp),
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            SignUpInputForm("Username")
            SignUpInputForm("Email")
            SignUpInputForm("Password", PasswordVisualTransformation())
            SignUpInputForm("Password Confirm", PasswordVisualTransformation())
        }
    }
}

@Composable
private fun SignUpInputForm(
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }

    TextField(
        value = input,
        onValueChange = { input = it },
        label = { Text(label) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BlueGrey20,
            focusedContainerColor = BlueGrey20,
            focusedIndicatorColor = Blue50,
            focusedLabelColor = Blue50
        ),
        visualTransformation = visualTransformation,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    MaterialTheme {
        SignUpScreen(PaddingValues())
    }
}
