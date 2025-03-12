package uz.devmi.rortyexperience.core

data class DisplayState<TYPE>(
    val data: TYPE? = null,
    val isLoading: Boolean = false,
    val error: AppError? = null,
)
