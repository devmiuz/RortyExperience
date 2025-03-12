package uz.devmi.rortyexperience.feature.character.list.parts

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uz.devmi.rortyexperience.core.domain.model.Character

@Composable
fun CharactersListPart(
    modifier: Modifier = Modifier,
    characters: List<Character>,
    onItemClick: (Character) -> Unit
) {
    LazyColumn {
        itemsIndexed(
            items = characters,
            key = { _, item -> "${item.id}${item.name}" }
        ) { index, item ->
            CharacterListItem(
                character = item,
                onClick = onItemClick
            )
        }
    }
}