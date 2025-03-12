package uz.devmi.rortyexperience.feature.character.list.parts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uz.devmi.rortyexperience.core.domain.model.Character

@Composable
fun CharacterListItem(
    modifier: Modifier = Modifier,
    character: Character,
    onClick: (Character) -> Unit
) {
    ListItem(
        headlineContent = { Text("Three line list item") },
        supportingContent = { Text("Secondary text that is long and perhaps goes onto another line") },
        leadingContent = {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
            )
        },
        trailingContent = { Text("meta") },
        modifier = modifier
                . fillMaxWidth ()
            .clickable { onClick(character) }
    )
}