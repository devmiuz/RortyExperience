package uz.devmi.rortyexperience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.devmi.rortyexperience.core.network.NetworkMonitor
import uz.devmi.rortyexperience.core.network.NetworkStatus
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    networkMonitor: NetworkMonitor
) : ViewModel() {
    private val _networkState = MutableStateFlow<NetworkStatus>(NetworkStatus.Unavailable)
    val networkState: StateFlow<NetworkStatus> = _networkState.asStateFlow()

    init {
        networkMonitor.networkStatus
            .distinctUntilChanged()
            .onEach { _networkState.value = it }
            .launchIn(viewModelScope)
    }
}