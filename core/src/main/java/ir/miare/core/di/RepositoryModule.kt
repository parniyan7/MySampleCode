package ir.miare.core.di

import ir.miare.core.data.repository.fakeRepository.FakeRepository
import ir.miare.core.data.repository.fakeRepository.FakeRepositoryImpl
import ir.miare.core.data.source.remote.webService.ApiWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Singleton
    @Provides
    fun provideFakeApiRepository(
        service: ApiWebService
    ): FakeRepository {
        return FakeRepositoryImpl(service) as FakeRepository
    }
}