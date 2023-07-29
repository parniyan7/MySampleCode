package ir.miare.challenge

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApp : Application(), Application.ActivityLifecycleCallbacks {

    private val TAG = MyApp::class.java.name


    companion object {
        private val TAG = "App"
        private var appInstance: MyApp? = null

        @Deprecated(message = "use Hilt to inject context", level = DeprecationLevel.WARNING)
        fun getInstance(): MyApp {
            Log.d(TAG, "getInstance: ")
            if (appInstance == null) appInstance = MyApp()
            return appInstance!!
        }

    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        Log.d(TAG, "onActivityCreated: ")
    }

    override fun onActivityStarted(p0: Activity) {
        Log.d(TAG, "onActivityStarted: ")
    }

    override fun onActivityResumed(p0: Activity) {
        Log.d(TAG, "onActivityResumed: ")
    }

    override fun onActivityPaused(p0: Activity) {
        Log.d(TAG, "onActivityPaused: ")
    }

    override fun onActivityStopped(p0: Activity) {
        Log.d(TAG, "onActivityStopped: ")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        Log.d(TAG, "onActivitySaveInstanceState: ")
    }

    override fun onActivityDestroyed(p0: Activity) {
        Log.d(TAG, "onActivityDestroyed: ")
    }
}
