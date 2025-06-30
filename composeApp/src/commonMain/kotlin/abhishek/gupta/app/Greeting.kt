package abhishek.gupta.app
import kotlinx.serialization.Serializable


@Serializable
class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}