package org.atoyr.gra

import java.awt.image.BufferedImage
import java.awt.Image as image

internal fun RescaleAlgorithms.toHint(): Int = when (this) {
    RescaleAlgorithms.DEFAULT -> image.SCALE_DEFAULT
    RescaleAlgorithms.FAST -> image.SCALE_FAST
    RescaleAlgorithms.SMOOTH -> image.SCALE_SMOOTH
    RescaleAlgorithms.REPLICATE -> image.SCALE_REPLICATE
    RescaleAlgorithms.AREA_AVERAGE -> image.SCALE_AREA_AVERAGING
}

internal fun typeToId(type: ImageType): Int = when (type) {
    ImageType.RGB24 -> BufferedImage.TYPE_INT_RGB
    ImageType.RGBA32 -> BufferedImage.TYPE_INT_ARGB
    ImageType.GRAY8 -> BufferedImage.TYPE_BYTE_GRAY
    else -> -1
}

internal fun idToType(id: Int): ImageType = when (id) {
    else -> TODO()
}