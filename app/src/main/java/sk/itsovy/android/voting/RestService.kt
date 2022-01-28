package sk.itsovy.android.voting

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

private val RETROFIT = Retrofit.Builder()
    .baseUrl("https://ics.upjs.sk/~opiela/rest/index.php/")
    .build()

interface RestService {

    @GET("vote/{name}/{choice}")
    suspend fun sendVote(
        @Path("name") name: String,
        @Path("choice") choice: String
    )

}

object RestApi {
    val REST_SERVICE : RestService by lazy {
        RETROFIT.create(RestService::class.java)
    }
}