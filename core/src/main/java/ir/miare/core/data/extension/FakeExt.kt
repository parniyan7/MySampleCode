package ir.miare.core.data.extension

import ir.miare.core.data.source.entity.fake.FakeDataEntity
import ir.miare.core.data.source.entity.fake.LeagueEntity
import ir.miare.core.data.source.entity.fake.PlayerEntity
import ir.miare.core.data.source.entity.fake.TeamEntity
import ir.miare.core.data.source.remote.apiModel.fake.FakeDataRemoteResponse
import ir.miare.core.data.source.remote.apiModel.fake.LeagueRemoteResponse
import ir.miare.core.data.source.remote.apiModel.fake.PlayerRemoteResponse
import ir.miare.core.data.source.remote.apiModel.fake.TeamRemoteResponse
import ir.miare.core.util.DelegateAdapterItem


fun FakeDataRemoteResponse.toFakeDataEntity(): FakeDataEntity = FakeDataEntity(
    league = this.league.toLeagueEntity(),
    players = this.players.map { it.toPlayerEntity() }
)

fun LeagueRemoteResponse.toLeagueEntity(): LeagueEntity = LeagueEntity(
    name = this.name,
    country = this.country,
    rank = this.rank,
    totalMatches = this.totalMatches
)

fun PlayerRemoteResponse.toPlayerEntity(): PlayerEntity = PlayerEntity(
    name = this.name,
    team = this.team.toTeamEntity(),
    totalGoal = this.totalGoal
)

fun TeamRemoteResponse.toTeamEntity(): TeamEntity = TeamEntity(
    name = this.name,
    rank = this.rank
)


fun PlayerEntity.toPlayerDelegatedItem(): DelegateAdapterItem = this

fun List<PlayerEntity>.toPlayersDelegatedItems(): List<DelegateAdapterItem> =
    this.map { item -> item.toPlayerDelegatedItem() }

fun List<FakeDataEntity>.toFakeDataDelegatedItems(): List<DelegateAdapterItem> {

    val resulList = mutableListOf<DelegateAdapterItem>()

    for (item in this) {

        resulList.add(item.league)

        for (subItem in item.players) {
            resulList.add(subItem)
        }

    }

    return resulList
}
