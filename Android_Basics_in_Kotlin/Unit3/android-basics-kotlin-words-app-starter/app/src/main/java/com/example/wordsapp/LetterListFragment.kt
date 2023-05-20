package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding


class LetterListFragment : Fragment() {
    // 레이아웃 확장은 onCreateView 에서 가능하기 때문에 null 로 생성해준다
    // 언더바를 붙이는 이유는 null 허용 객체이기 때문에 언더바를 붙이는 것 같다
    private var _binding: FragmentLetterListBinding? = null
    // null 이 아닌 안전한 binding 객체를 세팅
    private val binding get() = _binding!!
    // 리사이클러 객체, 추후에 초기화 하겠다고 선언
    private lateinit var recyclerView: RecyclerView

    // 왼쪽 상단의 메뉴바 상태
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 옵션 메뉴 표시
        setHasOptionsMenu(true)
    }

    // 뷰 생성
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // 뷰가 생성되어있어 레이아웃을 확장시키고 리턴한다
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 실제 뷰들을 매칭시켜준다
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    // 뷰가 삭제되었을 때
    override fun onDestroyView() {
        super.onDestroyView()
        // binding 객체를 비워준다
        _binding = null
    }

    // 액티비티와의 차이점으로 프래그먼트에는 menuInflater 속성이 없으며 리턴문 또한 없다
    // 대신 매개변수로 전달된다
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // 전달된 inflater 속성에 추가
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    // 아이템 선택했을 경우 동작
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 선택한 메뉴 id를 가져와서 확인
        return when (item.itemId) {
            // 우리가 정의한 menu id를 선택했다면
            R.id.action_switch_layout -> {
                // 현재 boolean 값을 반대로 바꾸고(linear -> grid OR grid -> linear)
                isLinearLayoutManager = !isLinearLayoutManager
                // 레이아웃 다시 세팅해주고
                chooseLayout()
                // 아이콘도 다시 세팅
                setIcon(item)

                // 우리가 직접 컨트롤하기 때문에 true 값 리턴
                return true
            }

            // 그 외적인 부분은 부모에게
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun chooseLayout() {
        when (isLinearLayoutManager) {
            true -> {
                // 액티비티와 다르게 상위 context 를 가져온다(부모 액티비티 context)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = LetterAdapter()
            }
            false -> {
                recyclerView.layoutManager = GridLayoutManager(context, 4)
                recyclerView.adapter = LetterAdapter()
            }
        }
    }

    // 메뉴 아이콘 세팅
    private fun setIcon(menuItem: MenuItem?) {
        // 만일 전달받은 값이 비어있다면
        if(menuItem == null)
            // 종료
            return

        menuItem.icon =
            if (isLinearLayoutManager) {
                // context - null 일 수 있음 / requireContext - null 일경우 exception 발생
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_grid_layout)
            } else {
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_linear_layout)
            }
    }
}