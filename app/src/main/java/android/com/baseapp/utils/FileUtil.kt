package android.com.baseapp.utils

import android.com.baseapp.utils.BitmapUtil.getBitmapFromContentUri
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.roundToInt


object FileUtil {

    fun getCaptureImageFolder(context: Context): File {
        val captureImageFolder = File(context.filesDir, "CaptureImage")
        if (!captureImageFolder.exists()) {
            captureImageFolder.mkdir()
        }
        return captureImageFolder
    }

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

    fun deleteCaptureImageFolder(context: Context) {
        val captureImageFolder = getCaptureImageFolder(context)
        if (captureImageFolder.isDirectory) {
            captureImageFolder.listFiles()?.let {
                for (file: File in it) {
                    file.delete()
                }
            }
        }
    }

    fun compressBitmapFile(originalFile: File, outFile: File): Boolean {
        var inputStream: FileInputStream? = null
        var outputStream: FileOutputStream? = null
        return try {
            inputStream = FileInputStream(originalFile)
            val originalBitmap = BitmapFactory.decodeStream(inputStream)
            outFile.createNewFile()
            outputStream = FileOutputStream(outFile)
            originalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
            true
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
            false
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}