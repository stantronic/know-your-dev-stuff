package space.stanton.know.di

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import space.stanton.know.networking.ktorClient

val appModule = module {
    factoryOf(::ConcreteDependency) { bind<AbstractDependency>() }
    ktorClient()
}


val KoinInstance = startKoin {
    modules(appModule)
}

interface AbstractDependency {
    val message: String
}

class ConcreteDependency : AbstractDependency {
    override val message: String = "Hello from ConcreteDependency"
}