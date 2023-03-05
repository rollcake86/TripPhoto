package com.rollcake.tripPhoto.network

// import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import retrofit2.Call
// import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://apis.data.go.kr/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .baseUrl(BASE_URL)
    .build()


interface TripsApiService {

//    https://apis.data.go.kr/B551011/EngService1/locationBasedList1?
//    serviceKey=hm3Ng%2Bp0hajUH1lyqqB1JTmURPuIidiOj%2BoR1I49TQDEJPB9eY9CrArmUXrlx1PQ1DqvA%2B%2FqNSJWJhFa73mamw%3D%3D&
//    numOfRows=10&
//    pageNo=1&
//    MobileOS=ETC&
//    MobileApp=AppTest&
//    _type=json&
//    listYN=Y&
//    arrange=C&
//    mapX=126.981611&
//    mapY=37.568477&
//    radius=1000

    @GET("B551011/EngService1/locationBasedList1")
    suspend fun getProperties(
        @Query("serviceKey") key: String,
        @Query("numOfRows") numOfRows: String,
        @Query("pageNo") pageNo: String,
        @Query("MobileOS") MobileOS: String,
        @Query("MobileApp") MobileApp: String,
        @Query("_type") _type: String,
        @Query("listYN") listYN: String,
        @Query("arrange") arrange: String,
        @Query("mapX") mapX: Double,
        @Query("mapY") mapY: Double,
        @Query("radius") radius: Int,
    ): Call<TripAPIProperty>
}

object TripApi {
    val retrofitService: TripsApiService by lazy { retrofit.create(TripsApiService::class.java) }
}
