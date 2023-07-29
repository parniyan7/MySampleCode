package ir.miare.core.data.source.entity.fake



import ir.miare.core.util.DelegateAdapterItem
import java.io.Serializable

data class FakeDataEntity(
    val league: LeagueEntity,
    var players: List<PlayerEntity>
)

data class LeagueEntity(
    val name: String,
    val country: String,
    val rank: Int,
    val totalMatches: Int,
): Serializable, DelegateAdapterItem {
    override fun id(): Any = name // or something else
    override fun content(): Any = name
}

data class PlayerEntity(
    val name: String,
    val team: TeamEntity,
    val totalGoal: Int
) : Serializable, DelegateAdapterItem {
    override fun id(): Any = name // or something else
    override fun content(): Any = name
}

data class TeamEntity(
    val name: String,
    val rank: Int
) : Serializable