package me.emilia

data class Five(
    val ordering: List<Pair<Int, Int>>,
    val updates: List<List<Int>>
) {
    // For a specific key, looks up which numbers have to occur before it
    val beforeLookup: Map<Int, List<Int>> = ordering
        .groupBy { it.second }
        .mapValues { it.value.map { i -> i.first } }

    fun calculate(): Int =
        updates.filter { isValidUpdate(it) }
            .sumOf { update ->
                update[update.size / 2]
            }

    fun isValidUpdate(update: List<Int>): Boolean {
        for ((index, page) in update.withIndex()) {
            val beforeNumbers = beforeLookup[page] ?: continue
            val numbersAfter = update.subList(index, update.size)

            // Check that no number that has to occur before this one
            // occurs after
            for (number in beforeNumbers) {
                if (numbersAfter.contains(number)) {
                    // "number" was after "page"
                    return false
                }
            }
        }

        return true
    }
}


data class FivePartTwo(
    val ordering: List<Pair<Int, Int>>,
    val updates: List<List<Int>>
) {
    private val partOne: Five = Five(ordering, updates)

    fun calculate(): Int =
        updates.filter { !isSorted(it) }
            .map { sortUpdate(it) }
            .map { sortUpdate(it) }
            .map { sortUpdate(it) }
            .map { println(it); it }
            .sumOf { it[it.size / 2] }

    private fun sortUpdate(constUpdate: List<Int>): List<Int> {
        val update = constUpdate.toMutableList()
        // Bubblesort
        for (subListIndex in update.indices) {
            for (index in subListIndex until update.size - 1) {
                if (isBigger(update[index], update[index + 1])) {
                    val temp = update[index + 1]
                    update[index + 1] = update[index]
                    update[index] = temp
                }
            }
        }

        return update
    }

    // True if left is bigger than right
    private fun isBigger(left: Int, right: Int): Boolean {
        val smallerNumbers = partOne.beforeLookup[left]
        return smallerNumbers?.contains(right)
            ?: false // False if they are equal
    }

    private fun isSorted(update: List<Int>) = partOne.isValidUpdate(update)

}


