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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.R
import com.example.cupcake.data.OrderViewModel
import com.example.cupcake.databinding.FragmentStartBinding

/**
 * This is the first screen of the Cupcake app. The user can choose how many cupcakes to order.
 */
class StartFragment : Fragment() {

    // Binding object instance corresponding to the fragment_start.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentStartBinding? = null

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
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 자기자신을 넘겨주어 버튼 클릭 이벤트를 넘겨줌
        binding?.startFragment = this
    }

    /**
     * 주문 받은 컵케이크의 개수를 받고 다음 프래그먼트(flavor)로 이동한다.
     */
    fun orderCupcake(quantity: Int) {
        // 주문받은 케이크의 개수를 저장(ViewModel)
        sharedViewModel.setQuantity(quantity)
        // 컵 케이크의 맛이 설정되어 있지 않닫면 기본맛을 바닐라 맛으로 설정
        if(sharedViewModel.hasNoFlavorSet()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }

    /**
     * 프래그먼트가 파괴될 때 binding 객체도 비워주어 메모리 누수 예방
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
