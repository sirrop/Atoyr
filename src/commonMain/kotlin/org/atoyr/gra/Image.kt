package org.atoyr.gra

interface Image {
    val width: Int
    val height: Int
    val type: ImageType

    fun draw(draw: Drawer.() -> Unit)
    fun process(process: ImageProcessor.() -> Unit)

    fun scaledImage(scale: Double, rescaleAlgorithm: RescaleAlgorithms): Image

    fun dispose()
}

enum class ImageType {
    RGB24, RGBA32,  // 8bit RGBカラー
    RGB48, RGBA64,  // 16bit RGBカラー
    CMY24, CMYA32, // 8bit
    CMY48, CMYA64,  // 16bit
    CMYK32, CMYKA40,    //8bit
    CMYK64, CMYKA80,    // 16bit
    GRAY1, GRAY8, GRAY16    // グレースケール 1bit, 8bit, 16bit
}

enum class RescaleAlgorithms {
    DEFAULT, FAST, SMOOTH, REPLICATE, AREA_AVERAGE
}