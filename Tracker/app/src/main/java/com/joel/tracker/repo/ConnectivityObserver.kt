package com.joel.tracker.repo

import com.joel.tracker.domain.ConnectivityStatus
import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observer() : Flow<ConnectivityStatus>

}