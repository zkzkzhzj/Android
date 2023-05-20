package com.example.wordsapp

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NavigationTests {
    lateinit var navController: TestNavHostController
    lateinit var letterListScenario: FragmentScenario<LetterListFragment>

    /*
    @Before
    계측 테스트(androidTest)와 단위 테스트(test)에서 중복되는
    코드를 반복하지 않고 모든 테스트에 동일한 구성을 설정할 수 있는 기능
    해당 메서드는 클래스 내부에서 작성하는 모든 테스트에 대해 실행 된다.
    반대로 모든 테스트 이후에 반드시 실행되어야 하는 코드가 있다면 @After 주석을 사용
    단, 모든 Test 코드와 함께 Before After 코드가 매번 실행된다.

    단 한번만 실행되는 코드를 작성하고 싶다면 JUnit 에서는 BeforeClass, AfterClass 주석을 지원하며
    companion object 내부에 선언해주면 되겠다(단 한번 실행)
    */
    @Before
    fun setUp() {
        // Navigation 테스트를 위한 API 제공하는 NavHostController 하위 클래스
        val navController = TestNavHostController(
            // 테스트 중인 애플리케이션의 Context 반환
            ApplicationProvider.getApplicationContext()
        )

        // 테스트기기에서 해당 프래그먼트를 실행, 사용중인 테마 세팅
        letterListScenario = launchFragmentInContainer<LetterListFragment>(themeResId = R.style.Theme_Words)

        // 실행된 프래그먼트에 사용할 네비게이션 선언
        letterListScenario.onFragment { fragment ->
            // navController 에 그렸던 navigation 세팅 해주고
            navController.setGraph(R.navigation.nav_graph)
            // 위 프래그먼트의 뷰와 네비게이션 컨트롤러를 연결시켜줌
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    // 테스트 메소드 생성
    @Test
    fun navigate_to_words_nav_component() {
        // 상단에 세팅한 네비게이션 프래그먼트 이벤트를 트리거 시킴
        onView(withId(R.id.recycler_view))
                // 현재 선택한 뷰에 대한 작업 실행(recycler_view)
            .perform(RecyclerViewActions
                    // 설정한 position 에 대한 viewAction 수행
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        // 두 객체가 같은지 확인하는 메소드, navController destination id 값과 wordListFragment id 값이 같은지 체크
        // 다르다면 AssertionError 발생
        assertEquals(navController.currentDestination?.id, R.id.wordListFragment)
    }
}