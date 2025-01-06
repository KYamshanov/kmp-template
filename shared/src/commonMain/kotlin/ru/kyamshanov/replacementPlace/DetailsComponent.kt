package ru.kyamshanov.replacementPlace

import com.arkivanov.decompose.value.Value

interface DetailsComponent {
    val model: Value<Model>

    fun onBack()

    data class Model(
        val title: String,
    )
}