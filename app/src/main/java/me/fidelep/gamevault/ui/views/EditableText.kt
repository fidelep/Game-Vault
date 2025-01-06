package me.fidelep.gamevault.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EditableTextItem(
    label: String,
    value: TextFieldValue,
    isEditing: Boolean,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.inverseOnSurface),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 4.dp),
        )
        if (isEditing) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                    .padding(8.dp),
            )
        } else {
            Text(
                text = value.text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Start,
            )
        }
    }
}

@Preview
@Composable
private fun EditableTextItemPreview() {
    val mockedValue = TextFieldValue("Valor del campo")
    val label = "Titulo del campo"
    Column {
        EditableTextItem(
            label = label,
            value = mockedValue,
            isEditing = true,
            onValueChange = {},
        )
        EditableTextItem(
            label = label,
            value = mockedValue,
            isEditing = false,
            onValueChange = {},
        )
    }
}
