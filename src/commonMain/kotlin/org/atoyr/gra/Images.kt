package org.atoyr.gra

expect fun emptyImage(): Image
expect fun createBitmapImage(width: Int, height: Int, type: ImageType): Image
expect fun createSynchronizedBitmapImage(width: Int, height: Int, type: ImageType): Image
expect fun createVectorImage(vw: Int, vh: Int, type: ImageType): Image
expect fun createSynchronizedVectorImage(vw: Int, vh: Int, type: ImageType): Image