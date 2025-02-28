package nextstep.signup.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen() {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 37.dp),
            text = stringResource(R.string.header),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        UserInputTextField(
            Modifier.fillMaxWidth().padding(top = 2.dp),
            username,
            { username = it },
            stringResource(R.string.username),
            ImeAction.Next
        )
        UserInputTextField(
            Modifier.fillMaxWidth(),
            email,
            { email = it },
            stringResource(R.string.email),
            ImeAction.Next
        )
        UserInputTextField(
            Modifier.fillMaxWidth(),
            password,
            { password = it },
            stringResource(R.string.password),
            ImeAction.Next
        )
        UserInputTextField(
            Modifier.fillMaxWidth().padding(bottom = 1.dp),
            passwordConfirmation,
            { passwordConfirmation = it },
            stringResource(R.string.password_confirmation),
            ImeAction.Done
        )

        SignUpButton(Modifier.fillMaxWidth().height(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignupTheme {
        SignUpScreen()
    }
}