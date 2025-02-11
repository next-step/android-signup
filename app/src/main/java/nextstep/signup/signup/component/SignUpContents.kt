package nextstep.signup.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R

@Composable
fun SignUpContents(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_name),
            keyboardType = KeyboardType.Text
        )


        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_email),
            keyboardType = KeyboardType.Text
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_password),
            keyboardType = KeyboardType.NumberPassword,
            visualTransformation = PasswordVisualTransformation()
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_password_confirm),
            keyboardType = KeyboardType.NumberPassword,
            visualTransformation = PasswordVisualTransformation()
        )

        SignUpButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            buttonTitle = stringResource(R.string.signup_main_signtup_button),
            onClick = {})
    }
}


@Preview(showBackground = true)
@Composable
private fun SignUpContentsPreview() {
    SignUpContents()
}