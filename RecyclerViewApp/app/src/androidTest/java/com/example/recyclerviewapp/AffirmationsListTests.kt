package com.example.recyclerviewapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/*
 * 안드로이드 프레임워크에 종속성이 있는 테스트
 * 하드웨어 기기나 에뮬레이터에서 실행되는 테스트
 */
/*
 * 테스트 코드는 로직이 포함되면 안된다.
 * 로직을 테스트 하기만 해야한다.
 * UI 테스트(계측테스트[androidTest]) 인터페이스의 예상 상태만 테스트
 */
// 테스트 실행기
@RunWith(AndroidJUnit4::class)
class AffirmationsListTests {
    // 시나리오 규칙
    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    // 테스트 메소드, 스크롤 테스트
    @Test
    fun scroll_to_item() {
        // 마지막 항목으로 스크롤
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions
                .scrollTo<RecyclerView.ViewHolder>(
                    // 하위 항목의 텍스트를 감지하기 위해 hasDescendant 사용(가이드 코드에 포함되어 있지 않았음)
                    hasDescendant(withText(R.string.affirmation10))
                )
        )

        // 해당 코드가 동작하지 않아 문제를 찾아보았으나 우선적으로 휴대폰 기기의 애니메이션 설정을 비활성화 하여 해결
        // 표시되는 텍스트에 기반하여 UI 구성요소 식별(UI 구성요소가 표시된다는 어션셜 생성- null 이 아님을 전달)
        onView(withText(R.string.affirmation10))
            .check(matches(isDisplayed())
            )
    }
}