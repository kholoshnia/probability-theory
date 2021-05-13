package ru.lab.controllers

import ru.lab.views.ResultsView
import ru.lab.views.graphs.DistributionFunctionView
import ru.lab.views.graphs.FrequencyBarView
import ru.lab.views.graphs.FrequencyPolygonView
import tornadofx.Controller
import java.util.*
import kotlin.math.log2
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt


class ComputeController : Controller() {
    private val formController: FormController by inject()
    private val resultsView: ResultsView by inject()

    private val distributionFunctionView: DistributionFunctionView by inject()
    private val frequencyBarView: FrequencyBarView by inject()
    private val frequencyPolygonView: FrequencyPolygonView by inject()

    private fun getStatisticalSeries(sampleList: List<Double>): Map<Double, Double> {
        val frequencyMap = HashMap<Double, Double>()
        sampleList.forEach {
            val probability = Collections.frequency(sampleList, it).toDouble() / sampleList.size
            frequencyMap[it] = probability
        }
        return frequencyMap.toSortedMap()
    }

    private fun getMathExpectation(statisticalSeries: Map<Double, Double>, powValue: Int): Double {
        return statisticalSeries
            .map { it.key.pow(powValue) * it.value }
            .sum()
    }

    private fun getVariance(statisticalSeries: Map<Double, Double>): Double {
        return getMathExpectation(statisticalSeries, 2) -
                getMathExpectation(statisticalSeries, 1).pow(2.0)
    }

    private fun getDistributionFunction(statisticalSeries: Map<Double, Double>): Map<Double, Pair<Double, Double>> {
        val distributionFunction = HashMap<Double, Pair<Double, Double>>()
        var probability = 0.0

        var from = Double.NEGATIVE_INFINITY
        var to = statisticalSeries.keys.first()
        distributionFunction[probability] = Pair(from, to)

        statisticalSeries.forEach { (n, p) ->
            if (n == statisticalSeries.keys.first()) {
                return@forEach
            }

            from = to
            to = n
            probability += p
            distributionFunction[probability] = Pair(from, to)
        }

        from = statisticalSeries.keys.last()
        to = Double.POSITIVE_INFINITY
        probability = 1.0
        distributionFunction[probability] = Pair(from, to)

        return distributionFunction.toSortedMap()
    }

    private fun getIntervalSeries(
        sampleList: List<Double>,
        min: Double,
        max: Double
    ): Map<Pair<Double, Double>, Int> {
        val n = (1 + log2(sampleList.size.toDouble())).roundToInt()
        val h = (max - min) / n
        var start = min - h / 2
        val intervalSeries = HashMap<Pair<Double, Double>, Int>()

        for (i in 0..n) {
            var number = 0
            sampleList.forEach {
                if (start <= it && it < start + h) {
                    number++
                }
            }

            intervalSeries[Pair(start, start + h)] = number
            start += h
        }

        return intervalSeries
    }

    fun compute() {
        val sampleList = formController.getSampleList()

        val min = sampleList.minOrNull() ?: throw IllegalArgumentException("Cannot find min")
        val max = sampleList.maxOrNull() ?: throw IllegalArgumentException("Cannot find max")
        val scope = max.minus(min)
        val statisticalSeries = getStatisticalSeries(sampleList)
        val mathExpectation = getMathExpectation(statisticalSeries, 1)
        val variance = getVariance(statisticalSeries)
        val msd = sqrt(variance)
        val distributionFunction = getDistributionFunction(statisticalSeries)
        val intervalSeries = getIntervalSeries(sampleList, min, max)

        distributionFunctionView.showDistributionFunction(distributionFunction)
        frequencyBarView.showIntervalSeries(intervalSeries)
        frequencyPolygonView.showStatisticalSeries(statisticalSeries)

        val statisticalSeriesString = statisticalSeries.entries.joinToString(" ") {
            "${it.key}: ${it.value}"
        }

        val distributionFunctionString = distributionFunction.entries.joinToString(" ") {
            "${String.format("%.3f", it.key)}: ${it.value.first} < x <= ${it.value.second}"
        }

        val intervalSeriesString = intervalSeries.entries.joinToString(" ") {
            "${it.value}: ${String.format("%.3f", it.key.first)} < x <= ${String.format("%.3f", it.key.second)}"
        }

        resultsView.minValueValueLabel.text = min.toString()
        resultsView.maxValueValueLabel.text = max.toString()
        resultsView.scopeValueLabel.text = scope.toString()
        resultsView.mathExpectationValueLabel.text = mathExpectation.toString()
        resultsView.varianceValueLabel.text = variance.toString()
        resultsView.msdValueLabel.text = msd.toString()
        resultsView.statisticalSeriesValueLabel.text = statisticalSeriesString
        resultsView.intervalSeriesValueLabel.text = intervalSeriesString
        resultsView.distributionFunctionValueLabel.text = distributionFunctionString

        println("${ResultsView.MIN_VALUE}$min")
        println("${ResultsView.MAX_VALUE}$max")
        println("${ResultsView.SCOPE}$scope")
        println("${ResultsView.MATH_EXPECTATION}$mathExpectation")
        println("${ResultsView.VARIANCE}$variance")
        println("${ResultsView.MSD}$msd")
        println("${ResultsView.STATISTICAL_SERIES}$statisticalSeriesString")
        println("${ResultsView.INTERVAL_SERIES}$intervalSeriesString")
        println("${ResultsView.DISTRIBUTION_FUNCTION}$distributionFunctionString")
    }
}
