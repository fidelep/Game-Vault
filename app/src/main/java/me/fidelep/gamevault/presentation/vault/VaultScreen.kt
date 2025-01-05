package me.fidelep.gamevault.presentation.vault

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.fidelep.gamevault.R
import me.fidelep.gamevault.domain.model.SearchFilter
import me.fidelep.gamevault.domain.model.VideoGameModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VaultScreen(
    navController: NavController,
    onQueryChange: (String, SearchFilter) -> Unit,
    videoGames: List<VideoGameModel>,
    modifier: Modifier = Modifier,
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf(SearchFilter.ALL) }

    SearchBar(
        modifier = modifier,
        inputField = {
            SearchBarDefaults.InputField(
                onQueryChange = {
                    searchQuery = it
                    onQueryChange(it, selectedFilter)
                },
                query = searchQuery,
                onSearch = { onQueryChange(searchQuery, selectedFilter) },
                expanded = true,
                onExpandedChange = { },
                placeholder = { Text(stringResource(R.string.search)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    Box(
                        modifier =
                            Modifier
                                .wrapContentSize(Alignment.TopStart)
                                .padding(end = 8.dp),
                    ) {
                        var expanded by remember { mutableStateOf(false) }

                        TextButton(onClick = { expanded = true }) {
                            Text(selectedFilter.name)
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = stringResource(R.string.filter_options),
                            )
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            SearchFilter.entries.forEach { filter ->
                                DropdownMenuItem(
                                    text = { Text(filter.name) },
                                    onClick = {
                                        selectedFilter = filter
                                        expanded = false
                                    },
                                )
                            }
                        }
                    }
                },
            )
        },
        expanded = true,
        onExpandedChange = { },
    ) {
        LazyVerticalGrid(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface),
            columns = GridCells.Adaptive(144.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
        ) {
            items(videoGames) {
                VideoGameItem(
                    videoGame = it,
                    onItemClick = {},
                )
            }
        }
    }
}

@Preview
@Composable
private fun VaultScreenPreview() {
    val mockedList =
        listOf(
            VideoGameModel(
                id = 582,
                title = "Tarisland",
                thumbnail = "https://www.freetogame.com/g/582/thumbnail.jpg",
                shortDescription = "A cross-platform MMORPG developed by Level Infinite and Published by Tencent.",
                gameUrl = "https://www.freetogame.com/open/tarisland",
                genre = "MMORPG",
                platform = "PC (Windows)",
                publisher = "Tencent",
                developer = "Level Infinite",
                releaseDate = "2024-06-22",
                freetogameProfileUrl = "https://www.freetogame.com/tarisland",
            ),
            VideoGameModel(
                id = 540,
                title = "Overwatch 2",
                thumbnail = "https://www.freetogame.com/g/540/thumbnail.jpg",
                shortDescription = "A hero-focused first-person team shooter from Blizzard Entertainment.",
                gameUrl = "https://www.freetogame.com/open/overwatch-2",
                genre = "Shooter",
                platform = "PC (Windows)",
                publisher = "Activision Blizzard",
                developer = "Blizzard Entertainment",
                releaseDate = "2022-10-04",
                freetogameProfileUrl = "https://www.freetogame.com/overwatch-2",
            ),
            VideoGameModel(
                id = 516,
                title = "PUBG: BATTLEGROUNDS",
                thumbnail = "https://www.freetogame.com/g/516/thumbnail.jpg",
                shortDescription = "Get into the action in one of the longest running battle royale games PUBG Battlegrounds.",
                gameUrl = "https://www.freetogame.com/open/pubg",
                genre = "Shooter",
                platform = "PC (Windows)",
                publisher = "KRAFTON, Inc.",
                developer = "KRAFTON, Inc.",
                releaseDate = "2022-01-12",
                freetogameProfileUrl = "https://www.freetogame.com/pubg",
            ),
            VideoGameModel(
                id = 508,
                title = "Enlisted",
                thumbnail = "https://www.freetogame.com/g/508/thumbnail.jpg",
                shortDescription = "Get ready to command your own World War II military squad in Gaijin and Darkflow.",
                gameUrl = "https://www.freetogame.com/open/enlisted",
                genre = "Shooter",
                platform = "PC (Windows)",
                publisher = "Gaijin Entertainment",
                developer = "Darkflow Software",
                releaseDate = "2021-04-08",
                freetogameProfileUrl = "https://www.freetogame.com/enlisted",
            ),
            VideoGameModel(
                id = 345,
                title = "Forge of Empires",
                thumbnail = "https://www.freetogame.com/g/345/thumbnail.jpg",
                shortDescription = "A free to play 2D browser-based online strategy game, become the leader and raise your city.",
                gameUrl = "https://www.freetogame.com/open/forge-of-empires",
                genre = "Strategy",
                platform = "Web Browser",
                publisher = "InnoGames",
                developer = "InnoGames",
                releaseDate = "2012-04-17",
                freetogameProfileUrl = "https://www.freetogame.com/forge-of-empires",
            ),
        )
    VaultScreen(
        onQueryChange = { _, _ -> },
        navController = rememberNavController(),
        videoGames = mockedList,
    )
}
