package uz.devmi.rortyexperience.feature.character.list.parts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.flow.distinctUntilChanged
import uz.devmi.rortyexperience.R
import uz.devmi.rortyexperience.core.DisplayStateHandler
import uz.devmi.rortyexperience.feature.character.list.CharacterListContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreenBody(
    modifier: Modifier = Modifier,
    state: CharacterListContract.State,
    onAction: (CharacterListContract.Action) -> Unit
) {
    val listState = rememberLazyListState()

    SideEffect {
        println("AA : ${state.isLoadingMore}")
    }

    LaunchedEffect(state.isLoadingMore) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .distinctUntilChanged()
            .collect { lastVisibleIndex ->
                println("isLoading : ${state.isLoadingMore}")
                if (state.isLoadingMore) return@collect
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisibleIndex != null && lastVisibleIndex >= totalItems - 1) {

                    println("need to load more $lastVisibleIndex")
                    onAction(CharacterListContract.Action.LoadNextPage)
                }
            }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.characters),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        },
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        DisplayStateHandler(
            state = state.characters,
            loadingContent = { CharacterListShimmer() },
            modifier = Modifier
                .padding(innerPadding)
        ) { characters ->
            CharactersListPart(
                listState = listState,
                characters = characters,
                isLoadingMore = state.isLoadingMore,
                onItemClick = {}
            )
        }
    }
}