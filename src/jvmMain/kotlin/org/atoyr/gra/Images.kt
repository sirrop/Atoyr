package org.atoyr.gra

import java.awt.Graphics2D
import java.awt.Shape
import java.awt.geom.AffineTransform
import java.awt.geom.Area
import java.awt.image.BufferedImage
import java.util.Objects

private object EmptyImage: Image {
    override val width: Int
        get() = 0
    override val height: Int
        get() = 0
    override val type: ImageType
        get() = TODO("Not yet implemented")

    override fun dispose() {
    }

    override fun draw(draw: Drawer.() -> Unit) {
    }

    override fun process(process: ImageProcessor.() -> Unit) {
    }

    override fun scaledImage(scale: Double, rescaleAlgorithm: RescaleAlgorithms): Image {
        return this
    }

    override fun hashCode(): Int {
        return Objects.hash(width, height, type, javaClass)
    }

    override fun equals(other: Any?): Boolean {
        return other === this
    }

    override fun toString(): String {
        return "This is the empty image."
    }
}
private class BitmapImage(private val image: BufferedImage): Image {
    companion object {
        fun create(width: Int, height: Int, type: ImageType): Image {
            val img = BufferedImage(width, height, typeToId(type))
            return BitmapImage(img)
        }
    }
    override val width: Int
        get() = image.width

    override val height: Int
        get() = image.height

    override val type: ImageType
        get() = idToType(image.type)

    private val drawer: Drawer by lazy {
        createBufferedImageDrawer(image)
    }

    private val processor: ImageProcessor by lazy {
        createBufferedImageProcessor(image)
    }

    override fun draw(draw: Drawer.() -> Unit) {
        drawer.draw()
    }

    override fun process(process: ImageProcessor.() -> Unit) {
        processor.process()
    }

    override fun scaledImage(scale: Double, rescaleAlgorithm: RescaleAlgorithms): Image {
        val w = (width * scale).toInt()
        val h = (width * scale).toInt()
        val dst = image.getScaledInstance(w, h, rescaleAlgorithm.toHint())
        val result = BufferedImage(w, h, image.type)
        result.createGraphics()
                .use{
                    drawImage(dst, 0, 0, null)
                }
        return BitmapImage(result)
    }

    private fun Graphics2D.use(func: Graphics2D.() -> Unit) {
        func()
        dispose()
    }

    override fun dispose() {
        drawer.dispose()
        processor.dispose()
        image.flush()
    }

    override fun hashCode(): Int = Objects.hash(image)
    override fun equals(other: Any?): Boolean = if (other is BitmapImage) other.image == image else false
    override fun toString(): String = "Image@${hashCode()}, width=${width}, height=${height}, type=${type}, bitmap"
}
private class VectorImage(override val width: Int, override val height: Int, override val type: ImageType): Image {
    private var vectorData: MutableList<PaintOperationWrapper> = mutableListOf()

    private val drawer: Drawer by lazy {
        createVectorDrawer(vectorData)
    }

    private val processor: ImageProcessor by lazy {
        createVectorImageProcessor(vectorData)
    }

    override fun draw(draw: Drawer.() -> Unit) {
        drawer.draw()
    }

    override fun process(process: ImageProcessor.() -> Unit) {
        processor.process()
    }

    override fun scaledImage(scale: Double, rescaleAlgorithm: RescaleAlgorithms): Image {
        val w = (width * scale).toInt()
        val h = (height * scale).toInt()
        val result = VectorImage(w, h, type)
        result.vectorData = vectorData.map {
            if (it.operationType() == PaintOperationWrapper.OperationType.FILTER) {
                it
            } else {
                val shape = it.shape as Shape
                val newShape: Shape
                        = Area(shape)
                        .createTransformedArea(
                                AffineTransform(doubleArrayOf(
                                        scale, 0.0, 0.0,
                                        0.0, scale, 0.0,
                                        0.0, 0.0, scale
                                ))
                        )
                wrap(newShape)
            }
        }.toMutableList()
        return result
    }

    override fun dispose() {
        drawer.dispose()
        processor.dispose()
        vectorData.clear()
    }

    override fun hashCode(): Int {
        return Objects.hash(vectorData, drawer, processor)
    }

    override fun equals(other: Any?): Boolean = if (other is VectorImage) vectorData == other.vectorData else false

    override fun toString(): String = "Image@${hashCode()}, vw=${width}, vh=${height}, type=${type}, vector"
}
private class SynchronizedImage(private val image: Image): Image by image {
    @Synchronized
    override fun draw(draw: Drawer.() -> Unit) {
        image.draw(draw)
    }

    @Synchronized
    override fun process(process: ImageProcessor.() -> Unit) {
        image.process(process)
    }

    @Synchronized
    override fun dispose() {
        image.dispose()
    }

    @Synchronized
    override fun scaledImage(scale: Double, rescaleAlgorithm: RescaleAlgorithms): Image {
        return image.scaledImage(scale, rescaleAlgorithm)
    }

    override fun toString(): String {
        val str = image.toString().split(", ").toMutableList()
        val newType = "synchronized ${str.last()}"
        str[str.size - 1] = newType
        return str.joinToString(separator = ", ")
    }
}

actual fun emptyImage(): Image = EmptyImage

actual fun createBitmapImage(width: Int, height: Int, type: ImageType): Image {
    if (width == 0 && height == 0) { return emptyImage() }

    require(width > 0 && height > 0) { "画像のサイズは正の数を指定してください" }

    return if (typeToId(type) != -1) BitmapImage.create(width, height, type) else TODO()
}

actual fun createSynchronizedBitmapImage(width: Int, height: Int, type: ImageType): Image = SynchronizedImage( createBitmapImage(width, height, type) )


actual fun createVectorImage(vw: Int, vh: Int, type: ImageType): Image {
    if (vw == 0 && vh == 0) { return emptyImage() }

    require(vw > 0 && vh > 0) { "画像のサイズは正の数を指定してください" }

    return VectorImage(vw, vh, type)
}

actual fun createSynchronizedVectorImage(vw: Int, vh: Int, type: ImageType): Image = SynchronizedImage( createVectorImage(vw, vh, type) )