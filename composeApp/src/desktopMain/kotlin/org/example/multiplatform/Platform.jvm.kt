package org.example.multiplatform

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual object AppDispatchers : DispatcherProvider {
    actual override val io: CoroutineDispatcher = Dispatchers.IO
}