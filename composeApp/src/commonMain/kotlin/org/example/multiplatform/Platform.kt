package org.example.multiplatform

import kotlinx.coroutines.CoroutineDispatcher

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

interface DispatcherProvider {
    val io: CoroutineDispatcher
}

expect object AppDispatchers : DispatcherProvider {
    override val io: CoroutineDispatcher
}