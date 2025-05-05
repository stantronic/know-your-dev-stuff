package space.stanton.know.di

import org.koin.dsl.module
import space.stanton.know.data.questionsRepository
import space.stanton.know.networking.ktorClient
import space.stanton.know.presentation.questionsPresenter

val sharedModule = module {
    questionsPresenter()
    questionsRepository()
    ktorClient()
}