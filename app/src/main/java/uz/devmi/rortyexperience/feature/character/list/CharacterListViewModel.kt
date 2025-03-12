package uz.devmi.rortyexperience.feature.character.list

import androidx.lifecycle.viewModelScope
import arrow.core.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.devmi.rortyexperience.core.BaseViewModel
import uz.devmi.rortyexperience.core.DisplayState
import uz.devmi.rortyexperience.core.domain.model.PageInfo
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

    private var pageInfo = PageInfo(
        totalItemsCount = 0,
        totalPages = 0,
        nextPage = null,
        previousPage = null,
        currentPage = 0
    )

    init {
        loadCharacters(1)
    }

    override fun onAction(action: Action) {
        when (action) {
            is Action.LoadNextPage -> {
                if (pageInfo.nextPage != null){
                    loadCharacters(pageInfo.nextPage!!)
                }
            }
        }
    }

    private fun loadCharacters(page: Int) {
        viewModelScope.launch {
            println("loading characters page $page")
            if (page == 1) {
                updateState { it.copy(characters = DisplayState(isLoading = true)) }
            } else {
                updateState { it.copy(isLoadingMore = true) }
            }

            when (val result = characterRepository.getCharacters(page)) {
                is Either.Left -> {
                    if (page == 1) {
                        updateState { it.copy(characters = DisplayState(error = result.value)) }
                    } else {
                        updateState { it.copy(isLoadingMore = false) }
                    }
                }

                is Either.Right -> {
                    pageInfo = result.value.info

                    if (page == 1) {
                        updateState { it.copy(characters = DisplayState(data = result.value.results)) }
                    } else {
                        updateState { previousState ->
                            previousState.copy(
                                characters = DisplayState(
                                    data = previousState.characters.data?.plus(result.value.results)
                                ),
                                isLoadingMore = false
                            )
                        }
                    }
                }
            }
        }
    }
}