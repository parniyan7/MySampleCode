package ir.miare.core.data.source.remote.webService


import ir.miare.core.data.source.remote.apiModel.fake.FakeDataRemoteResponse
import retrofit2.Response
import retrofit2.http.GET
import ir.logicbase.mockfit.Mock



interface ApiWebService {
    @Mock("data.json")
    @GET("list")
    suspend fun getData(): Response<List<FakeDataRemoteResponse>>
}