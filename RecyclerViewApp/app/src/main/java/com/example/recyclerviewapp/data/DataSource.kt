package com.example.recyclerviewapp.data

import com.example.recyclerviewapp.R
import com.example.recyclerviewapp.model.Affirmation

// get data
class DataSource {
    fun loadAffirmations(): List<Affirmation>{
        return listOf(
            Affirmation(R.string.affirmation1),
            Affirmation(R.string.affirmation2),
            Affirmation(R.string.affirmation3),
            Affirmation(R.string.affirmation4),
            Affirmation(R.string.affirmation5),
            Affirmation(R.string.affirmation6),
            Affirmation(R.string.affirmation7),
            Affirmation(R.string.affirmation8),
            Affirmation(R.string.affirmation9),
            Affirmation(R.string.affirmation10)
        )
    }
}