package ir.miare.challenge.fragment


import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.mockk.verify
import ir.miare.challenge.ui.fragment.RankingFragmentVm
import ir.miare.core.domain.FakeUseCase
import org.junit.Test


internal class RankingFragmentVmTest{

    private val fakeUseCase = mockk<FakeUseCase>(relaxed = true)

    private val vm = RankingFragmentVm(fakeUseCase)

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `call getFakeData method, then ensure getFakeData of usecase was called`(){
        vm.getFakeData()
        verify { fakeUseCase.getFakeData() }
    }

    @Test
    fun `call getTeamAndLeagueSortData method, then ensure getTeamAndLeagueSortData of usecase was called`(){
        vm.getTeamAndLeagueSortData()
        verify { fakeUseCase.getTeamAndLeagueSortData() }
    }

    @Test
    fun `call getMostGoalsScoredByAPlayerSortData method, then ensure getMostGoalsScoredByAPlayerSortData of usecase was called`(){
        vm.getMostGoalsScoredByAPlayerSortData()
        verify { fakeUseCase.getMostGoalsScoredByAPlayerSortData() }
    }

    @Test
    fun `call getAverageGoalsPerMatchSortData method, then ensure getAverageGoalsPerMatchSortData of usecase was called`(){
        vm.getAverageGoalsPerMatchSortData()
        verify { fakeUseCase.getAverageGoalsPerMatchSortData() }
    }


}