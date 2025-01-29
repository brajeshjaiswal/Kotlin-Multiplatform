package di

import org.example.multiplatform.viewmodel.HomeViewModel
import org.koin.dsl.module

val provideviewModelModule = module {
    single {
        HomeViewModel(get())
    }
}