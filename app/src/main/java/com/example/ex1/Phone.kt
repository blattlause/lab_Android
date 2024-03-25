package com.example.ex1

import java.io.Serializable

class Phone(var subscriber: String, var number: String, var internationalCall: Boolean):
    Serializable {
    override fun toString(): String {
        return "Абонент: '$subscriber', номер: '$number', меджугородние звонки: $internationalCall)"
    }
}