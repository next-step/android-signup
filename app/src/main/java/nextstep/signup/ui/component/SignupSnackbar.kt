package nextstep.signup.ui.component

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignupSnackbar(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier
    ) { snackbar ->
        Snackbar(
            snackbarData = snackbar,
            modifier = modifier,
        )
    }
}

@Preview
@Composable
fun SignupSnackbarPreview() {
    val hostState = SnackbarHostState()

    SignupSnackbar(
        hostState = hostState
    )

    LaunchedEffect(Unit) {
        hostState.showSnackbar(
            message = "Message",
            actionLabel = "Button",
        )
    }
}