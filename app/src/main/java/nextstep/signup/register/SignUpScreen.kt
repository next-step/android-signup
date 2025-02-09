package nextstep.signup.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.register.component.SignUpButton
import nextstep.signup.register.component.SignUpTextFiled
import nextstep.signup.register.component.SignUpTitle


@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SignUpTitle(modifier = Modifier.padding(bottom = 40.dp))
        SignUpTextFiled()
        SignUpButton(
            modifier = Modifier.padding(top = 40.dp),
            onSignUp = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRegisterScreen() {
    SignUpScreen()
}