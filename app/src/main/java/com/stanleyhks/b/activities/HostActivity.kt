package com.stanleyhks.b.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import com.stanleyhks.b.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_activity)

        lifecycleScope.launch(Dispatchers.IO) {

            val gadId =
                AdvertisingIdClient.getAdvertisingIdInfo(application.applicationContext).id.toString()
            OneSignal.initWithContext(application.applicationContext)
            OneSignal.setAppId(this@HostActivity.getString(R.string.onesignal_id))
            OneSignal.setExternalUserId(gadId)
        }
    }
}