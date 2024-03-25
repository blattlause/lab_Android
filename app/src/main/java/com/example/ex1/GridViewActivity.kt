package com.example.ex1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView

class GridViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view)

        val phoneList = intent.getSerializableExtra("phoneList") as ArrayList<Phone>
        val selectedPhones: MutableList<Phone> = mutableListOf()
        val adapter = PhoneListAdapter(this, phoneList, selectedPhones, true)
        val gridView: GridView = findViewById(R.id.gridView)

        gridView.numColumns = 2
        gridView.adapter = adapter
    }
}