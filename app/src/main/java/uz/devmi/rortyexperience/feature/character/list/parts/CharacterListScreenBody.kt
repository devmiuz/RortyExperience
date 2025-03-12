package uz.devmi.rortyexperience.feature.character.list.parts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uz.devmi.rortyexperience.core.DisplayStateHandler
import uz.devmi.rortyexperience.feature.character.list.CharacterListContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreenBody(
    modifier: Modifier = Modifier,
    state: CharacterListContract.State,
    onAction: (CharacterListContract.Action) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Characters") }
            )
        },
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        DisplayStateHandler(
            state = state.characters
        ) {characters ->
            CharactersListPart(characters = characters.results) { }
        }
    }
}