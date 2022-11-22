/*
 * Copyright (c) 2021 The Android Open Source Project
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

package com.example.android.sports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.android.sports.databinding.FragmentSportsListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class SportsListFragment : Fragment() {

    private val sportsViewModel: SportsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSportsListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSportsListBinding.bind(view)

        // 슬라이딩 창 이벤트 추가
        val slidingPaneLayout = binding.slidingPaneLayout
        // 슬라이딩을 막기
        slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            SportsListOnBackPressedCallback(slidingPaneLayout)
        )

        // Initialize the adapter and set it to the RecyclerView.
        val adapter = SportsAdapter {
            // Update the user selected sport as the current sport in the shared viewmodel
            // This will automatically update the dual pane content
            sportsViewModel.updateCurrentSport(it)
            // Navigate to the details screen
            /*val action = SportsListFragmentDirections.actionSportsListFragmentToNewsFragment()
            this.findNavController().navigate(action)*/
            // 프래그먼트를 이동시키는 것이 아닌 우측에 있는 레이아웃을 변경
            slidingPaneLayout.openPane()
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(sportsViewModel.sportsData)
    }
}

// 뒤로 가기 버튼 구현
class SportsListOnBackPressedCallback(
    private val slidingPaneLayout: SlidingPaneLayout
) : OnBackPressedCallback(
    // 슬라이딩 창이 존재하고 단일 창이 표시되는 경우에만 동작 하도록 지정
    slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
),
    // 뒤로가기 버튼 이외의 이벤트를 수신, 콜백 활성화 비활성화
    SlidingPaneLayout.PanelSlideListener {

    // 리스너 추가
    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }

    // 뒤로가기 버튼을 눌렀을 경우 메소드
    override fun handleOnBackPressed() {
        slidingPaneLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) {
    }

    // 창이 열렸다면
    override fun onPanelOpened(panel: View) {
        // 활성화
        isEnabled = true
    }

    // 창이 닫혔다면
    override fun onPanelClosed(panel: View) {
        // 비활성화
        isEnabled = false
    }
}
