package com.example.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.adapter.ItemAdapter
import com.example.recyclerviewapp.data.DataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // data setting
        val myDataset = DataSource().loadAffirmations()

        // set recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        // this context, data
        recyclerView.adapter = ItemAdapter(this, myDataset)
        // not change layout size setting(performance)
        recyclerView.setHasFixedSize(true)
    }
}