package com.example.busschedule.application

import android.app.Application
import com.example.busschedule.database.AppDatabase

// 데이터 베이스를 1번만 생성(앱 실행 시)
class BusScheduleApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}