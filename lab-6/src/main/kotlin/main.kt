import services.ComputeService
import services.TablesService


fun task1(computeService: ComputeService) {
    val elements = listOf(174.0, 164.0, 182.0, 169.0, 171.0, 187.0, 176.0, 177.0, 168.0, 171.0, 180.0, 175.0)
    val gamma = 0.9

    val result = computeService.task1(elements, gamma)
    println(result)
}

fun task2(computeService: ComputeService) {
    val n = 64
    val sum = 1008.0
    val msSum = 172.8
    val gamma = 0.9

    val result = computeService.task2(n, sum, msSum, gamma)
    println(result)
}

fun main() {
    val tablesService = TablesService()
    val computeService = ComputeService(tablesService)

    task1(computeService)
    task2(computeService)
}