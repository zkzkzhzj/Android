package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class DiceRoller : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_rollder)

        val rollButton: Button = findViewById<Button>(R.id.diceBtn)

        rollButton.setOnClickListener { rollDice() }

        rollDice()
    }

    private fun rollDice() {
        val dice = Dice(6)
        val firstDiceRoll = dice.roll()
        val secondDiceRoll = dice.roll()

        val firstDice: ImageView = findViewById(R.id.firstDice)
        val secondDice: ImageView = findViewById(R.id.secondDice)

        firstDice.setImageResource(getDiceImage(firstDiceRoll))
        firstDice.contentDescription = firstDiceRoll.toString()
        secondDice.setImageResource(getDiceImage(secondDiceRoll))
        secondDice.contentDescription = secondDiceRoll.toString()
    }

    private fun getDiceImage(diceNum: Int): Int{
        return when(diceNum){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}

class Dice(private val numSides: Int) {

    fun roll(): Int{
        return (1..numSides).random()
    }
}