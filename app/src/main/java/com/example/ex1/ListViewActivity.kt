package com.example.ex1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast

class ListViewActivity : AppCompatActivity() {

    private val selectedPhones: MutableList<Phone> = mutableListOf()
    private lateinit var phoneList: ArrayList<Phone>
    private lateinit var adapter: PhoneListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        phoneList = intent.getSerializableExtra("phoneList") as ArrayList<Phone>
        adapter = PhoneListAdapter(this, phoneList, selectedPhones, false)

        val listView: ListView = findViewById(R.id.list_view)
        listView.adapter = adapter

        val spinner: Spinner = findViewById(R.id.spinner)
        val options = arrayOf("Выберите опцию", "Междугородняя связь", "Нет")
        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterSpinner

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedOption = options[position]
                if (selectedOption != "Выберите опцию") {

                    val filteredPhones = when (selectedOption) {
                        "Междугородняя связь" -> phoneList.filter { it.internationalCall == true}
                        "Нет" -> phoneList.filter { !it.internationalCall }
                        else -> listOf()
                    }

                    adapter = PhoneListAdapter(this@ListViewActivity, filteredPhones, selectedPhones, false)
                    listView.adapter = adapter
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val selectButton: Button = findViewById(R.id.select_button)
        selectButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("selectedPhones", ArrayList(selectedPhones))
            setResult(Activity.RESULT_OK, intent)
            Toast.makeText(this, "Selected Phones: $selectedPhones", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}