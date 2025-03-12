package uz.devmi.rortyexperience.feature.character.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.devmi.rortyexperience.core.BaseViewModel
import uz.devmi.rortyexperience.core.domain.repository.CharacterRepository
import uz.devmi.rortyexperience.feature.character.list.CharacterListContract.Action
import uz.devmi.rortyexperience.feature.character.list.CharacterListContract.Effect
import uz.devmi.rortyexperience.feature.character.list.CharacterListContract.State
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseViewModel<State, Action, Effect>(
    initialState = State()
) {

    init {
        fetchCharacters(0)
    }

    override fun onAction(action: Action) {
        TODO("Not yet implemented")
    }

    private fun fetchCharacters(page: Int) {
        handleApiCall(
            task = {
                characterRepository.getCharacters(page)
            },
            onModify = { modification ->
                updateState { it.copy(characters = modification) }
            },
            onSuccess = {payload->
                payload
            },
            onFailure = {error->
                error
            }
        )

    }

}