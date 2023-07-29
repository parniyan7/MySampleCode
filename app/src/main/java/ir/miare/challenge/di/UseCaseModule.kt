package ir.miare.challenge.di



import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.miare.challenge.usecase.FakeUseCaseImpl
import ir.miare.core.data.repository.fakeRepository.FakeRepository
import ir.miare.core.domain.FakeUseCase
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideFakeUseCase(fakeRepository: FakeRepository): FakeUseCase {
        return FakeUseCaseImpl(fakeRepository)
    }
}