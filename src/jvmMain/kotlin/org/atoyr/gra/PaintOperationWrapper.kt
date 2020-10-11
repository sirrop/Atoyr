package org.atoyr.gra

import java.awt.Shape
import java.awt.image.BufferedImageOp

interface PaintOperationWrapper {
    val shape: Shape?
    val op: BufferedImageOp?

    fun getOperation(): Any
    fun operationType(): OperationType

    enum class OperationType {
        DRAW, FILTER
    }
}

fun wrap(shape: Shape): PaintOperationWrapper = object: PaintOperationWrapper {
    override val shape: Shape?
        get() = shape

    override val op: BufferedImageOp?
        get() = null

    override fun getOperation(): Any = shape
    override fun operationType(): PaintOperationWrapper.OperationType {
        return PaintOperationWrapper.OperationType.DRAW
    }
}

fun wrap(filter: BufferedImageOp): PaintOperationWrapper = object: PaintOperationWrapper {
    override val shape: Shape?
        get() = null

    override val op: BufferedImageOp?
        get() = filter

    override fun getOperation(): Any = filter
    override fun operationType(): PaintOperationWrapper.OperationType {
        return PaintOperationWrapper.OperationType.FILTER
    }
}
