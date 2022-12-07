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
package com.example.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch

// 상태 클래스
enum class AmphibianApiStatus { LOADING, ERROR, DONE }

class AmphibianViewModel : ViewModel() {

    // 현재 상태 저장
    private val _status = MutableLiveData<AmphibianApiStatus>()

    val status: LiveData<AmphibianApiStatus>
        get() = _status

    // 받아온 양서류 리스트 저장
    private val _amphibianList = MutableLiveData<List<Amphibian>>()

    val amphibianList: LiveData<List<Amphibian>>
        get() = _amphibianList

    // 단일 양서류 데이터 저장
    private val _amphibian = MutableLiveData<Amphibian>()

    val amphibian: LiveData<Amphibian>
        get() = _amphibian

    init {
        setAmphibian()
    }

    // 받아온 데이터 세팅
    private fun setAmphibian() {
        // 백그라운드에서 데이터를 가져올 수 있도록 코루틴 사용
        viewModelScope.launch {
            // 데이터를 받는 도중이라면 LOADING 세팅
            _status.value = AmphibianApiStatus.LOADING
            try {
                // 데이터 가져오기
                _amphibianList.value = AmphibianApi.retrofitService.getamphibains()

                // 데이터를 정상적으로 가져왔다면 DONE 세팅
                _status.value = AmphibianApiStatus.DONE
            } catch (e: Exception) {
                // 에러가 발생했을 경우 ERROR 세팅
                _status.value = AmphibianApiStatus.ERROR

                // 데이터 리스트를 비워준다
                _amphibianList.value = listOf()
            }
        }
    }

    // 리사이클러 뷰에서 개별 아이템 클릭한 아이템 세팅
    fun onAmphibianClicked(amphibian: Amphibian) {
        _amphibian.value = amphibian
    }
}
