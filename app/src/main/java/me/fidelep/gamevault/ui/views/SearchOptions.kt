package me.fidelep.gamevault.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.fidelep.gamevault.R
import me.fidelep.gamevault.domain.model.SearchFilter

@Composable
fun SearchOptions(
    selectedFilter: SearchFilter,
    isExpanded: Boolean,
    onExpand: (isExpanded: Boolean) -> Unit,
    onMenuItemSelect: (filter: SearchFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .wrapContentSize(Alignment.TopStart)
                .padding(end = 8.dp),
    ) {
        TextButton(onClick = { onExpand(true) }) {
            Text(selectedFilter.name)
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = stringResource(R.string.filter_options),
            )
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { onExpand(false) },
        ) {
            SearchFilter.entries.forEach { filter ->
                DropdownMenuItem(
                    text = { Text(filter.name) },
                    onClick = { onMenuItemSelect(filter) },
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchOptionsPreview() {
    SearchOptions(
        selectedFilter = SearchFilter.ALL,
        isExpanded = true,
        onExpand = {},
        onMenuItemSelect = {},
    )
}
