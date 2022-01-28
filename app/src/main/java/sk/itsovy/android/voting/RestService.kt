package sk.itsovy.android.voting

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val MOSHI = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val RETROFIT = Retrofit.Builder()
    .baseUrl("https://ics.upjs.sk/~opiela/rest/index.php/")
    .addConverterFactory(MoshiConverterFactory.create(MOSHI))
    .build()

interface RestService {

    @GET("vote/{name}/{choice}")
    suspend fun sendVote(
        @Path("name") name: String,
        @Path("choice") choice: String
    )

    @GET("votes")
    suspend fun getVotes(): List<Vote>

}

data class Vote(
    val name: String,
    @Json(name = "vote") val choice: String
)

object RestApi {
    val REST_SERVICE: RestService by lazy {
        RETROFIT.create(RestService::class.java)
    }
}