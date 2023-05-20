package com.example.android.marsphotos.network

import com.example.android.marsphotos.data.MarsPhoto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// 접속할 주소
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

// 모시 빌더
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// 레트로핏 인스턴스
private val retrofit = Retrofit.Builder()
    // ScalarsConverterFactory.create()
    // URL 를 로드하고 API 를 빌드하기위한 팩토리 생성(JSON 값에서 String 값으로 변환)
    // 현재는 모시 빌더로 변경
    // 모시를 사용하여 코틀린 객체로 가져옴(List<MarsPhoto>)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    // URL 로드
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {
    // GET 방식으로 통신하며 이미지를 가져오는 메소드(Retrofit 에서 정의한 엔드 포인트[baseUrl] 추가)
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

// 싱글톤 객체
object MarsApi {
    // 처음 사용할 때 초기화 될 수 있도록 lazy 사용
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}
