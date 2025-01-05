package me.fidelep.gamevault.presentation.vault

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.fidelep.gamevault.R
import me.fidelep.gamevault.domain.model.VideoGameModel

@Composable
fun VideoGameItem(
    videoGame: VideoGameModel,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        onClick = onItemClick,
    ) {
        Column(
            modifier =
                Modifier
                    .wrapContentSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                modifier =
                    Modifier
                        .wrapContentSize()
                        .defaultMinSize(120.dp, 120.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)),
                model = videoGame.thumbnail,
                placeholder = painterResource(id = R.drawable.ic_videogame),
                contentDescription = stringResource(R.string.miniatura_del_juego, videoGame.title),
                contentScale = ContentScale.FillBounds,
            )
            
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = videoGame.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.genre_data, videoGame.genre),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.release_date_data, videoGame.releaseDate),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Preview
@Composable
private fun VideoGameItemPreview() {
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
    VideoGameItem(mock, {})
}
