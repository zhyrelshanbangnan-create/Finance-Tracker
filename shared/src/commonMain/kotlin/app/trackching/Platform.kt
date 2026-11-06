package app.trackching

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform