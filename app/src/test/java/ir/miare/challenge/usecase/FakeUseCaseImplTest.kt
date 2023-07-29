package ir.miare.challenge.usecase


import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.mockk
import ir.miare.core.data.repository.fakeRepository.FakeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test


internal class FakeUseCaseImplTest{

    private val fakeRepository = mockk<FakeRepository>(relaxed = true)
    private val fakeUseCaseImpl = FakeUseCaseImpl(fakeRepository)

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `once getAllDataFromRemote method call, then getAllDataFromRemote calls from repository`(){
        runBlocking {
            fakeUseCaseImpl.getAllDataFromRemote()
            coVerify { fakeRepository.getFakeDataFromRemote() }
        }
    }

    @Test
    fun `once getFakeData method call, then getFakeData calls from repository`(){
            fakeUseCaseImpl.getFakeData()
            coVerify { fakeRepository.getFakeData() }
    }

    @Test
    fun `once getTeamAndLeagueSortData method call, then getTeamAndLeagueSortData calls from repository`(){
        fakeUseCaseImpl.getTeamAndLeagueSortData()
        coVerify { fakeRepository.getTeamAndLeagueSortData() }
    }

    @Test
    fun `once getMostGoalsScoredByAPlayerSortData method call, then getMostGoalsScoredByAPlayerSortData calls from repository`(){
        fakeUseCaseImpl.getMostGoalsScoredByAPlayerSortData()
        coVerify { fakeRepository.getMostGoalsScoredByAPlayerSortData() }
    }

    @Test
    fun `once getAverageGoalsPerMatchSortData method call, then getAverageGoalsPerMatchSortData calls from repository`(){
        fakeUseCaseImpl.getAverageGoalsPerMatchSortData()
        coVerify { fakeRepository.getAverageGoalsPerMatchSortData() }
    }

}