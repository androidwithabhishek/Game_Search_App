package abhishek.gupta.game.ui.game

import abhishek.gupta.common.domain.model.Game

import abhishek.gupta.game.domain.useCases.GetGameUseCases
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update


class GameViewModel(private val getGameUseCases: GetGameUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow(GameScreen.UiState())
    val uiState = _uiState.asStateFlow()
    private var currentPage = 1
    private var isLoading = false

    init {
        getGames()

    }

//    fun getGames() = getGameUseCases.invoke(currentPage).onStart {
//
//        _uiState.update { GameScreen.UiState(isLoading = true) }
//
//    }.onEach { result ->
//        result.onSuccess { data ->
//            _uiState.update {
//                GameScreen.UiState(data = data)
//            }
//
//
//        }.onFailure { error ->
//            _uiState.update {
//                GameScreen.UiState(error = error.message.toString())
//            }
//        }
//    }.launchIn(viewModelScope)
    fun getGames() {
        if (isLoading) return
        isLoading = true

        getGameUseCases(currentPage).onStart {
            _uiState.update { it.copy(isLoading = true) }
        }.onEach { result ->
            result.onSuccess { games ->
                _uiState.update {
                    it.copy(
                        data = (it.data ?: emptyList()) + games,
                        isLoading = false
                    )
                }
                currentPage++
            }.onFailure { e ->
                _uiState.update {
                    it.copy(error = e.message ?: "Unknown Error", isLoading = false)
                }
            }
            isLoading = false
        }.launchIn(viewModelScope)
    }

}

object GameScreen {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: List<Game>? = null
    )
}