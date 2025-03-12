package uz.devmi.rortyexperience.core.data.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageInfoResponse(
    @SerialName("count")
    val count: Int?,
    @SerialName("pages")
    val pages : Int?,
    @SerialName("next")
    val nextLink : String?,
    @SerialName("prev")
    val prevLink : String?
)
