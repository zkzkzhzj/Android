package com.example.busschedule.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

// 뷰 모델
class BusScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {

    fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()

    fun scheduleForStopName(name: String): Flow<List<Schedule>> = scheduleDao.getByStopName(name)
}

// 라이프 사이클에 자유로운 뷰 모델 생성 및 접근, 아니라면 라이프 사이클 처리를 뷰 모델 내부에서 해주어야함
class BusScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}