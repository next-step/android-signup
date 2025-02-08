package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.ui.component.SignupScreen
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                SignupScreen()
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
)
@Composable
fun SignupScreenPreview() {
    SignupTheme {
        SignupScreen()
    }
}
