package uz.devmi.rortyexperience.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ConnectivityObserver @Inject constructor(
    @ApplicationContext context: Context
) : NetworkMonitor {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _networkState = MutableStateFlow(getCurrentNetworkStatus()) // ✅ Store latest status
    override val networkStatus: StateFlow<NetworkStatus> = _networkState.asStateFlow()

    init {
        startNetworkCallback() // ✅ Register callback on init
    }

    private fun startNetworkCallback() {
        val networkCallback = object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                _networkState.value = NetworkStatus.Available
            }

            override fun onLost(network: Network) {
                _networkState.value = NetworkStatus.Lost
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                _networkState.value = NetworkStatus.Losing
            }

            override fun onUnavailable() {
                _networkState.value = NetworkStatus.Unavailable
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    private fun getCurrentNetworkStatus(): NetworkStatus {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return when {
            capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true -> NetworkStatus.Available
            else -> NetworkStatus.Unavailable
        }
    }
}
