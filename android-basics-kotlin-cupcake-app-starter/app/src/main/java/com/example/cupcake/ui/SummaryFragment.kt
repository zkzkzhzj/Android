/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.R
import com.example.cupcake.data.OrderViewModel
import com.example.cupcake.databinding.FragmentSummaryBinding

/**
 * [SummaryFragment] contains a summary of the order details with a button to share the order
 * via another app.
 */
class SummaryFragment : Fragment() {

    // Binding object instance corresponding to the fragment_summary.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentSummaryBinding? = null

    /*
     * 뷰 모델 선언
     * by viewModels() 를 사용하게 되면 해당 프래그먼트 범위의 ViewModel 이 저장되지만
     * by activityViewModels() 를 사용하면 프래그먼트의 상단 액티비티 범위의 ViewModel 이 저장되어
     * 액티비티에서 사용하는 프래그먼트끼리 공유 가능
     */
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // 라이프 사이클 전달
            lifecycleOwner = viewLifecycleOwner
            // DataBinding 으로 ViewModel 객체 전달
            viewModel = sharedViewModel
            // 자기자신을 넘겨주어 goToNextScreen 메소드를 사용할 수 있게 함
            summaryFragment = this@SummaryFragment
        }
    }

    /**
     * 주문 정보를 이메일로 전송한다.
     */
    fun sendOrder() {
        // 개수를 가져옴
        val numberOfCupcakes = sharedViewModel.quantity.value ?: 0
        // 주문 정보를 가져온다
        val orderSummary = getString(
            R.string.order_details,
            // 수량 문자열 사용
            resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
            sharedViewModel.flavor.value.toString(),
            sharedViewModel.date.value.toString(),
            sharedViewModel.price.value.toString()
        )

        // 이메일 인텐트를 만들어준다(SEND 속성)
        val intent = Intent(Intent.ACTION_SEND)
            // 텍스트 타입 지정
            .setType("text/plain")
            // 제목
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
            // 내용
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        // 해당 인텐트를 처리할 수 있는(이메일을 보낼 수 있는) 다른 앱이 있는지 확인하고 난뒤에 인텐트 실행
        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }
    }

    /**
     * 취소버튼 클릭
     */
    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
