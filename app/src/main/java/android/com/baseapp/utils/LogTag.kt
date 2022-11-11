package android.com.baseapp.utils

import android.util.Log
import javax.inject.Inject

open class LogTag @Inject constructor() {
    private val STACK_TRACE_LEVELS_UP = 5
    val TAG_SCB = "SCB-TrackLog"
    fun d(message: String) {
        Log.d("testt", message)
    }
}