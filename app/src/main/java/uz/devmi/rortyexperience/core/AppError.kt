package uz.devmi.rortyexperience.core

sealed interface AppError {
    data object NotFound : AppError
    data object NetworkError : AppError
    data class Unknown(val message: String) : AppError
}