package ru.sonya.week3.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sonya.week3.data.CatsRepository
import ru.sonya.week3.domain.GetCatsUseCase
import ru.sonya.week3.data.Repository
import ru.sonya.week3.domain.GetUseCase
import ru.sonya.week3.domain.UpdateCatsUseCase
import ru.sonya.week3.domain.UpdateUseCase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface GetUseCaseModule {
    @Binds
    fun bindGetUseCaseModule(
        getCatsUseCase: GetCatsUseCase,
    ): GetUseCase
}

@InstallIn(SingletonComponent::class)
@Module
interface UpdateUseCaseModule {
    @Binds
    fun bindUpdateUseCaseModule(
        updateCatsUseCase: UpdateCatsUseCase,
    ): UpdateUseCase
}

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideGetCatsUseCase(repository: Repository): GetCatsUseCase {
        return GetCatsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateCatsUseCase(repository: Repository): UpdateCatsUseCase {
        return UpdateCatsUseCase(repository)
    }

}
