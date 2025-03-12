package uz.devmi.rortyexperience.core.network

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val networkStatus: Flow<NetworkStatus>
}

sealed class NetworkStatus {
    data object Available : NetworkStatus()
    data object Unavailable : NetworkStatus()
    data object Losing : NetworkStatus()
    data object Lost : NetworkStatus()
}
