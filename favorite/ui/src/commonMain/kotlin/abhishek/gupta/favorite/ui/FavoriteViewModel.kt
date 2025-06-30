package abhishek.gupta.favorite.ui

import abhishekg.gupta.favorite.domain.useCase.DeleteUseCase
import abhishekg.gupta.favorite.domain.useCase.GetAllLocalCachedGamesUseCase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class FavoriteViewModel(
    private val getAllLocalCachedGameUseCase: GetAllLocalCachedGamesUseCase,
    private val deleteUseCase: DeleteUseCase
) : ViewModel() {

    val game = getAllLocalCachedGameUseCase.invoke().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),emptyList()
    )

    fun delete(id:Int) = viewModelScope.launch {
        deleteUseCase.invoke(id)
    }


}

