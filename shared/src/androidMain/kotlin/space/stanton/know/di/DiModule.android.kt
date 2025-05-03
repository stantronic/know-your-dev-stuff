package space.stanton.know.di

import androidx.lifecycle.ViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import space.stanton.know.networking.ktorClient

actual fun platformModule() = module {
    factoryOf(::ConcreteDependency) { bind<AbstractDependency>() }
    ktorClient()
    viewModelOf(::QuestionsViewModel)
}


class QuestionsViewModel(private val exampleDependency: AbstractDependency) : ViewModel() {

    fun getMessage() = exampleDependency.message
}