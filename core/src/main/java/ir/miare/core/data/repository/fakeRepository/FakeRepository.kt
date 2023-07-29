package ir.miare.core.data.repository.fakeRepository


import ir.miare.core.data.ResponseState
import ir.miare.core.data.extension.toFakeDataEntity
import ir.miare.core.data.repository.BaseRepository
import ir.miare.core.data.source.entity.fake.FakeDataEntity
import ir.miare.core.data.source.entity.fake.PlayerEntity
import ir.miare.core.data.source.remote.webService.ApiWebService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface FakeRepository {
    suspend fun getFakeDataFromRemote(): Flow<ResponseState<List<FakeDataEntity>>>

    fun getFakeData(): List<FakeDataEntity>

    fun getTeamAndLeagueSortData(): List<FakeDataEntity>

    fun getMostGoalsScoredByAPlayerSortData(): List<PlayerEntity>

    fun getAverageGoalsPerMatchSortData(): List<FakeDataEntity>
}

class FakeRepositoryImpl @Inject constructor(
    private val fakeApiWebService: ApiWebService
) : BaseRepository(), FakeRepository {

    var fakeList: MutableList<FakeDataEntity> = mutableListOf()

    override suspend fun getFakeDataFromRemote(): Flow<ResponseState<List<FakeDataEntity>>> = flow {

        try {
            emit(ResponseState.Loading)
            val response = fakeApiWebService.getData()
            fakeList.clear()
            fakeList.addAll( response.body()?.map { it.toFakeDataEntity() }?.toList() ?: listOf() )
            emit(responseMapper(response, fakeList))
        } catch (e: Exception) {
            emit(ResponseState.Error((e)))
        }
    }

    override fun getFakeData(): List<FakeDataEntity> = fakeList

    override fun getTeamAndLeagueSortData(): List<FakeDataEntity> {

        val sortedList = fakeList.sortedBy { item -> item.league.rank } // sort leagues

        sortedList.forEachIndexed { index, fakeDataEntity ->

            fakeDataEntity.players.sortedBy { item -> item.team.rank }.also { sortedPlayers ->
                sortedList[index].players = sortedPlayers
            }

        }

        return sortedList
    }

    override fun getMostGoalsScoredByAPlayerSortData(): List<PlayerEntity> {
        fakeList.flatMap { it.players }.also {
            it.sortedByDescending { item -> item.totalGoal }.also { result ->
                return result
            }
        }
    }

    override fun getAverageGoalsPerMatchSortData(): List<FakeDataEntity>{

        val sortedList: MutableList<FakeDataEntity> = mutableListOf()
        val pairList = mutableListOf< Pair<Int,Double> >() // index , average

        fakeList.forEachIndexed { index, fakeDataEntity ->
            pairList.add(
                Pair(
                    index,
                    fakeDataEntity.players.sumOf { item -> item.totalGoal } / fakeDataEntity.league.totalMatches.toDouble()
                )
            )
        }

        pairList.sortedByDescending { item -> item.second }.also {
                resultList ->

            resultList.forEach {
                sortedList.add( fakeList[it.first] )
            }

        }

        return sortedList
    }

}