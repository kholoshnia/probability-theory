package ru.lab.views.graphs

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.Cursor
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import tornadofx.View
import tornadofx.linechart
import tornadofx.series
import tornadofx.vbox


class FrequencyPolygonView : View() {
    private val series: ObservableList<XYChart.Data<Number, Number>> = FXCollections.observableArrayList()

    fun showStatisticalSeries(statisticalSeries: Map<Double, Double>) {
        series.clear()
        statisticalSeries.forEach {
            series.add(XYChart.Data(it.key, it.value))
        }
    }

    override val root = vbox {
        linechart("Frequency polygon", NumberAxis(), NumberAxis()) {
            series("Frequency polygon", series)
            isLegendVisible = false
            cursor = Cursor.CROSSHAIR
            prefWidth = 450.0
        }
    }
}