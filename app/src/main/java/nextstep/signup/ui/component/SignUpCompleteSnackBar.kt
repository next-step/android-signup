package nextstep.signup.ui.component

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import nextstep.signup.R

@Composable
internal fun SignUpSnackBar(
    snackBarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()
    val message = stringResource(R.string.completed_sign_up)

    LaunchedEffect(Unit) {
        scope.launch {
            snackBarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Long,
                withDismissAction = true,
            )
        }
    }
}