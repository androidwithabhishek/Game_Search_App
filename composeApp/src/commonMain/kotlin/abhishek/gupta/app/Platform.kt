package abhishek.gupta.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform