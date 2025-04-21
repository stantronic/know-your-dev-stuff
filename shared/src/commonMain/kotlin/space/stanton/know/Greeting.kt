package space.stanton.know

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Know Your Dev Stuff!"
    }
}