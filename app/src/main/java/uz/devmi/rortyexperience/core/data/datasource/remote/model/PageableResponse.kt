package uz.devmi.rortyexperience.core.data.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PageableResponse<T>(
    val info : PageInfoResponse,
    val results : List<T>
)
