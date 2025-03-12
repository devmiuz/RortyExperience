package uz.devmi.rortyexperience.feature.character.list

import uz.devmi.rortyexperience.core.AppError
import uz.devmi.rortyexperience.core.DisplayState
import uz.devmi.rortyexperience.core.domain.model.Character

object CharacterListContract {
    data class State(
        val characters: DisplayState<List<Character>> = DisplayState(),
        val isLoadingMore : Boolean = false,
        val loadMoreError: AppError? = null
    )

    sealed class Action {
        data object LoadNextPage : Action()

        data object Refresh : Action()

        data object DismissLoadMoreError : Action()

        data object Retry : Action()
    }

    sealed class Effect {}
}