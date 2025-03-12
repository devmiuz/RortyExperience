package uz.devmi.rortyexperience.core.data.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("id")
    val id : Int?,
    @SerialName("name")
    val name : String?,
    @SerialName("image")
    val imageUrl : String?
)
