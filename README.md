# burning-trees

You are given a MxN grid where each item contains an integer with one of the following values:
- 2: fire
- 1: tree
- 0: empty

The grid can have any M,N dimensions between 1 and 10000.

More than one fire might be present in the starting grid.

Every minute the fire burns adjacent trees but never diagonal trees (up,down,left or right only).

Your program return the minimum number of minutes it takes for all trees in the grid to catch fire, or, if any tree remains unburnt -1

Example:
- Input: [[2,1,0],[0,1,0],[0,1,1]]
- Solution: 4

Another Example:
- Input [[2,1,1],[0,1,1],[1,0,0]]
- Solution: -1

Write a program that solves the problem.