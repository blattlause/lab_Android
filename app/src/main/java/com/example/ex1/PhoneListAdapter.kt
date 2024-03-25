package com.example.ex1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.TextView

class PhoneListAdapter(context: Context, private val phones: List<Phone>, private val selectedPhones: MutableList<Phone>, private val isGridViewActivity: Boolean) :
    ArrayAdapter<Phone>(context, R.layout.phone, phones) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.phone, parent, false)
        }

        val phone = phones[position]

        val subscriberTextView: TextView = view!!.findViewById(R.id.subscriber)
        val numberTextView: TextView = view.findViewById(R.id.number)
        val internationalCallTextView: TextView = view.findViewById(R.id.internationalCall)

        val radioButton: RadioButton = view.findViewById(R.id.radio_button)

        subscriberTextView.text = "Subscriber: ${phone.subscriber}"
        numberTextView.text = "Number: ${phone.number}"
        internationalCallTextView.text = "International Call: ${phone.internationalCall}"

        if (isGridViewActivity) {
            radioButton.visibility = View.GONE
        } else {
            radioButton.visibility = View.VISIBLE
        }

        view.setOnClickListener {
            val intent = Intent().apply {
                putExtra("selectedPhone", phone)
            }
            (context as Activity).setResult(Activity.RESULT_OK, intent)
            (context as Activity).finish()
        }

        radioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (!selectedPhones.contains(phone)) {
                    selectedPhones.add(phone)
                }
            } else {
                selectedPhones.remove(phone)
            }
        }

        return view
    }
}