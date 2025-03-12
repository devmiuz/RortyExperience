package uz.devmi.rortyexperience.feature.character.list.parts

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.devmi.rortyexperience.core.domain.model.Character

@Composable
fun CharactersListPart(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    characters: List<Character>,
    isLoadingMore: Boolean,
    onItemClick: (Character) -> Unit
) {
    LazyColumn(
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        itemsIndexed(
            items = characters,
            key = { _, item -> "${item.id}${item.name}" }
        ) { index, item ->
            CharacterListItem(
                character = item,
                onClick = onItemClick
            )
        }

        if (isLoadingMore) {
            item {
                CircularProgressIndicator(Modifier.size(32.dp))
            }
        }
    }
}