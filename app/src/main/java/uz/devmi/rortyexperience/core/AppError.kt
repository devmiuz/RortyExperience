package uz.devmi.rortyexperience.core

sealed interface AppError {
    object NotFound : AppError
    object NetworkError : AppError
    data class Unknown(val message: String) : AppError
}