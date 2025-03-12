package uz.devmi.rortyexperience.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.devmi.rortyexperience.core.data.repository.CharacterRepositoryImp
import uz.devmi.rortyexperience.core.domain.repository.CharacterRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCharacterRepository(impl: CharacterRepositoryImp): CharacterRepository

}