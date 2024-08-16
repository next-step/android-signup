package nextstep.signup.ui.signup.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.ui.common.component.SignupButton
import nextstep.signup.ui.theme.Blue50

@Composable
fun CreateAccountButton(
    isSuccessfulCondition: Boolean,
    onCreateAccountButtonClick: () -> Unit,
) {
    val buttonContentDescription = stringResource(R.string.signup_create_account_button)

    SignupButton(
        buttonText = stringResource(R.string.signup_button),
        buttonTextColor = if (isSuccessfulCondition) Color.White else Color.Black,
        containerColor = if (isSuccessfulCondition) Blue50 else Color.Gray,
        onButtonClick = onCreateAccountButtonClick,
        enabled = isSuccessfulCondition,
        componentDescription = buttonContentDescription,
    )
}

@Preview
@Composable
private fun CreateAccountButtonPreview() {
    CreateAccountButton(
        isSuccessfulCondition = false,
        onCreateAccountButtonClick = {},
    )
}
