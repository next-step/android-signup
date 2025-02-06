package nextstep.signup.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.register.component.SignUpButton
import nextstep.signup.register.component.SignUpTitle
import nextstep.signup.register.component.SignUpTextFiled


@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .verticalScroll(rememberScrollState())
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        SignUpTitle(modifier = Modifier.fillMaxWidth())

        SignUpTextFiled.UserName(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
        )
        SignUpTextFiled.Email(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
        SignUpTextFiled.Password(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
        SignUpTextFiled.PasswordConfirm(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        SignUpButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onSignUp = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRegisterScreen() {
    SignUpScreen()
}