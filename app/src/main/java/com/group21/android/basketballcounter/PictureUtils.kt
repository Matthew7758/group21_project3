package com.group21.android.basketballcounter

import android.R.attr
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import kotlin.math.roundToInt
import android.R.attr.path
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface
import java.io.IOException


fun getScaledBitmap(path: String, activity: Activity): Bitmap {
    val size = Point()
    activity.windowManager.defaultDisplay.getSize(size)
    return getScaledBitmap(path, size.x, size.y)
}

fun getScaledBitmap(path: String, destWidth: Int, destHeight: Int):
        Bitmap {
    var options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(path, options)

    val srcWidth = options.outWidth.toFloat()
    val srcHeight = options.outHeight.toFloat()

    var inSampleSize = 1
    if (srcHeight > destHeight || srcWidth > destWidth) {
        val heightScale = srcHeight / destHeight
        val widthScale = srcWidth / destWidth
        val sampleScale = if (heightScale > widthScale) {
            heightScale
        } else {
            widthScale
        }
        inSampleSize = sampleScale.roundToInt()
    }
    options = BitmapFactory.Options()
    options.inSampleSize = inSampleSize
    return BitmapFactory.decodeFile(path, options)
}

@Throws(IOException::class)
fun rotateImage(bitmap: Bitmap, path: String): Bitmap {

    var rotate = 0
    val exif = ExifInterface(path)
    val orientation: Int = exif.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_NORMAL
    )
    when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
        ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
        ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
    }
    val matrix = Matrix()
    matrix.postRotate(rotate.toFloat())
    return Bitmap.createBitmap(
        bitmap, 0, 0, bitmap.width,
        bitmap.height, matrix, true
    )
}

class PictureUtils