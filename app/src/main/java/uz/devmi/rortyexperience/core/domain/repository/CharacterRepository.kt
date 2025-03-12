package uz.devmi.rortyexperience.core.domain.repository

import arrow.core.Either
import uz.devmi.rortyexperience.core.AppError
import uz.devmi.rortyexperience.core.domain.model.Character
import uz.devmi.rortyexperience.core.domain.model.Pageable

interface CharacterRepository {

    suspend fun getCharacters(page: Int): Either<AppError, Pageable<Character>>
}