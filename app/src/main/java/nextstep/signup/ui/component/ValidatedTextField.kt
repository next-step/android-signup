package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ValidatedTextField(
    field: InputFieldModel,
    modifier: Modifier = Modifier,
) {
    var errorRes by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(field.value) {
        if (field.value.isEmpty()) return@LaunchedEffect
        val result = field.validator.validate(field.value)
        errorRes = if (result.isValid.not()) result.message else null
    }

    Column(modifier = modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = field.value,
            label = field.label,
            isError = errorRes != null,
            onValueChange = { field.onValueChange(it) },
            singleLine = true,
            visualTransformation = field.visualTransformation
        )
        errorRes?.let {
            Text(
                text = stringResource(id = it),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}