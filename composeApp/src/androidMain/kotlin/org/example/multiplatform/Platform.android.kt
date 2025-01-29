package org.example.multiplatform

import android.os.Build
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual object AppDispatchers : DispatcherProvider {
    actual override val io: CoroutineDispatcher = Dispatchers.IO
}