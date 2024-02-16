package android.com.baseapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.BUILD_TYPE != "release"){
            plant(MyDebugTree())
        }
    }
}

class MyDebugTree : Timber.DebugTree() {
    private val TAG = "timber"
    override fun createStackElementTag(element: StackTraceElement): String {
        Log.d("testt","createStackElementTag")
        return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        //prevent tag maximum length -> add tag to message
        if(tag?.contains(":", true) == true){
            //using default tag, because user not set tag -> createStackElementTag is called to generate tag
            super.log(priority, TAG ,"$message - $tag", t)
        }else{
            //normally with tag is set by user
            super.log(priority, tag ,message, t)
        }
    }
}
