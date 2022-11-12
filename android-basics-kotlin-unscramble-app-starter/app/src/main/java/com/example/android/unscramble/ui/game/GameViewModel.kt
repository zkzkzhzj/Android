package com.example.android.unscramble.ui.game

import android.text.Spannable
import android.text.SpannableString
import android.text.style.TtsSpan
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // 유저 점수, 변경되는 값이기 때문에 LiveData 대신 MutableLiveData 사용
    private val _score = MutableLiveData(0)

    // 변경하지 못하게 하기 위하여 LiveData 사용
    val score: LiveData<Int>
        get() = _score

    // 푼 문제 확인
    private val _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    // 내부에서 수정할 수 있게 private var
    // LiveData 로 지정
    private val _currentScrambledWord = MutableLiveData<String>()

    // 외부에서는 접근만 가능하도록 val
    // 유저가 Talkback 기능으로 플레이할 수 있도록 변수 생성
    val currentScrambledWord: LiveData<Spannable> = Transformations.map(_currentScrambledWord) {
        if (it == null) {
            SpannableString("")
        } else {
            val scrambledWord = it.toString()
            val spannable: Spannable = SpannableString(scrambledWord)
            spannable.setSpan(
                TtsSpan.VerbatimBuilder(scrambledWord).build(),
                0,
                scrambledWord.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            spannable
        }
    }

    // 뷰 모델 삭제
    // 해당 변수 내부에 초기화 메소드를 추가로 두어 메모리 누수 방지를 할 수 있을 듯 하다
    override fun onCleared() {
        super.onCleared()
        Log.d(GameFragment.TAG, "GameViewModel destroy")
    }

    // 게임에서 사용한 단어의 목록(사용한 단어를 저장하여 중복 단어가 나오지 않도록 관리)
    private var wordsList: MutableList<String> = mutableListOf()

    // 유저가 추측해야 할 단어(추후에 초기화)
    private lateinit var currentWord: String

    // 객체 초기화 중 작업 선언
    init {
        Log.d(GameFragment.TAG, "GameViewModel created")
        getNextWord()
    }

    // 다음 단어 함수
    private fun getNextWord() {
        // 단어 리스트중 유저가 추측할 단어를 가져옴
        currentWord = allWordsList.random()
        // 해당 단어를 섞기 위해서 CharArray 로 캐스팅
        val tempWord = currentWord.toCharArray()
        // 단어를 섞음
        tempWord.shuffle()
        // 단어를 섞었지만 같은 배열이 나오는 것을 방지하여 체크 (test -> test 나올경우)
        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        // 해당 단어가 이미 사용되었는지 확인
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            // LiveData 데이터에 접근을 하기 위하여 value 지정
            _currentScrambledWord.value = String(tempWord)
            // LiveData 이기 때문에 inc() 사용하여 1씩 증분
            _currentWordCount.value = (_currentWordCount.value)?.inc()
            wordsList.add(currentWord)
        }
    }

    // 현재 유저가 푼 문제의 수를 확인하여 새로운 문제를 풀지, 종료할지 선택하여 결과 리턴
    fun nextWord(): Boolean {
        // 풀었던 문제 수가 10보다 작다면 다음문제 출제 후 true / 아닐경우 false
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    // 정답을 맞출 경우 점수 누적
    private fun increaseScore() {
        // LiveData 이기때문에 plus() 사용하여 값을 늘림
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }

    // 유저가 정답을 맞추었는지 확인
    fun isUserWordCorrect(playerWord: String): Boolean {
        // 유저가 작성한 정답이 문제와 동일하다면 점수 증가
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }

        return false
    }

    /*
    * Re-initializes the game data to restart the game.
    */
    fun reinitializeData() {
        // LiveData 값에 참조
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }
}
