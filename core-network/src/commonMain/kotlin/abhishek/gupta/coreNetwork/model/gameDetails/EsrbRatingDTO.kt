package abhishek.gupta.coreNetwork.model.gameDetails

import kotlinx.serialization.Serializable

@Serializable

data class EsrbRatingDTO(
    val id: Int = 0,
    val name: String = "",
    val slug: String =""

)