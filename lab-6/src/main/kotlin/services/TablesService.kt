package services

import model.Table
import kotlin.math.roundToInt

class TablesService {
    private fun getColumn(
        tableList: List<List<String>>,
        x: Double
    ): Int {
        var columnIndex = -1
        tableList.first().forEachIndexed { i, el ->
            if (i != 0 && el.toDouble() == x) {
                columnIndex = i
            }
        }
        return columnIndex
    }

    private fun getRow(
        table: Table,
        tableList: List<List<String>>,
        y: Double
    ): Int {
        var rowIndex = -1

        if (table == Table.STUDENT && y > tableList[tableList.size - 2].first().toDouble()) {
            rowIndex = tableList.size - 2
        } else {
            tableList.forEachIndexed { i, el ->
                if (i != 0 && i != tableList.size - 1 && el.first().toDouble() == y) {
                    rowIndex = i
                }
            }
        }
        return rowIndex
    }

    private fun getAverage(
        table: Table,
        tableList: List<List<String>>,
        rowIndex: Int,
        x: Double,
        y: Double
    ): Double {
        var columnIndex = -1
        val header = tableList.first()
        for (i in 0..header.size - 2) {
            if (i != 0 && header[i].toDouble() > x && x < header[i + 1].toDouble()) {
                columnIndex = i
            }
        }

        if (columnIndex == -1) {
            throw IllegalArgumentException("Row not found: table: ${table.name}, n: $y, p: $x")
        }

        return (tableList[rowIndex][columnIndex].toDouble()
                + tableList[rowIndex][columnIndex + 1].toDouble()) / 2
    }

    fun getValue(table: Table, y: Double, x: Double): Double? {
        val text = this::class.java.getResource(table.path)?.readText()

        if (text != null) {
            val tableList = text
                .replace(",", ".")
                .split("\n")
                .map { it.split(" ") }

            val columnIndex = getColumn(tableList, x)
            val rowIndex = getRow(table, tableList, y)

            if (rowIndex == -1) {
                return getAverage(table, tableList, rowIndex, x, y)
            }

            if (columnIndex == -1) {
                throw IllegalArgumentException("Column not found: table: ${table.name}, n: $y, p: $x")
            }

            return tableList[rowIndex][columnIndex].toDouble()
        }

        return null
    }

    fun getSum(table: Table, x: Double): Double? {
        val text = this::class.java.getResource(table.path)?.readText()

        if (text != null) {
            val tableList = text
                .replace(",", ".")
                .split("\n")
                .map { it.split(" ") }

            val map = HashMap<Double, Double>()
            tableList.forEachIndexed { i, row ->
                row.forEachIndexed { j, el ->
                    if (i != 0 && j != 0) {
                        map[tableList.first()[j].toDouble() + row.first().toDouble()] = el.toDouble()
                    }
                }
            }

            val roundedX = (x / 0.0001).roundToInt() * 0.0001
            val value = map[roundedX]
            if (value != null) return value

            var result = 0.0
            map.keys.forEach {
                if (it > x && x < it) {
                    result = map[it]!!
                }
            }

            return result
        }

        return null
    }
}