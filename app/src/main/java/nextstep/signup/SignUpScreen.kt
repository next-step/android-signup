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
            UserNameInputForm()
            EmailInputForm()
            PasswordInputForm()
            PasswordConfirmInputForm()
        }
    }
}


@Composable
private fun UserNameInputForm(modifier: Modifier = Modifier) {
    var userName by remember { mutableStateOf("") }

    TextField(
        value = userName,
        onValueChange = { userName = it },
        label = { Text("Username") },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BlueGrey20,
            focusedContainerColor = BlueGrey20,
            focusedIndicatorColor = Blue50,
            focusedLabelColor = Blue50
        ),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun EmailInputForm(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }

    TextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Email") },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BlueGrey20,
            focusedContainerColor = BlueGrey20,
            focusedIndicatorColor = Blue50,
            focusedLabelColor = Blue50
        ),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun PasswordInputForm(modifier: Modifier = Modifier) {
    var password by remember { mutableStateOf("") }

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BlueGrey20,
            focusedContainerColor = BlueGrey20,
            focusedIndicatorColor = Blue50,
            focusedLabelColor = Blue50
        ),
        modifier = modifier.fillMaxWidth()
    )
}


@Composable
private fun PasswordConfirmInputForm(modifier: Modifier = Modifier) {
    var passwordConfirm by remember { mutableStateOf("") }

    TextField(
        value = passwordConfirm,
        onValueChange = { passwordConfirm = it },
        label = { Text("Password Confirm") },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BlueGrey20,
            focusedContainerColor = BlueGrey20,
            focusedIndicatorColor = Blue50,
            focusedLabelColor = Blue50
        ),
        visualTransformation = PasswordVisualTransformation(),
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
