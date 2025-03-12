package uz.devmi.rortyexperience.core.data.mapper

import retrofit2.HttpException
import uz.devmi.rortyexperience.core.AppError
import uz.devmi.rortyexperience.core.data.datasource.remote.model.CharacterResponse
import uz.devmi.rortyexperience.core.data.datasource.remote.model.PageInfoResponse
import uz.devmi.rortyexperience.core.data.datasource.remote.model.PageableResponse
import uz.devmi.rortyexperience.core.domain.model.Character
import uz.devmi.rortyexperience.core.domain.model.PageInfo
import uz.devmi.rortyexperience.core.domain.model.Pageable
import java.io.IOException

fun Throwable.toUserError(): AppError = when (this) {
    is HttpException -> if (code() == 404) AppError.NotFound else AppError.NetworkError
    is IOException -> AppError.NetworkError
    else -> AppError.Unknown(message ?: "Unknown error")
}

fun PageableResponse<CharacterResponse>.toDomain(currentPage: Int): Pageable<Character> {
    return Pageable(
        info = info.toDomain(currentPage),
        results = results.toDomain()
    )
}

fun PageInfoResponse.toDomain(currentPage: Int): PageInfo {
    return PageInfo(
        totalItemsCount = count ?: 0,
        totalPages = pages ?: 0,
        currentPage = currentPage,
        nextPage = parsePageFromUrl(nextLink),
        previousPage = parsePageFromUrl(prevLink)
    )
}

fun parsePageFromUrl(url: String?): Int? {
    return url?.substringAfter("page=", "")?.toIntOrNull()
}


fun List<CharacterResponse>.toDomain(): List<Character> {
    return map { it.toDomain() }
}

fun CharacterResponse.toDomain(): Character {
    return Character(
        id = id ?: -1,
        name = name ?: "",
        imageUrl = imageUrl ?: ""
    )
}