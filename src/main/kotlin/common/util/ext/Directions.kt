package net.dblsaiko.hctm.common.util.ext

import net.minecraft.util.math.Direction
import net.minecraft.util.math.Direction.Axis.X
import net.minecraft.util.math.Direction.Axis.Y
import net.minecraft.util.math.Direction.Axis.Z
import net.minecraft.util.math.Direction.DOWN
import net.minecraft.util.math.Direction.EAST
import net.minecraft.util.math.Direction.NORTH
import net.minecraft.util.math.Direction.SOUTH
import net.minecraft.util.math.Direction.UP
import net.minecraft.util.math.Direction.WEST

fun Direction.rotateClockwise(axis: Direction.Axis): Direction {
    return when (axis) {
        X -> when (this) {
            DOWN -> SOUTH
            UP -> NORTH
            NORTH -> DOWN
            SOUTH -> UP
            else -> this
        }
        Y -> when (this) {
            NORTH -> EAST
            SOUTH -> WEST
            WEST -> NORTH
            EAST -> SOUTH
            else -> this
        }
        Z -> when (this) {
            DOWN -> WEST
            UP -> EAST
            WEST -> UP
            EAST -> DOWN
            else -> this
        }
    }
}

fun Direction.rotateCounterClockwise(axis: Direction.Axis): Direction {
    return when (axis) {
        X -> when (this) {
            DOWN -> NORTH
            UP -> SOUTH
            NORTH -> UP
            SOUTH -> DOWN
            else -> this
        }
        Y -> when (this) {
            NORTH -> WEST
            SOUTH -> EAST
            WEST -> SOUTH
            EAST -> NORTH
            else -> this
        }
        Z -> when (this) {
            DOWN -> EAST
            UP -> WEST
            WEST -> DOWN
            EAST -> UP
            else -> this
        }
    }
}