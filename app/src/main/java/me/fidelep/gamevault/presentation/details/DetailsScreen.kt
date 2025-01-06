package me.fidelep.gamevault.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.fidelep.gamevault.R
import me.fidelep.gamevault.domain.model.VideoGameModel
import me.fidelep.gamevault.ui.views.EditableTextItem
import me.fidelep.gamevault.ui.views.OptionButtons

@Composable
fun DetailsScreen(
    videoGame: VideoGameModel,
    onDeleteVideoGame: (VideoGameModel) -> Unit,
    onUpdateVideoGame: (VideoGameModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isEditing by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf(TextFieldValue(videoGame.title)) }
    var thumbnail by remember { mutableStateOf(TextFieldValue(videoGame.thumbnail)) }
    var shortDescription by remember { mutableStateOf(TextFieldValue(videoGame.shortDescription)) }
    var gameUrl by remember { mutableStateOf(TextFieldValue(videoGame.gameUrl)) }
    var genre by remember { mutableStateOf(TextFieldValue(videoGame.genre)) }
    var platform by remember { mutableStateOf(TextFieldValue(videoGame.platform)) }
    var publisher by remember { mutableStateOf(TextFieldValue(videoGame.publisher)) }
    var developer by remember { mutableStateOf(TextFieldValue(videoGame.developer)) }
    var releaseDate by remember { mutableStateOf(TextFieldValue(videoGame.releaseDate)) }
    var freetogameProfileUrl by remember { mutableStateOf(TextFieldValue(videoGame.freetogameProfileUrl)) }

    Box(
        modifier =
            modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
        ) {
            AsyncImage(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)),
                model = videoGame.thumbnail,
                placeholder = painterResource(id = R.drawable.ic_videogame),
                contentDescription = stringResource(R.string.miniatura_del_juego, videoGame.title),
                contentScale = ContentScale.FillBounds,
            )

            Spacer(modifier = Modifier.height(8.dp))

            OptionButtons(
                isEditing = isEditing,
                onDeleteVideoGame = { onDeleteVideoGame(videoGame) },
                onUpdateVideoGame = {
                    if (isEditing) {
                        onUpdateVideoGame(
                            videoGame.copy(
                                title = title.text,
                                thumbnail = thumbnail.text,
                                shortDescription = shortDescription.text,
                                gameUrl = gameUrl.text,
                                genre = genre.text,
                                platform = platform.text,
                                publisher = publisher.text,
                                developer = developer.text,
                                releaseDate = releaseDate.text,
                                freetogameProfileUrl = freetogameProfileUrl.text,
                            ),
                        )
                    }
                    isEditing = !isEditing
                },
            )

            Spacer(modifier = Modifier.height(16.dp))

            EditableTextItem(
                label = "Title",
                value = title,
                isEditing = isEditing,
                onValueChange = {
                    title = it
                },
            )
            EditableTextItem(
                label = "Thumbnail",
                value = thumbnail,
                isEditing = isEditing,
                onValueChange = { thumbnail = it },
            )
            EditableTextItem(
                label = "Short Description",
                value = shortDescription,
                isEditing = isEditing,
                onValueChange = {
                    shortDescription = it
                },
            )
            EditableTextItem(
                label = "Game URL",
                value = gameUrl,
                isEditing = isEditing,
                onValueChange = {
                    gameUrl = it
                },
            )
            EditableTextItem(
                label = "Genre",
                value = genre,
                isEditing = isEditing,
                onValueChange = { genre = it },
            )
            EditableTextItem(
                label = "Platform",
                value = platform,
                isEditing = isEditing,
                onValueChange = {
                    platform = it
                },
            )
            EditableTextItem(
                label = "Publisher",
                value = publisher,
                isEditing = isEditing,
                onValueChange = { publisher = it },
            )
            EditableTextItem(
                label = "Developer",
                value = developer,
                isEditing = isEditing,
                onValueChange = { developer = it },
            )
            EditableTextItem(
                label = "Release Date",
                value = releaseDate,
                isEditing = isEditing,
                onValueChange = { releaseDate = it },
            )
            EditableTextItem(
                label = "Freetogame Profile URL",
                value = freetogameProfileUrl,
                isEditing = isEditing,
                onValueChange = { freetogameProfileUrl = it },
            )
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    val mock =
        VideoGameModel(
            id = 582,
            title = "Mock of a game with a really really large title to display and test",
            thumbnail = "https://www.freetogame.com/g/582/thumbnail.jpg",
            shortDescription = "A cross-platform MMORPG developed by Level Infinite and Published by Tencent.",
            gameUrl = "https://www.freetogame.com/open/tarisland",
            genre = "MMORPG",
            platform = "PC (Windows)",
            publisher = "Tencent",
            developer = "Level Infinite",
            releaseDate = "2024-06-22",
            freetogameProfileUrl = "https://www.freetogame.com/tarisland",
        )
    DetailsScreen(mock, {}, {})
}
