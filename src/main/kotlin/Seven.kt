package me.emilia

enum class Operator {
    Add,
    Mult,
    Concat; // for part two

    fun apply(left: Long, right: Long) =
        when (this) {
            Add -> left + right
            Mult -> left * right
            Concat -> "$left$right".toLong()
        }
}

data class Equation(val solution: Long, val inputs: List<Long>) {
    fun canBeCorrect() = recursiveCheck(inputs.subList(1, inputs.size), inputs.first())

    // returns true if we found a solution, either through a recursive
    // call or by getting the final calculation
    // no input from inputsLeft has been used in calculation yet
    private fun recursiveCheck(inputsLeft: List<Long>, current: Long): Boolean {
        if (inputsLeft.isEmpty()) {
            return current == solution
        }

        val rightHand = inputsLeft.first()
        val rest = inputsLeft.subList(1, inputsLeft.size)
        for (operator in Operator.entries) {
            val result = operator.apply(current, rightHand)
            if (recursiveCheck(rest, result)) {
                return true
            }
        }

        return false
    }
}

data class Seven(val equations: List<Equation>) {
    fun calculate() = equations
        .filter { it.canBeCorrect() }
        .sumOf { it.solution }
}