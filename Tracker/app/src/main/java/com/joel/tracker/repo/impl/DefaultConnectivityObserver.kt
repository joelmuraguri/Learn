package com.joel.tracker.repo.impl

import com.joel.tracker.domain.ConnectivityStatus
import com.joel.tracker.repo.ConnectivityObserver
import kotlinx.coroutines.flow.Flow

class DefaultConnectivityObserver : ConnectivityObserver {

    override fun observer(): Flow<ConnectivityStatus> {
        TODO("Not yet implemented")
    }

}