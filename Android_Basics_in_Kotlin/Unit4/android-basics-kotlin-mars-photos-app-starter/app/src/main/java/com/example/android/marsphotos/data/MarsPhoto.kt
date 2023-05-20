package com.example.android.marsphotos.data

import com.squareup.moshi.Json

data class MarsPhoto(
    val id: String,
    // Json 객체의 key 값과 동일하게 해주기 위하여 어노테이션 사용
    @Json(name = "img_src")
    val imgSrcUrl: String
)
