package com.example.ex1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import com.google.android.material.textfield.TextInputEditText

import android.widget.Toast

class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE_GRID_VIEW = 1001
        const val REQUEST_CODE_LIST_VIEW =  1002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phones1 = mutableListOf<Phone>()
        phones1.add(Phone("Абонент1", "111111111", false))
        phones1.add(Phone("Абонент2", "222222222", true))
        phones1.add(Phone("Абонент3", "333333333", false))
        phones1.add(Phone("Абонент4", "444444444", true))
        phones1.add(Phone("Абонент5", "555555555", false))
        phones1.add(Phone("Абонент6", "666666666", true))

        val phones2 = mutableListOf<Phone>()
        phones2.add(Phone("Абонент7", "777777777", false))
        phones2.add(Phone("Абонент8", "888888888", true))
        phones2.add(Phone("Абонент9", "999999999", false))
        phones2.add(Phone("Абонент10", "111111111", true))
        phones2.add(Phone("Абонент11", "222222222", false))
        phones2.add(Phone("Абонент12", "333333333", true))

        val radioButton1: RadioButton = findViewById(R.id.radioButton1)
        val radioButton2: RadioButton = findViewById(R.id.radioButton2)

        val searchButton: Button = findViewById(R.id.button)

        searchButton.setOnClickListener {
            if (radioButton1.isChecked) {
                val intent = Intent(this, GridViewActivity::class.java)
                intent.putExtra("phoneList", ArrayList(phones1))
                startActivityForResult(intent, REQUEST_CODE_GRID_VIEW)
            } else if (radioButton2.isChecked) {
                val intent = Intent(this, ListViewActivity::class.java)
                intent.putExtra("phoneList", ArrayList(phones2))
                startActivityForResult(intent, REQUEST_CODE_LIST_VIEW)
            } else {
                Toast.makeText(this, "Выберите тип поиска", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_GRID_VIEW -> {
                if (resultCode == Activity.RESULT_OK) {
                    val selectedPhone = data?.getSerializableExtra("selectedPhone") as? Phone
                    val displayText = selectedPhone?.run {
                        "Subscriber: $subscriber, Number: $number, International Call: $internationalCall"
                    } ?: ""
                    findViewById<TextInputEditText>(R.id.text_input).setText(displayText)
                }
            }
            REQUEST_CODE_LIST_VIEW -> {
                if (resultCode == Activity.RESULT_OK) {
                    val selectedPhones = data?.getSerializableExtra("selectedPhones") as? ArrayList<Phone>
                    val displayText = selectedPhones?.joinToString("\n") { phone ->
                        "Subscriber: ${phone.subscriber}, Number: ${phone.number}, International Call: ${phone.internationalCall}"
                    } ?: ""
                    findViewById<TextInputEditText>(R.id.text_input).setText(displayText)
                } else {
                    Toast.makeText(this, "No phones selected", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                Toast.makeText(this, "Result not processed correctly", Toast.LENGTH_SHORT).show()
            }
        }
    }
}