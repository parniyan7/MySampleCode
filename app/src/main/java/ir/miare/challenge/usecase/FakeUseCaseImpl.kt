package ir.miare.challenge.usecase


import ir.miare.core.data.ResponseState
import ir.miare.core.data.extension.toFakeDataDelegatedItems
import ir.miare.core.data.extension.toPlayersDelegatedItems
import ir.miare.core.data.repository.fakeRepository.FakeRepository
import ir.miare.core.data.source.entity.fake.FakeDataEntity
import ir.miare.core.domain.FakeUseCase
import ir.miare.core.util.DelegateAdapterItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FakeUseCaseImpl @Inject constructor(
    private val fakeRepository: FakeRepository
): FakeUseCase {

    override suspend fun getAllDataFromRemote(): Flow<ResponseState<List<FakeDataEntity>>> = fakeRepository.getFakeDataFromRemote()

    override fun getFakeData(): List<DelegateAdapterItem> = fakeRepository.getFakeData().toFakeDataDelegatedItems()

    override fun getTeamAndLeagueSortData(): List<DelegateAdapterItem> = fakeRepository.getTeamAndLeagueSortData().toFakeDataDelegatedItems()

    override fun getMostGoalsScoredByAPlayerSortData(): List<DelegateAdapterItem> = fakeRepository.getMostGoalsScoredByAPlayerSortData().toPlayersDelegatedItems()

    override fun getAverageGoalsPerMatchSortData(): List<DelegateAdapterItem> = fakeRepository.getAverageGoalsPerMatchSortData().toFakeDataDelegatedItems()

}