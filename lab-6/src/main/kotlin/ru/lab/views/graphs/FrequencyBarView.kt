package ru.lab.views.graphs

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import tornadofx.View
import tornadofx.barchart
import tornadofx.series
import tornadofx.vbox


class FrequencyBarView : View() {
    private val series: ObservableList<XYChart.Data<String, Number>> = FXCollections.observableArrayList()

    fun showStatisticalSeries(statisticalSeries: Map<Double, Double>, size: Int) {
        series.clear()
        statisticalSeries.forEach {
            series.add(XYChart.Data(it.key.toString(), it.value * size))
        }
    }

    override val root = vbox {
        barchart("Frequency bar", CategoryAxis(), NumberAxis()) {
            series("Frequency bar", series)
            isLegendVisible = false
            prefWidth = 450.0
        }
    }
}