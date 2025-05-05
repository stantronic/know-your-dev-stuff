package space.stanton.know.di

import androidx.lifecycle.ViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import space.stanton.know.presentation.QuestionsPresenter


actual fun platformModule() = module {
    viewModelOf(::QuestionsViewModel)
}


class QuestionsViewModel(private val exampleDependency: QuestionsPresenter) : ViewModel() {
    fun getMessage() = exampleDependency.message
}