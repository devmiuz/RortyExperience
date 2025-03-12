package uz.devmi.rortyexperience.core.data.repository

import arrow.core.Either
import uz.devmi.rortyexperience.core.AppError
import uz.devmi.rortyexperience.core.data.datasource.remote.Webservice
import uz.devmi.rortyexperience.core.data.mapper.toDomain
import uz.devmi.rortyexperience.core.data.mapper.toUserError
import uz.devmi.rortyexperience.core.domain.model.Character
import uz.devmi.rortyexperience.core.domain.model.Pageable
import uz.devmi.rortyexperience.core.domain.repository.CharacterRepository
import javax.inject.Inject


class CharacterRepositoryImp @Inject constructor(
    private val webservice: Webservice
) : CharacterRepository {

    override suspend fun getCharacters(page: Int): Either<AppError, Pageable<Character>> {
        return runCatching {
            webservice.getCharactersByPage(page)
        }.fold(
            onSuccess = { Either.Right(it.toDomain(page)) },
            onFailure = { Either.Left(it.toUserError()) }
        )
    }
}