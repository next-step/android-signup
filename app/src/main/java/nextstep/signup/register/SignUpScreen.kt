package nextstep.signup.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.register.component.SignUpButton
import nextstep.signup.register.component.SignUpTextFiled
import nextstep.signup.register.component.SignUpTitle


@Composable
fun SignUpScreen() {

    var isEnableSignUp by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val snackBarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SignUpTitle(modifier = Modifier.padding(bottom = 40.dp))
            SignUpTextFiled(onChangeValid = { isEnableSignUp = it })
            SignUpButton(
                modifier = Modifier.padding(top = 40.dp),
                isEnabled = isEnableSignUp,
                onSignUp = {
                    scope.launch {
                        snackBarHostState.showSnackbar(context.getString(R.string.sign_up_snack_bar))
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRegisterScreen() {
    SignUpScreen()
}