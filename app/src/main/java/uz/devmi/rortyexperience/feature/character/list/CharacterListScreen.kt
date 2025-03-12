package uz.devmi.rortyexperience.feature.character.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uz.devmi.rortyexperience.feature.character.list.parts.CharacterListScreenBody

@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CharacterListScreenBody(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier
    )

}