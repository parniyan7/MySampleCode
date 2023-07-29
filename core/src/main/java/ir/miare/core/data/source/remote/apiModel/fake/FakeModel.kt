package ir.miare.core.data.source.remote.apiModel.fake


import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class FakeDataRemoteResponse(
    @JsonProperty("league") var league: LeagueRemoteResponse,
    @JsonProperty("players") var players: List<PlayerRemoteResponse>
)

data class LeagueRemoteResponse(
    @JsonProperty("name") val name: String,
    @JsonProperty("country") val country: String,
    @JsonProperty("rank") val rank: Int,
    @JsonProperty("total_matches") val totalMatches: Int,
)

data class PlayerRemoteResponse(
    @JsonProperty("name") val name: String,
    @JsonProperty("team") val team: TeamRemoteResponse,
    @JsonProperty("total_goal") val totalGoal: Int
) : Serializable

data class TeamRemoteResponse(
    @JsonProperty("name") val name: String,
    @JsonProperty("rank") val rank: Int
) : Serializable