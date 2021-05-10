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


class DistributionFunctionView : View() {
    private val series: ObservableList<XYChart.Data<Number, Number>> = FXCollections.observableArrayList()

    private fun getX(
        value: Double,
        distributionFunction: Map<Double, Pair<Double, Double>>
    ) = when (value) {
        Double.NEGATIVE_INFINITY -> distributionFunction.values.first().second - 1
        Double.POSITIVE_INFINITY -> distributionFunction.values.last().first + 1
        else -> value
    }

    fun showDistributionFunction(distributionFunction: Map<Double, Pair<Double, Double>>) {
        series.clear()
        distributionFunction.forEach {
            series.add(XYChart.Data(getX(it.value.first, distributionFunction), it.key))
            series.add(XYChart.Data(getX(it.value.second, distributionFunction), it.key))
        }
    }

    override val root = vbox {
        linechart("Distribution function", NumberAxis(), NumberAxis()) {
            series("Distribution function", series)
            isLegendVisible = false
            cursor = Cursor.CROSSHAIR
            prefWidth = 450.0
        }
    }
}