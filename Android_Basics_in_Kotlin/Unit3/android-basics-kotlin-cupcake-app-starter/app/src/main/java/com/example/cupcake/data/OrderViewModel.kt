package com.example.cupcake.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

// 컵 케이크당 가격
private const val PRICE_PER_CUPCAKE = 2.00

// 당일 수령시 추가금액
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {
    // 주문 수량
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    // 맛
    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    // 수령 날짜
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    // 가격
    private val _price = MutableLiveData<Double>()

    /*
     * 가격 형식 지정
     * LiveData 변수를 받아 문자열로 포매팅하여 반환
     */
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    // 사용자에게 보여줄 수령 날짜
    val dateOptions = getPickupOptions()

    // 주문 수량 세팅
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        // 주문 수량을 받아 가격 최신화
        updatePrice()
    }

    // 케이크 맛 세팅
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    // 수령 날짜 세팅
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        // 당일 날짜라면 추가금액 설정
        updatePrice()
    }

    // 케이크 맛을 선택하였는지 체크
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    init {
        resetOrder()
    }

    // 선택 초기화
    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    // 케이크 수령 날짜
    private fun getPickupOptions(): List<String> {
        // 선택가능한 날짜 리스트
        val options = mutableListOf<String>()
        // 현재 날짜를 지정한 형식으로 가져옴
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        // 캘린더 인스턴스를 가져옴
        val calendar = Calendar.getInstance()
        // 지정된 횟수만큼 반복
        repeat(4) {
            // 현재 날짜를 담아주고
            options.add(formatter.format(calendar.time))
            // 하루증가 시킴
            calendar.add(Calendar.DATE, 1)
        }

        // 4일이 담긴 리스트 반환
        return options
    }

    // 케이크 가격
    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }
}
