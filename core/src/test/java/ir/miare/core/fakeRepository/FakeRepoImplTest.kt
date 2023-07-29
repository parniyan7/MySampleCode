package ir.miare.core.fakeRepository



import io.mockk.MockKAnnotations
import io.mockk.mockk
import ir.miare.core.MainDispatcherRule
import ir.miare.core.data.repository.fakeRepository.FakeRepositoryImpl
import ir.miare.core.data.source.entity.fake.FakeDataEntity
import ir.miare.core.data.source.entity.fake.LeagueEntity
import ir.miare.core.data.source.entity.fake.PlayerEntity
import ir.miare.core.data.source.entity.fake.TeamEntity
import ir.miare.core.data.source.remote.webService.ApiWebService
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class FakeRepositoryImplTest {

    @get:Rule
    val rule = MainDispatcherRule()

    private val webService = mockk<ApiWebService>(relaxed = true)
    private val repo = FakeRepositoryImpl(webService)

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }


    @Test
    fun `call getTeamAndLeagueSortData method returns valid sorted data`() {
        repo.fakeList = getFakeList()
        val result = repo.getTeamAndLeagueSortData()
        Assert.assertEquals( getTeamAndLeagueSortData(), result)
    }

    @Test
    fun `call getMostGoalsScoredByAPlayerSortData method returns valid sorted data`() {
        repo.fakeList = getFakeList()
        val result = repo.getMostGoalsScoredByAPlayerSortData()
        Assert.assertEquals( getMostGoalsScoredByAPlayerSortData(), result)
    }

    @Test
    fun `call getAverageGoalsPerMatchSortData method returns valid sorted data`() {
        repo.fakeList = getFakeList()
        val result = repo.getAverageGoalsPerMatchSortData()
        Assert.assertEquals( getAverageGoalsPerMatchSortData(), result)
    }

    @Test
    fun `call getFakeData method returns valid sorted data`() {
        repo.fakeList = getFakeList()
        val result = repo.getFakeData()
        Assert.assertEquals( getFakeList(), result)
    }


    // region helper functions

    private fun getFakeList(): MutableList<FakeDataEntity> = mutableListOf(
        FakeDataEntity(
            LeagueEntity(
                "Serie A",
                "Italy",
                3,
                32
            ),
            listOf(
                PlayerEntity(
                    "Edin Dzeko",
                    TeamEntity(
                        "Inter",
                        2
                    ),
                    17
                ),

                PlayerEntity(
                    "Angel Di Maria",
                    TeamEntity(
                        "Juventus",
                        3
                    ),
                    9
                ),

                PlayerEntity(
                    "Zlatan Ibrahimovic",
                    TeamEntity(
                        "Ac Milan",
                        1
                    ),
                    17
                ),

                )
        ),

        FakeDataEntity(
            LeagueEntity(
                "Premier League",
                "England",
                1,
                38
            ),
            listOf(
                PlayerEntity(
                    "Mohammad Salah",
                    TeamEntity(
                        "Liverpool",
                        2
                    ),
                    25
                ),

                PlayerEntity(
                    "Erling Haaland",
                    TeamEntity(
                        "Man City",
                        1
                    ),
                    33
                ),

                PlayerEntity(
                    "Marcus Rashford",
                    TeamEntity(
                        "Man United",
                        3
                    ),
                    17
                ),

                )
        ),

        FakeDataEntity(
            LeagueEntity(
                "LaLiga",
                "Spain",
                2,
                36
            ),
            listOf(
                PlayerEntity(
                    "Antoine Griezmann",
                    TeamEntity(
                        "Atletico",
                        3
                    ),
                    21
                ),

                PlayerEntity(
                    "Karim Benzema",
                    TeamEntity(
                        "Real Madrid",
                        2
                    ),
                    27
                ),

                PlayerEntity(
                    "Robert Lewandowski",
                    TeamEntity(
                        "Barcelona",
                        1
                    ),
                    23
                ),

                )
        ),
    )

    private fun getTeamAndLeagueSortData(): MutableList<FakeDataEntity> = mutableListOf(

        FakeDataEntity(
            LeagueEntity(
                "Premier League",
                "England",
                1,
                38
            ),
            listOf(

                PlayerEntity(
                    "Erling Haaland",
                    TeamEntity(
                        "Man City",
                        1
                    ),
                    33
                ),

                PlayerEntity(
                    "Mohammad Salah",
                    TeamEntity(
                        "Liverpool",
                        2
                    ),
                    25
                ),

                PlayerEntity(
                    "Marcus Rashford",
                    TeamEntity(
                        "Man United",
                        3
                    ),
                    17
                ),

                )
        ),

        FakeDataEntity(
            LeagueEntity(
                "LaLiga",
                "Spain",
                2,
                36
            ),
            listOf(

                PlayerEntity(
                    "Robert Lewandowski",
                    TeamEntity(
                        "Barcelona",
                        1
                    ),
                    23
                ),

                PlayerEntity(
                    "Karim Benzema",
                    TeamEntity(
                        "Real Madrid",
                        2
                    ),
                    27
                ),

                PlayerEntity(
                    "Antoine Griezmann",
                    TeamEntity(
                        "Atletico",
                        3
                    ),
                    21
                ),

                )
        ),

        FakeDataEntity(
            LeagueEntity(
                "Serie A",
                "Italy",
                3,
                32
            ),
            listOf(
                PlayerEntity(
                    "Zlatan Ibrahimovic",
                    TeamEntity(
                        "Ac Milan",
                        1
                    ),
                    17
                ),

                PlayerEntity(
                    "Edin Dzeko",
                    TeamEntity(
                        "Inter",
                        2
                    ),
                    17
                ),

                PlayerEntity(
                    "Angel Di Maria",
                    TeamEntity(
                        "Juventus",
                        3
                    ),
                    9
                ),


                )
        ),

        )

    private fun getMostGoalsScoredByAPlayerSortData(): List<PlayerEntity> = listOf(
        PlayerEntity(
            "Erling Haaland",
            TeamEntity(
                "Man City",
                1
            ),
            33
        ),
        PlayerEntity(
            "Karim Benzema",
            TeamEntity(
                "Real Madrid",
                2
            ),
            27
        ),
        PlayerEntity(
            "Mohammad Salah",
            TeamEntity(
                "Liverpool",
                2
            ),
            25
        ),
        PlayerEntity(
            "Robert Lewandowski",
            TeamEntity(
                "Barcelona",
                1
            ),
            23
        ),
        PlayerEntity(
            "Antoine Griezmann",
            TeamEntity(
                "Atletico",
                3
            ),
            21
        ),
        PlayerEntity(
            "Edin Dzeko",
            TeamEntity(
                "Inter",
                2
            ),
            17
        ),
        PlayerEntity(
            "Zlatan Ibrahimovic",
            TeamEntity(
                "Ac Milan",
                1
            ),
            17
        ),
        PlayerEntity(
            "Marcus Rashford",
            TeamEntity(
                "Man United",
                3
            ),
            17
        ),
        PlayerEntity(
            "Angel Di Maria",
            TeamEntity(
                "Juventus",
                3
            ),
            9
        ),

        )

    private fun getAverageGoalsPerMatchSortData(): MutableList<FakeDataEntity> = mutableListOf(

        FakeDataEntity(
            LeagueEntity(
                "Premier League",
                "England",
                1,
                38
            ),
            listOf(
                PlayerEntity(
                    "Mohammad Salah",
                    TeamEntity(
                        "Liverpool",
                        2
                    ),
                    25
                ),

                PlayerEntity(
                    "Erling Haaland",
                    TeamEntity(
                        "Man City",
                        1
                    ),
                    33
                ),

                PlayerEntity(
                    "Marcus Rashford",
                    TeamEntity(
                        "Man United",
                        3
                    ),
                    17
                ),

                )
        ),

        FakeDataEntity(
            LeagueEntity(
                "LaLiga",
                "Spain",
                2,
                36
            ),
            listOf(
                PlayerEntity(
                    "Antoine Griezmann",
                    TeamEntity(
                        "Atletico",
                        3
                    ),
                    21
                ),

                PlayerEntity(
                    "Karim Benzema",
                    TeamEntity(
                        "Real Madrid",
                        2
                    ),
                    27
                ),

                PlayerEntity(
                    "Robert Lewandowski",
                    TeamEntity(
                        "Barcelona",
                        1
                    ),
                    23
                ),

                )
        ),

        FakeDataEntity(
            LeagueEntity(
                "Serie A",
                "Italy",
                3,
                32
            ),
            listOf(
                PlayerEntity(
                    "Edin Dzeko",
                    TeamEntity(
                        "Inter",
                        2
                    ),
                    17
                ),

                PlayerEntity(
                    "Angel Di Maria",
                    TeamEntity(
                        "Juventus",
                        3
                    ),
                    9
                ),

                PlayerEntity(
                    "Zlatan Ibrahimovic",
                    TeamEntity(
                        "Ac Milan",
                        1
                    ),
                    17
                ),

                )
        ),
    )

    // endregion

}
