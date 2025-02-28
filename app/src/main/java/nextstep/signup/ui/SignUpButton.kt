package nextstep.signup.ui

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50

@Composable
fun SignUpButton(
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue50,
            contentColor = Color.White
        ),
        onClick = {
            Log.d("SignUp", "SignUp Button Clicked")
        }
    ) {
        Text(text = stringResource(R.string.sign_up), fontSize = 14.sp)
    }
}