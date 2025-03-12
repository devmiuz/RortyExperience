package uz.devmi.rortyexperience.core.domain.model

data class Pageable<T>(
    val info : PageInfo?,
    val results : List<T>
)
