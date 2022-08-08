package com.example.diceroller

import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun generates_number(){
        val dice = Dice(6)
        val rollResult = dice.roll()

        assertTrue("값은 항상 1과 6사이로 나와야한다", rollResult in 1..6)
    }
}