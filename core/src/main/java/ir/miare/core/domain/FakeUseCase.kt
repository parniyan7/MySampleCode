package ir.miare.core.domain

import ir.miare.core.data.ResponseState
import ir.miare.core.data.source.entity.fake.FakeDataEntity
import ir.miare.core.util.DelegateAdapterItem
import kotlinx.coroutines.flow.Flow

interface FakeUseCase {
    suspend fun getAllDataFromRemote(): Flow<ResponseState<List<FakeDataEntity>>>

    fun getFakeData(): List<DelegateAdapterItem>

    fun getTeamAndLeagueSortData(): List<DelegateAdapterItem>

    fun getMostGoalsScoredByAPlayerSortData(): List<DelegateAdapterItem>

    fun getAverageGoalsPerMatchSortData(): List<DelegateAdapterItem>

}