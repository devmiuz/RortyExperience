package uz.devmi.rortyexperience.feature.character.list.parts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import uz.devmi.rortyexperience.core.domain.model.Character

@Composable
fun CharacterListItem(
    modifier: Modifier = Modifier,
    character: Character,
    onClick: (Character) -> Unit
) {
    ListItem(
        headlineContent = {
            Text(character.name, style = MaterialTheme.typography.titleMedium)
        },
        leadingContent = {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = "${character.name} image",
                modifier = Modifier
                    .size(56.dp)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(character) }
    )
}