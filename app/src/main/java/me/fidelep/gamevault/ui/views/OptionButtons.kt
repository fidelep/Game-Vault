package me.fidelep.gamevault.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.fidelep.gamevault.R

@Composable
fun OptionButtons(
    isEditing: Boolean,
    onDeleteVideoGame: () -> Unit,
    onUpdateVideoGame: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        ElevatedButton(
            onClick = { onDeleteVideoGame() },
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = stringResource(R.string.delete_icon),
                Modifier.size(16.dp),
            )
            Text(stringResource(R.string.delete))
        }
        ElevatedButton(
            colors = ButtonDefaults.buttonColors(),
            onClick = { onUpdateVideoGame() },
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "",
                Modifier.size(16.dp),
            )
            Text(
                if (isEditing) {
                    stringResource(R.string.save)
                } else {
                    stringResource(R.string.edit)
                },
            )
        }
    }
}

@Preview
@Composable
private fun OptionButtonsPreview() {
    Column {
        OptionButtons(isEditing = true, onDeleteVideoGame = { }, onUpdateVideoGame = { })
        OptionButtons(isEditing = false, onDeleteVideoGame = { }, onUpdateVideoGame = { })
    }
}
