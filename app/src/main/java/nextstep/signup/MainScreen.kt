package nextstep.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.component.SignUpButton
import nextstep.signup.ui.component.SignUpEditFields
import nextstep.signup.ui.component.SignUpTitle
import nextstep.signup.ui.model.SignUpInputsModel
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val isValid by remember { mutableStateOf(true) }

    var inputsModel by remember { mutableStateOf(SignUpInputsModel()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .padding(top = 112.dp),
        verticalArrangement = Arrangement.spacedBy(42.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SignUpTitle()
        SignUpEditFields(
            onUpdateModel = { inputsModel = it }
        )
        SignUpButton(enabled = isValid, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    SignupTheme {
        MainScreen(
            modifier = Modifier,
        )
    }
}