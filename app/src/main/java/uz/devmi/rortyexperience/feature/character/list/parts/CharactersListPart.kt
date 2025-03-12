package uz.devmi.rortyexperience.feature.character.list.parts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.devmi.rortyexperience.core.domain.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListPart(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    characters: List<Character>,
    isLoadingMore: Boolean,
    onItemClick: (Character) -> Unit,
    refresh: () -> Unit,
    isRefreshing: Boolean
) {
    PullToRefreshBox(
        modifier = modifier,
        onRefresh = refresh,
        isRefreshing = isRefreshing
    ) {
        LazyColumn(
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            itemsIndexed(
                items = characters,
                key = { _, item -> "${item.id}${item.name}" }
            ) { index, item ->
                CharacterListItem(
                    character = item,
                    onClick = onItemClick
                )
                if (index != characters.lastIndex){
                    HorizontalDivider()
                }
            }

            if (isLoadingMore) {
                item {
                    CircularProgressIndicator(Modifier.size(32.dp))
                }
            }
        }
    }
}