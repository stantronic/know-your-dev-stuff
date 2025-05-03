package space.stanton.know

class Greeting {

//    private val peopleService: PeopleService = KoinInstance.koin.get()

    fun greet(): String {

        return "Know Your Dev Stuff! ${
            ""
//            peopleService.getPeople()
        }"
    }
}