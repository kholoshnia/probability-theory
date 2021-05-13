package ru.lab.controllers

import ru.lab.views.FormView
import tornadofx.Controller


class FormController : Controller() {
    private val formView: FormView by inject()

    private fun toDouble(string: String) = string
        .replace(",", ".")
        .toDouble()

    fun getSampleList(): List<Double> = formView.sampleTextField.text
        .split(" ")
        .map { toDouble(it) }
        .toList()
}