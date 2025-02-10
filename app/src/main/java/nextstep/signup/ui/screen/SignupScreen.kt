package nextstep.signup.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.component.SignupButton
import nextstep.signup.ui.component.SignupTextField
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                top = 60.dp,
                start = 32.dp,
                end = 32.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.welcome_to_compose),
            fontWeight = FontWeight.W700,
            fontSize = 26.sp,
            lineHeight = 20.sp,
        )
        Spacer(modifier = Modifier.height(42.dp))
        SignupTextField(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.username),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(33.dp))
        SignupTextField(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.email),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(33.dp))
        SignupTextField(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(33.dp))
        SignupTextField(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.password_confirm),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(39.dp))
        SignupButton(
            label = stringResource(R.string.signup),
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}

@Preview
@Composable
private fun SignupScreenPreview() {
    SignupTheme {
        SignupScreen(modifier = Modifier
            .fillMaxSize()
            .background(Color.White))
    }
}
