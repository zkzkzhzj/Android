package com.example.busschedule.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * 데이터 베이스 테이블 정의
 * @Entity 어노테이션을 통해서 테이블이라고 알림
 */
@Entity
data class Schedule(
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "stop_name") val stopName: String,
    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int,
)
