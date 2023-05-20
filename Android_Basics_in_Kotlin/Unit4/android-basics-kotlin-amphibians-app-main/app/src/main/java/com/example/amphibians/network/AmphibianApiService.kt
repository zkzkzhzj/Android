/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.amphibians.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://developer.android.com/courses/pathways/android-basics-kotlin-unit-4-pathway-2/"
private const val DATA_URL = "android-basics-kotlin-unit-4-pathway-2-project-api.json"

// moshi builder 생성
val moshi = Moshi
    .Builder()
    // json 데이터를 코틀린 객체로 변경
    .add(KotlinJsonAdapterFactory())
    .build()

// retrofit builder 생성
val retrofit = Retrofit.Builder()
    // moshi converter factory 추가
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    // 기본 Url
    .baseUrl(BASE_URL)
    .build()

interface AmphibianApiService {
    @GET(DATA_URL)
    // 코루틴 내부에서 사용하는 메소드 이기 때문에 suspend 키워드 사용
    suspend fun getamphibains(): List<Amphibian>
}

// 싱글톤 class
object AmphibianApi {
    // 호출될 때 retrofit 객체 생성
    val retrofitService: AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }
}
