package com.apps65.netutils.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.shareIn

@OptIn(ExperimentalCoroutinesApi::class)
class FlowConnectionService constructor(
    context: Context,
    dispatcher: CoroutineDispatcher
) : ConnectionService {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val connectionFlow = callbackFlow<Boolean> {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                sendBlocking(true)
            }

            override fun onUnavailable() {
                sendBlocking(false)
            }

            override fun onLost(network: Network) {
                sendBlocking(false)
            }
        }
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(
            request,
            networkCallback
        )

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }.shareIn(
        scope = CoroutineScope(dispatcher),
        started = SharingStarted.WhileSubscribed(),
        replay = 1
    )

    override fun observeConnectionState(): Flow<Boolean> = connectionFlow

    override fun hasConnection(): Boolean {
        val connectedNetwork = connectivityManager.allNetworks
            .firstOrNull { it.isConnected() }
        return connectedNetwork != null
    }

    private fun Network.isConnected(): Boolean {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(this)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }
}
