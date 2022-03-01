package com.giles.wealthparkcoding

import android.app.Application
import androidx.navigation.NavController
import timber.log.Timber

open class WealthParkCodingApp : Application() {

    companion object {
        lateinit var wealthParkDatabase: WealthParkCodingAppDatabase
    }


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            /**
             * catch all the thread error
             * */
//            /** General avoid crash when release version */
//            Thread.setDefaultUncaughtExceptionHandler { thread, error ->
//                Timber.e("Crashed happened : $thread: $error")
//            }
        }
        wealthParkDatabase = WealthParkCodingAppDatabase.getDatabase(this)
    }
}