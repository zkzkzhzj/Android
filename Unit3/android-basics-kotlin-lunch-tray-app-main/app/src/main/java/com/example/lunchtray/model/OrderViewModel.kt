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
package com.example.lunchtray.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.lunchtray.data.DataSource
import java.text.NumberFormat

class OrderViewModel : ViewModel() {

    // 메뉴 아이템
    val menuItems = DataSource.menuItems

    // 메인메뉴, 사이드메뉴, 디저트 각각 금액 저장
    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    // 세율(고정값)
    private val taxRate = 0.08

    // 메인메뉴 선택 항목 저장을 위한 변수
    private val _entree = MutableLiveData<MenuItem?>()
    val entree: LiveData<MenuItem?> = _entree

    // 사이드메뉴 선택 항목 저장
    private val _side = MutableLiveData<MenuItem?>()
    val side: LiveData<MenuItem?> = _side

    // 디저트 선택 항목 저장
    private val _accompaniment = MutableLiveData<MenuItem?>()
    val accompaniment: LiveData<MenuItem?> = _accompaniment

    // 음식값
    private val _subtotal = MutableLiveData(0.0)
    val subtotal: LiveData<String> = Transformations.map(_subtotal) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    // 총계(음식값 + 세금)
    private val _total = MutableLiveData(0.0)
    val total: LiveData<String> = Transformations.map(_total) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    // 세금
    private val _tax = MutableLiveData(0.0)
    val tax: LiveData<String> = Transformations.map(_tax) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    /**
     * Set the entree for the order.
     */
    fun setEntree(entree: String) {
        // 메인 메뉴를 선택한 후 변경이 있을 경우 저장된 값을 뺴줌
        _subtotal.value = _subtotal.value?.minus(previousEntreePrice)

        // 메인 메뉴를 선택한 후 변경할 경우 값을 초기화 해주기 위해 저장
        previousEntreePrice = _entree.value?.price ?: 0.0
        // 선택한 메뉴 저장
        _entree.value = menuItems[entree]
        // 금액 계산을 위하여 price 인수 전달
        _entree.value?.price?.let { updateSubtotal(it) }
    }

    /**
     * Set the side for the order.
     */
    fun setSide(side: String) {
        // if _side.value is not null, set the previous side price to the current side price.
        previousSidePrice = _side.value?.price ?: 0.0

        // if _subtotal.value is not null subtract the previous side price from the current
        // subtotal value. This ensures that we only charge for the currently selected side.
        _subtotal.value = _subtotal.value?.minus(previousSidePrice)

        // set the current side value to the menu item corresponding to the passed in string
        _side.value = menuItems[side]
        // update the subtotal to reflect the price of the selected side.
        _side.value?.price?.let { updateSubtotal(it) }
    }

    /**
     * Set the accompaniment for the order.
     */
    fun setAccompaniment(accompaniment: String) {
        previousAccompanimentPrice = _accompaniment.value?.price ?: 0.0

        _subtotal.value = _subtotal.value?.minus(previousAccompanimentPrice)

        _accompaniment.value = menuItems[accompaniment]
        _accompaniment.value?.price?.let { updateSubtotal(it) }
    }

    /**
     * Update subtotal value.
     */
    private fun updateSubtotal(itemPrice: Double) {
        // subtotal 값이 존재한다면 전달받은 값을 합산해주고 없다면 새롭게 세팅
        _subtotal.value = _subtotal.value?.plus(itemPrice) ?: itemPrice

        // 세금까지 합한 총 금액 구하기
        calculateTaxAndTotal()
    }

    /**
     * Calculate tax and update total.
     */
    fun calculateTaxAndTotal() {
        // _subtotal 값에 세율을 곱하여 세금 계산
        _tax.value = _subtotal.value?.times(taxRate)
        // _subtotal + _tax.value 값을 구하여 세금까지 더해진 총 금액 계산
        _total.value = _tax.value?.let { _subtotal.value?.plus(it) }
    }
/*
    private fun onSelectMenu(mld: MutableLiveData<MenuItem?>, menuName: String) {
        val previousSelectedMenuPrice = mld.value?.price ?: 0.0
        _subtotal.value = _subtotal.value?.minus(previousSelectedMenuPrice)

        val targetMenuItem = menuItems[menuName]
        mld.value = targetMenuItem
        updateSubtotal(targetMenuItem?.price ?: 0.0)
    }
*/

    /**
     * Reset all values pertaining to the order.
     */
    fun resetOrder() {
        // 초기화
        apply {
            this.previousEntreePrice = 0.0
            this.previousSidePrice = 0.0
            this.previousAccompanimentPrice = 0.0
            this._entree.value = null
            this._side.value = null
            this._accompaniment.value = null
            this._subtotal.value = 0.0
            this._total.value = 0.0
            this._tax.value = 0.0
        }
    }
}
