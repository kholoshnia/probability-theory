package services

import model.Table
import kotlin.math.pow
import kotlin.math.sqrt

class ComputeService(private val tablesService: TablesService) {
    fun task1(
        elements: List<Double>,
        gamma: Double
    ): String {
        val mean = elements.average()
        val variance = elements.map { it.pow(2) }.average() - mean.pow(2)
        val sdk = sqrt((elements.size / (elements.size - 1)).toDouble()) * variance

        val n = (elements.size - 1).toDouble()
        val p = (gamma + 1) / 2
        val student = tablesService.getValue(Table.STUDENT, n, p)
            ?: throw IllegalArgumentException("Student not found")

        val value = student * sdk / sqrt(elements.size.toDouble())
        val left = mean - value
        val right = mean + value

        return "$left < M < $right"
    }

    fun task2(
        n: Int,
        sum: Double,
        msSum: Double,
        gamma: Double
    ): String {
        val normal = tablesService.getSum(Table.NORMAL, gamma)
            ?: throw IllegalArgumentException("Normal not found")

        val mean = sum / n
        val sdk = sqrt(msSum / n)

        val value = (normal * sdk / sqrt(n.toDouble()))
        val left = mean - value
        val right = mean + value

        return "$left < M < $right"
    }
}