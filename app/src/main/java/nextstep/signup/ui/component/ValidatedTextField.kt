package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun ValidatedTextField(
    field: InputFieldModel,
    modifier: Modifier = Modifier,
) {
    fun validateField(value: String): Int? {
        return if (value.isNotEmpty()) {
            val result = field.validator.validate(value)
            if (result.isValid.not()) result.message else null
        } else {
            null
        }
    }

    val errorRes by remember(field.validator, field.value) {
        mutableStateOf(validateField(field.value))
    }

    Column(modifier = modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = field.value,
            label = field.label,
            isError = errorRes != null,
            onValueChange = { field.onValueChange(it) },
            singleLine = true,
            visualTransformation = field.visualTransformation,
            supportingText = {
                errorRes?.let {
                    Text(
                        text = stringResource(id = it),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        )
    }
}
