package space.stanton.know.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

expect fun platformModule(): Module

fun insertKoin() {
    startKoin {
        modules(platformModule(), sharedModule)
    }
}

