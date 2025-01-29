package org.example.multiplatform

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()

actual object AppDispatchers : DispatcherProvider {
    actual override val io: CoroutineDispatcher = Dispatchers.Default
}