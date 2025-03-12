package uz.devmi.rortyexperience.feature.character.list

import uz.devmi.rortyexperience.core.DisplayState
import uz.devmi.rortyexperience.core.domain.model.Character

object CharacterListContract {
    data class State(
        val characters: DisplayState<List<Character>> = DisplayState(),
        val isLoadingMore : Boolean = false
    )

    sealed class Action {
        data object LoadNextPage : Action()
    }

    sealed class Effect {}
}