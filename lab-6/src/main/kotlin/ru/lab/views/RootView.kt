package ru.lab.views

import ru.lab.views.graphs.DistributionFunctionView
import ru.lab.views.graphs.FrequencyBarView
import ru.lab.views.graphs.FrequencyPolygonView
import tornadofx.View
import tornadofx.hbox
import tornadofx.vbox


class RootView : View() {
    private val distributionFunctionView: DistributionFunctionView by inject()
    private val frequencyBarView: FrequencyBarView by inject()
    private val frequencyPolygonView: FrequencyPolygonView by inject()

    private val formView: FormView by inject()
    private val resultsView: ResultsView by inject()

    init {
        title = "Lab 6"
    }

    override val root = vbox {
        add(formView)

        hbox {
            add(distributionFunctionView)
            add(frequencyBarView)
            add(frequencyPolygonView)
        }

        add(resultsView)
    }
}