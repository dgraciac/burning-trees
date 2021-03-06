package main

fun burningTrees(grid: List<MutableList<Int>>): Int {
    var burnedTreesDuringPreviousIteration: MutableList<Pair<Int, Int>>
    var initialTrees: MutableList<Pair<Int, Int>>

    initializeTreesAndFire(grid).let {
        burnedTreesDuringPreviousIteration = it.first
        initialTrees = it.second
    }

    var iteration = 0
    while (burnedTreesDuringPreviousIteration.isNotEmpty()) {
        val burnedTreesDuringThisIteration: MutableList<Pair<Int, Int>> = mutableListOf()

        burnedTreesDuringPreviousIteration.forEach { burnedTree: Pair<Int, Int> ->
            propagateUp(burnedTree, grid, burnedTreesDuringThisIteration)
            propagateDown(burnedTree, grid, burnedTreesDuringThisIteration)
            propagateLeft(burnedTree, grid, burnedTreesDuringThisIteration)
            propagateRight(burnedTree, grid, burnedTreesDuringThisIteration)
        }

        if (burnedTreesDuringThisIteration.isNotEmpty()) iteration++
        burnedTreesDuringPreviousIteration = burnedTreesDuringThisIteration
    }

    return if(initialTrees.all { it.isBurned(grid) }) iteration
    else -1
}

private fun Pair<Int, Int>.isBurned(
    grid: List<MutableList<Int>>
) = grid[this.first][this.second] == 2

private fun propagateRight(
    burnedTree: Pair<Int, Int>,
    grid: List<MutableList<Int>>,
    burnedTreesDuringThisIteration: MutableList<Pair<Int, Int>>
) {
    if (burnedTree.second < grid[0].size - 1 && grid[burnedTree.first][burnedTree.second + 1] == 1) {
        grid[burnedTree.first][burnedTree.second + 1] = 2
        burnedTreesDuringThisIteration.add(Pair(burnedTree.first, burnedTree.second + 1))
    }
}

private fun propagateLeft(
    burnedTree: Pair<Int, Int>,
    grid: List<MutableList<Int>>,
    burnedTreesDuringThisIteration: MutableList<Pair<Int, Int>>
) {
    if (burnedTree.second > 0 && grid[burnedTree.first][burnedTree.second - 1] == 1) {
        grid[burnedTree.first][burnedTree.second - 1] = 2
        burnedTreesDuringThisIteration.add(Pair(burnedTree.first, burnedTree.second - 1))
    }
}

private fun propagateDown(
    burnedTree: Pair<Int, Int>,
    grid: List<MutableList<Int>>,
    burnedTreesDuringThisIteration: MutableList<Pair<Int, Int>>
) {
    if (burnedTree.first < grid.size - 1 && grid[burnedTree.first + 1][burnedTree.second] == 1) {
        grid[burnedTree.first + 1][burnedTree.second] = 2
        burnedTreesDuringThisIteration.add(Pair(burnedTree.first + 1, burnedTree.second))
    }
}

private fun propagateUp(
    burnedTree: Pair<Int, Int>,
    grid: List<MutableList<Int>>,
    burnedTreesDuringThisIteration: MutableList<Pair<Int, Int>>
) {
    if (burnedTree.first > 0 && grid[burnedTree.first - 1][burnedTree.second] == 1) {
        grid[burnedTree.first - 1][burnedTree.second] = 2
        burnedTreesDuringThisIteration.add(Pair(burnedTree.first - 1, burnedTree.second))
    }
}

private fun initializeTreesAndFire(grid: List<List<Int>>): Pair<MutableList<Pair<Int, Int>>, MutableList<Pair<Int, Int>>> {
    val fire: MutableList<Pair<Int, Int>> = mutableListOf()
    val trees: MutableList<Pair<Int, Int>> = mutableListOf()
    grid.forEachIndexed { i: Int, row: List<Int> ->
        row.forEachIndexed { j: Int, square: Int ->
            if (square == 2) fire.add(Pair(i, j)) else if (square == 1) trees.add(Pair(i, j))
        }
    }
    return Pair(fire, trees)
}