package space.stanton.know.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

//val sharedModule = module {
//    factoryOf(::ConcreteDependency) { bind<AbstractDependency>() }
//    ktorClient()
//}
//
expect fun platformModule(): Module


fun insertKoin(){
    startKoin {
        modules(platformModule())
    }
}

//
//val KoinInstance = startKoin {
//    modules(platformModule())
//}

interface AbstractDependency {
    val message: String
}

class ConcreteDependency : AbstractDependency {
    override val message: String = "Hello from ConcreteDependency"
}