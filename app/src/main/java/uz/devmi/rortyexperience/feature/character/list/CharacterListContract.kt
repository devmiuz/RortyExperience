package uz.devmi.rortyexperience.feature.character.list

import uz.devmi.rortyexperience.core.DisplayState
import uz.devmi.rortyexperience.core.domain.model.Character
import uz.devmi.rortyexperience.core.domain.model.Pageable

object CharacterListContract {
    data class State(
        val characters : DisplayState<Pageable<Character>> = DisplayState()
    )

    sealed class Action{

    }

    sealed class Effect {}
}