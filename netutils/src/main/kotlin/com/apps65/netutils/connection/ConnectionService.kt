package com.apps65.netutils.connection

import kotlinx.coroutines.flow.Flow

interface ConnectionService {
    fun observeConnectionState(): Flow<Boolean>
    fun hasConnection(): Boolean
}
