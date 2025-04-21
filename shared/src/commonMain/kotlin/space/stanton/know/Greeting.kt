package space.stanton.know

import space.stanton.know.di.KoinInstance
import space.stanton.know.networking.PeopleService

class Greeting {

    private val peopleService: PeopleService = KoinInstance.koin.get()

    suspend fun greet(): String {

        return "Know Your Dev Stuff! ${
            peopleService.getPeople()
        }"
    }
}