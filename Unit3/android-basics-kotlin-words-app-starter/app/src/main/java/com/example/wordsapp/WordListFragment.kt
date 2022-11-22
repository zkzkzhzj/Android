package com.example.wordsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentWordListBinding

class WordListFragment : Fragment() {
    // binding 객체 생성
    private var _binding: FragmentWordListBinding? = null
    private val binding get() = _binding!!
    // recycler 객체 생성
    private lateinit var recyclerView: RecyclerView

    // letterId 넘겨받은 텍스트
    private lateinit var letterId: String

    companion object {
        const val  LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    // 프래그먼트 생성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // arguments 가 null 이 아닐경우 실행
        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }

    // 뷰 생성
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWordListBinding.inflate(inflater, container, false)

        return binding.root
    }

    // 뷰 사용
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 리사이클러 세팅
        recyclerView = binding.recyclerView
        // 레이아웃 설정
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // 리사이클러 어뎁더 세팅 - WordAdapter 에서 Context 를 null 아닌 객체로 받기 때문에 requireContext 사용
        // activity?.intent?.extras?.getString(LETTER).toString() 사용하여 intent 값 받아올 수 있지만 권장사항 X
        recyclerView.adapter = WordAdapter(letterId, requireContext())

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    // 뷰가 삭제되었을 때
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}