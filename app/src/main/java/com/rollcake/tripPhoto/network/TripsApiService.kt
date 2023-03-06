package com.rollcake.tripPhoto.network

// import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import org.json.JSONObject
import retrofit2.Call
// import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.FormUrlEncoded


private const val BASE_URL = "https://apis.data.go.kr"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface TripsApiService {

    @GET("/B551011/EngService1/locationBasedList1?serviceKey=hm3Ng%2Bp0hajUH1lyqqB1JTmURPuIidiOj%2BoR1I49TQDEJPB9eY9CrArmUXrlx1PQ1DqvA%2B%2FqNSJWJhFa73mamw%3D%3D")
    fun getProperties(
        @Query("numOfRows") numOfRows: Int = 10 ,
        @Query("pageNo") pageNo: Int = 1,
        @Query("MobileOS") MobileOS: String = "ETC",
        @Query("MobileApp") MobileApp: String = "KoreaTrip",
        @Query("_type") _type: String = "json",
        @Query("listYN") listYN: String = "Y",
        @Query("arrange") arrange: String = "C",
        @Query("mapX") mapX: Double,
        @Query("mapY") mapY: Double,
        @Query("radius") radius: Int = 1000,
    ): Deferred<String>
}

object TripApi {
    val retrofitService: TripsApiService by lazy { retrofit.create(TripsApiService::class.java) }
}
