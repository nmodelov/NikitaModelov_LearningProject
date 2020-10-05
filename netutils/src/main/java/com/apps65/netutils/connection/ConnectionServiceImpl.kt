package com.apps65.netutils.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import io.reactivex.Observable

class ConnectionServiceImpl constructor(context: Context) : ConnectionService {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val connectionState = Observable.create<Boolean> { emitter ->
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                emitter.onNext(true)
            }

            override fun onUnavailable() {
                emitter.onNext(false)
            }

            override fun onLost(network: Network) {
                emitter.onNext(false)
            }
        }
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build(),
            networkCallback
        )
        emitter.setCancellable { connectivityManager.unregisterNetworkCallback(networkCallback) }
    }
        .replay(1)
        .refCount()

    override fun observeConnectionState(): Observable<Boolean> = connectionState

    override fun hasConnection(): Boolean {
        val connectedNetwork = connectivityManager.allNetworks.firstOrNull { it.isConnected() }
        return connectedNetwork != null
    }

    private fun Network.isConnected(): Boolean {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(this)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }
}
