package ru.lab.views

import javafx.scene.control.Alert
import javafx.scene.control.TextField
import ru.lab.controllers.ComputeController
import tornadofx.View
import tornadofx.action
import tornadofx.alert
import tornadofx.button
import tornadofx.field
import tornadofx.fieldset
import tornadofx.form
import tornadofx.singleAssign
import tornadofx.textfield


class FormView : View() {
    companion object {
        private const val SAMPLE = "-1.73 -0.73 1.66 -0.80 " +
                "0.62 1.52 1.63 1.04 0.42 -1.21 0.90 -1.00 " +
                "0.24 0.62 0.55 -1.45 -1.45 -0.52 0.17 -1.31"
    }

    private val computeController: ComputeController by inject()

    var sampleTextField: TextField by singleAssign()

    override val root = form {
        fieldset("Sample") {
            field {
                sampleTextField = textfield(SAMPLE)
            }

            field {
                button("Compute") {
                    action {
                        try {
                            computeController.compute()
                        } catch (e: Exception) {
                            alert(Alert.AlertType.WARNING, "Compute error", e.message)
                        }
                    }
                }
            }
        }
    }
}