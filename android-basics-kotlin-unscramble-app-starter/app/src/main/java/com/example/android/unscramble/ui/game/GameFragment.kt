/*
 * Copyright (C) 2020 The Android Open Source Project
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

package com.example.android.unscramble.ui.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.unscramble.R
import com.example.android.unscramble.databinding.GameFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Fragment where the game is played, contains the game logic.
 */
class GameFragment : Fragment() {
    companion object {
        const val TAG = "GameProject"
    }

    /*
    뷰 모델을 생성한 뒤 by viewModels() 속성을 위임
    by - get / set 책임을 다른 클래스에 위임할 수 있다
    만일 GameViewModel() 로 초기화할 경우에는 기기에서 회전이 일어나는 액티비티의 소멸이 진행될 경우
    다시 생성되면서 새로운 뷰 모델 인스턴스가 생성된다
    그래서 객체의 초기화를 viewModels 라는 별도의 클래스에 위임하여 유지할 수 있도록 한다.
     */
    private val viewModel: GameViewModel by viewModels()

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        /*
        ViewBinding -> GameFragmentBinding 이지만 DataBinding 으로 읺여 변경
         */
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)
        Log.d(TAG, "GameFragment created/re-created")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup a click listener for the Submit and Skip buttons.
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }

        // 데이터 바인딩 객체로 넘겨줌
        binding.gameViewModel = viewModel
        binding.maxNoOfWords = MAX_NO_OF_WORDS
        // LiveData 는 라이프사이클 관찰을 할 수 있어 넘겨줌
        binding.lifecycleOwner = viewLifecycleOwner

        /*
        LiveData 관찰
        viewLifecycleOwner - 라이프사이클을 넘겨주어 STARTED, RESUME 상태일 때만 알릴수 있게 함
        현재 풀어야할 문제 보여줌
         ***** 현재 DataBinding 사용하여 xml 내부에서 데이터 컨트롤 진행 중
         */
        /*viewModel.currentScrambledWord.observe(
            viewLifecycleOwner
        ) { newWord ->
            binding.textViewUnscrambledWord.text = newWord
        }*/
        // 현재 점수를 보여줌
        /*viewModel.score.observe(
            viewLifecycleOwner
        ) { newScore ->
            binding.score.text = getString(R.string.score, newScore)
        }*/
        // 풀었던 문제수를 보여줌
        /*viewModel.score.observe(
            viewLifecycleOwner
        ) { newWordCount ->
            binding.wordCount.text = getString(R.string.word_count, newWordCount, MAX_NO_OF_WORDS)
        }*/
    }

    // 마지막 단어까지 끝내면 Dialog 띄어줌
    private fun showFinalScoreDialog() {
        // 머터리얼 다이얼로그
        MaterialAlertDialogBuilder(requireContext())
            // 제목
            .setTitle(getString(R.string.congratulations))
            // 내용
            .setMessage(getString(R.string.you_scored, viewModel.score.value))
            // 뒤로키를 눌러서 취소할 수 없도록 만듬
            .setCancelable(false)
            // EXIT 버튼, 동작 정의
            .setNegativeButton(getString(R.string.exit)) { _, _ ->
                exitGame()
            }
            // PLAY AGAIN 버튼, 동작 정의
            .setPositiveButton(getString(R.string.play_again)) { _, _ ->
                restartGame()
            }
            // 보여주기
            .show()
    }

    // Submit 클릭
    private fun onSubmitWord() {
        // 유저가 작성한 단어를 가져옴
        val playerWord = binding.textInputEditText.text.toString()

        // 유저가 작성한 단어를 비교
        if (viewModel.isUserWordCorrect(playerWord)) {
            // 단어가 옳바르다면 경고 보내지 않음
            setErrorTextField(false)
            // 풀었던 문제 수 체크
            if(!viewModel.nextWord()) {
                // 종료 다이얼로그 보여줌
                showFinalScoreDialog()
            }
        } else {
            // 단어가 틀리다면 경고 보냄
            setErrorTextField(true)
        }
    }

    /*
    * 단어 스킵
    */
    private fun onSkipWord() {
        // 풀었던 문제수 체크
        if (viewModel.nextWord()) {
            // 넘지 않았다면 경고메시지 보여주지 않음(nextWord 내부에서 문제 새로 출시)
            setErrorTextField(false)
        } else {
            // 종료 다이얼로그 보여줌
            showFinalScoreDialog()
        }
    }

    /*
    * 다시시작
    */
    private fun restartGame() {
        // 데이터 초기화 메소드 호출
        viewModel.reinitializeData()
        // 에러메시지 초기화
        setErrorTextField(false)
    }

    /*
     * 게임 종료
     */
    private fun exitGame() {
        // 액티비티 종료
        activity?.finish()
    }

    /*
    * 틀렸다면 에러 메시지 보여주기 위한 메소드
    */
    private fun setErrorTextField(error: Boolean) {
        // 넘겨받은 boolean 값을 확인
        if (error) {
            // true 를 받았다면 레이아웃 오류 기능 활성화
            binding.textField.isErrorEnabled = true
            // 에러 메시지 출력
            binding.textField.error = getString(R.string.try_again)
        } else {
            // false 를 받았다면 레이아웃 오류기능 비활성화
            binding.textField.isErrorEnabled = false
            // 에러 메시지 비움
            binding.textInputEditText.text = null
        }
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "GameFragment destroy")
    }
}
