package main

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class BurningTreesKtTest {
    @Test
    fun abc() {
        burningTrees(
            listOf(
                mutableListOf(2,1,0),
                mutableListOf(0,1,0),
                mutableListOf(0,1,1)
            )
        ).let { Assertions.assertEquals(it, 4) }
    }

    @Test
    fun xyz() {
        burningTrees(
            listOf(
                mutableListOf(2,1,1),
                mutableListOf(0,1,1),
                mutableListOf(1,0,0)
            )
        ).let { Assertions.assertEquals(it, -1) }
    }
}