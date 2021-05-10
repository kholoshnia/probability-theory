package ru.lab.views

import javafx.scene.control.Label
import tornadofx.View
import tornadofx.hbox
import tornadofx.label
import tornadofx.paddingAll
import tornadofx.paddingRight
import tornadofx.singleAssign
import tornadofx.vbox


class ResultsView : View() {
    companion object {
        const val MIN_VALUE = "Min value: "
        const val MAX_VALUE = "Max value: "
        const val SCOPE = "Scope: "
        const val MATH_EXPECTATION = "Math expectation: "
        const val VARIANCE = "Variance: "
        const val MSD = "MSD: "
        const val STATISTICAL_SERIES = "Statistic series: "
        const val DISTRIBUTION_FUNCTION = "Distribution function: "
        const val INTERVAL_SERIES = "Interval series: "
    }

    private var minValueLabel: Label by singleAssign()
    private var maxValueLabel: Label by singleAssign()
    private var scopeLabel: Label by singleAssign()
    private var mathExpectationLabel: Label by singleAssign()
    private var varianceLabel: Label by singleAssign()
    private var msdLabel: Label by singleAssign()
    private var statisticalSeriesLabel: Label by singleAssign()
    private var distributionFunctionLabel: Label by singleAssign()
    private var intervalSeriesLabel: Label by singleAssign()

    var minValueValueLabel: Label by singleAssign()
    var maxValueValueLabel: Label by singleAssign()
    var scopeValueLabel: Label by singleAssign()
    var mathExpectationValueLabel: Label by singleAssign()
    var varianceValueLabel: Label by singleAssign()
    var msdValueLabel: Label by singleAssign()
    var statisticalSeriesValueLabel: Label by singleAssign()
    var distributionFunctionValueLabel: Label by singleAssign()
    var intervalSeriesValueLabel: Label by singleAssign()

    override val root = vbox(20) {
        paddingAll = 10

        hbox {
            minValueLabel = label(MIN_VALUE)
            minValueValueLabel = label {
                paddingRight = 20.0
            }

            maxValueLabel = label(MAX_VALUE)
            maxValueValueLabel = label {
                paddingRight = 20.0
            }

            scopeLabel = label(SCOPE)
            scopeValueLabel = label {
                paddingRight = 20.0
            }

            mathExpectationLabel = label(MATH_EXPECTATION)
            mathExpectationValueLabel = label {
                paddingRight = 20.0
            }

            varianceLabel = label(VARIANCE)
            varianceValueLabel = label {
                paddingRight = 20.0
            }

            msdLabel = label(MSD)
            msdValueLabel = label()
        }

        hbox {
            statisticalSeriesLabel = label(STATISTICAL_SERIES)
            statisticalSeriesValueLabel = label()
        }

        hbox {
            distributionFunctionLabel = label(DISTRIBUTION_FUNCTION)
            distributionFunctionValueLabel = label()
        }

        hbox {
            intervalSeriesLabel = label(INTERVAL_SERIES)
            intervalSeriesValueLabel = label()
        }
    }
}