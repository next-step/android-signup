package nextstep.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.components.SignUpButton
import nextstep.signup.components.SignUpTextField
import nextstep.signup.components.SignUpTitle
import nextstep.signup.ui.theme.SignupTheme

@Composable
internal fun SignUpScreen(
    inputFields: InputFields,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(60.dp))
        SignUpTitle(stringResource(R.string.sign_up_title))
        Spacer(Modifier.height(42.dp))
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            inputFields.fields.forEach { (key, state) ->
                SignUpTextField(
                    value = state.input,
                    onValueChange = { inputFields.update(key = key, newValue = it) },
                    label = stringResource(state.label),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = state.keyboardType,
                    needHide = state.needHide,
                    isError = state.isError,
                    supportingText = state.supportingText,
                )
            }
        }
        Spacer(Modifier.height(22.dp))
        SignUpButton(
            text = stringResource(R.string.sign_up_sign_up_button_text),
            onClick = onSignUpButtonClick,
            enabled = inputFields.isValidAll(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        val inputFields: InputFields = remember { InputFields() }

        SignUpScreen(
            inputFields = inputFields,
            onSignUpButtonClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
