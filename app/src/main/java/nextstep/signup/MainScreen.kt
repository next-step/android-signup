package nextstep.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.component.SignUpButton
import nextstep.signup.ui.component.SignUpTitle
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    title: String,
) {
    val isValid by remember { mutableStateOf(true) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SignUpTitle(title)
        SignUpButton(enabled = isValid, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    SignupTheme {
        MainScreen(
            modifier = Modifier,
            title = "제목입니다",
        )
    }
}