package space.stanton.know

import space.stanton.know.di.AbstractDependency
import space.stanton.know.di.KoinInstance

class Greeting {
   val dep : AbstractDependency  = KoinInstance.koin.get()

    fun greet(): String {

        return "Know Your Dev Stuff! ${dep.message}"
    }
}