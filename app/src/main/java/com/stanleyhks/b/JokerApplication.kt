package com.stanleyhks.b

import android.app.Application
import com.onesignal.OneSignal

class JokerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        OneSignal.initWithContext(applicationContext)
        OneSignal.setAppId(applicationContext.getString(R.string.onesignal_id))
    }

    companion object {
        lateinit var adID: String
    }
}