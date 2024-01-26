package android.com.baseapp.data.api

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class PrettyLogger : HttpLoggingInterceptor.Logger {
    private val mGson = GsonBuilder().setPrettyPrinting().create()
    private val TAG_API = "GSON"
    override fun log(message: String) {
        val trimMessage = message.trim { it <= ' ' }
        if (trimMessage.startsWith("{") && trimMessage.endsWith("}")
            || trimMessage.startsWith("[") && trimMessage.endsWith("]")
        ) {
            try {
                val prettyJson = mGson.toJson(JsonParser.parseString(message))
                Timber.tag(TAG_API).i(prettyJson)
            } catch (e: Exception) {
                Timber.tag(TAG_API).e(e, message)
            }
        } else {
            //convert URL-encoded text to Unicode character
            //%C6%A1 to ơ
            val decodedText = URLDecoder.decode(message, StandardCharsets.UTF_8.toString())
            Timber.tag(TAG_API).i(decodedText)
        }
    }
}