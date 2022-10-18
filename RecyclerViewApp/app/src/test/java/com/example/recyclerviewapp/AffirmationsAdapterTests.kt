package com.example.recyclerviewapp

import android.content.Context
import com.example.recyclerviewapp.adapter.ItemAdapter
import com.example.recyclerviewapp.model.Affirmation
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

/*
 * 로컬 단위 테스트(test), 로컬 JVM 에서 실행되는 테스트
 * Android 프레임워크 종속 항목이 없거나 있더라도 모의 구현할 수 있는 경우 사용
 */

// 현재 프로젝트는 단위 테스트에 적합하지 않다(테스트 로직이 많지 않기 때문)
class AffirmationsAdapterTests {
    // 모의 테스트
    // Context 는 현재 앱 상태에 관한 컨텍스트, 현재는 모의 인스턴스를 만들어 테스트
    private val context = mock(Context::class.java)

    // 어뎁터의 크기가 전달된 목록의 크기와 동일한지 테스트
    @Test
    fun adapter_size() {
        val data = listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.image1),
            Affirmation(R.string.affirmation2, R.drawable.image2)
        )

        val adapter = ItemAdapter(context, data)

        // 테스트 메서드(반환된 값이 위 리스트 크기와 동일 해야함)
        assertEquals("ItemAdapter is not the correct size", data.size, adapter.itemCount)
    }
}