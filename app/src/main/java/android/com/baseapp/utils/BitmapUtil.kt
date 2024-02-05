package android.com.baseapp.utils

import android.R.attr
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Rect
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import android.util.TypedValue
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.roundToInt


object BitmapUtil {


    fun saveBitmapFromContentUri(
        context: Context,
        imageUri: Uri,
        maxSize: Int,
        outFile: File
    ): Boolean {
        var result = false

        var outputStream = FileOutputStream(outFile)
        try {
            var bitmap = getBitmapFromContentUri(context.contentResolver, imageUri)
            if (bitmap != null) {
                val bitmapWidth = bitmap.width
                val bitmapHeight = bitmap.height

                if (bitmapWidth > maxSize || bitmapHeight > maxSize) {
                    val ratio = maxSize.toDouble() / Math.max(bitmapWidth, bitmapHeight).toDouble()

                    bitmap = Bitmap.createScaledBitmap(
                        bitmap,
                        (bitmapWidth * ratio).roundToInt(),
                        (bitmapHeight * ratio).roundToInt(),
                        false
                    )
                }

                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
                result = true
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            outputStream.close()
        }

        return result
    }

    //@Nullable
    //@Throws(IOException::class)
    fun getBitmapFromContentUri(contentResolver: ContentResolver, imageUri: Uri): Bitmap? {
        val decodedBitmap = when {
            Build.VERSION.SDK_INT < 28 -> {
                try {
                    @Suppress("DEPRECATION")
                    MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                } catch (e: Exception) {
                    null
                }
            }

            else -> {
                try {
                    val source = ImageDecoder.createSource(contentResolver, imageUri)
                    ImageDecoder.decodeBitmap(source) { decoder, _, _ ->
                        decoder.setTargetSampleSize(1) // shrinking by
                        decoder.isMutableRequired =
                            true // this resolve the hardware type of bitmap problem
                    }
                } catch (e: Exception) {
                    null
                }
            }
        } ?: return null

        var rotationDegrees = 0
        var flipX = false
        var flipY = false
        //val orientation: Int = getExifOrientationTag(imageUri)
        /*when (orientation) {
            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> flipX = true
            ExifInterface.ORIENTATION_ROTATE_90 -> rotationDegrees = 90
            ExifInterface.ORIENTATION_TRANSPOSE -> {
                rotationDegrees = 90
                flipX = true
            }
            ExifInterface.ORIENTATION_ROTATE_180 -> rotationDegrees = 180
            ExifInterface.ORIENTATION_FLIP_VERTICAL -> flipY = true
            ExifInterface.ORIENTATION_ROTATE_270 -> rotationDegrees = -90
            ExifInterface.ORIENTATION_TRANSVERSE -> {
                rotationDegrees = -90
                flipX = true
            }
            ExifInterface.ORIENTATION_UNDEFINED, ExifInterface.ORIENTATION_NORMAL -> {}
            else -> {}
        }*/

        return rotateBitmap(decodedBitmap, rotationDegrees, flipX, flipY)
    }

    /** Rotates a bitmap if it is converted from a bytebuffer.  */
    private fun rotateBitmap(
        bitmap: Bitmap,
        rotationDegrees: Int,
        flipX: Boolean,
        flipY: Boolean,
        maxSize: Int = 1920
    ): Bitmap {
        val matrix = Matrix()

        // Rotate the image back to straight.
        matrix.postRotate(rotationDegrees.toFloat())

        // Mirror the image along the X or Y axis.
        matrix.postScale(if (flipX) -1.0f else 1.0f, if (flipY) -1.0f else 1.0f)
        var rotatedBitmap =
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)

        // Recycle the old bitmap if it has changed.
        if (rotatedBitmap != bitmap) {
            bitmap.recycle()
        }

        val bitmapWidth = rotatedBitmap.width
        val bitmapHeight = rotatedBitmap.height

        if (bitmapWidth > maxSize || bitmapHeight > maxSize) {
            val ratio = maxSize.toDouble() / Math.max(bitmapWidth, bitmapHeight).toDouble()

            rotatedBitmap = Bitmap.createScaledBitmap(
                rotatedBitmap,
                (bitmapWidth * ratio).roundToInt(),
                (bitmapHeight * ratio).roundToInt(),
                false
            )
        }

        return rotatedBitmap
    }

    private fun getExifOrientationTag(imageUri: Uri): Int {
        // We only support parsing EXIF orientation tag from local file on the device.
        // See also:
        // https://android-developers.googleblog.com/2016/12/introducing-the-exifinterface-support-library.html
        if (ContentResolver.SCHEME_CONTENT != imageUri.scheme
            && ContentResolver.SCHEME_FILE != imageUri.scheme
        ) {
            return 0
        }
        var exif: ExifInterface
        try {
            exif = ExifInterface(imageUri.path ?: "")
        } catch (e: IOException) {
            Log.e("BitmapUtil", "failed to open file to read rotation meta data: $imageUri", e)
            return 0
        }
        return exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )
    }

    fun getBitmapFromFile(filePath: String): Bitmap? {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888 // Adjust config as needed
        return BitmapFactory.decodeFile(filePath, options)
    }

    fun writeTextToBitmap(inputBitmap: Bitmap, text: String, textSize: Float): Bitmap {

        var bitmapConfig: Bitmap.Config = Bitmap.Config.ARGB_8888
        // resource bitmaps is mutable,
        // so we need to convert it to mutable one
        var resultBitmap = inputBitmap.copy(bitmapConfig, true)

        val canvas = Canvas(resultBitmap)

        // new antialiasing Paint
        val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.RED
        // text size in pixels
        // Get the bounds of the text, using our TextSize.
        paint.textSize = textSize
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        // Calculate the desired size as a proportion of our TextSize.
        val desiredTextSize: Float = textSize * resultBitmap.width / bounds.width()
        // Set the paint for that size.
        paint.textSize = desiredTextSize
        // text shadow
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE)

        // init StaticLayout for text
        val textLayout = StaticLayout.Builder.obtain(
            text, 0, text.length, paint, resultBitmap.width - 10.px.toInt()
        ).setAlignment(Layout.Alignment.ALIGN_OPPOSITE).build()

        // set text width to canvas width minus 5dp padding
        val textWidth = textLayout.width.toFloat()
        // get height of multiline text
        //val textHeight = textLayout.height

        //val x:Float = resultBitmap.width - textWidth
        val x = 0f
        val y = 0f

        canvas.save()
        canvas.translate(x, y)
        textLayout.draw(canvas)
        canvas.restore()

        return resultBitmap
    }
}