package com.example.busschedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao

// 데이터 베이스 생성자
@Database(entities = arrayOf(Schedule::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    // 생성된 데이터 베이스가 있다면 리턴해주고 아니라면 생성해서 리턴
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/bus_schedule.db")
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}