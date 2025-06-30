package abhishek.gupta.coreNetwork.model.gameDetails

import kotlinx.serialization.Serializable

@Serializable
data class AddedByStatusDTO(
    val beaten: Int = 0,
    val dropped: Int = 0,
    val owned: Int = 0,
    val playing: Int = 0,
    val toplay: Int = 0,
    val yet: Int = 0
)