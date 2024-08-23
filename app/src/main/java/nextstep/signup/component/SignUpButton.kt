package nextstep.signup.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.Gray10

@Composable
fun SignUpButton(
    enabledSignUpButton : Boolean,
    onClickButton : () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(
            containerColor = Blue50,
            contentColor = Color.White,
            disabledContainerColor = Gray10.copy(alpha = 0.12f),
            disabledContentColor = Gray10
        ),
        enabled = enabledSignUpButton,
        onClick = onClickButton
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_button)
        )
    }
}