package space.stanton.know

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform