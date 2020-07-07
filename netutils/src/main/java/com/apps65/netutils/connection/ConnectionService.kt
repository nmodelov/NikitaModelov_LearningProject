package com.apps65.netutils.connection

import io.reactivex.Observable

interface ConnectionService {
    fun observeConnectionState(): Observable<Boolean>
    fun hasConnection(): Boolean
}
