package uz.devmi.rortyexperience.core.domain.model

data class PageInfo(
    val totalItemsCount: Int,
    val totalPages: Int,
    val nextPage: Int?,
    val previousPage: Int?,
    val currentPage: Int
)
