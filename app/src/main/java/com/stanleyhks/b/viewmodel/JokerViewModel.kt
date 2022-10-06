package com.stanleyhks.b.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.applinks.AppLinkData
import com.onesignal.OneSignal
import com.stanleyhks.b.JokerApplication
import com.stanleyhks.b.R
import com.stanleyhks.b.model.UrlEntity
import com.stanleyhks.b.repository.JokerRepository
import com.stanleyhks.b.util.UriBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokerViewModel(private val rep: JokerRepository, application: Application) :
    AndroidViewModel(application) {

    val urlEntity = rep.urlEntity

    private val builder = UriBuilder()
    private val url = MutableLiveData<String>()

    val urlLiveData: LiveData<String> = url

    fun getDeepLink(activity: Context?) {
        AppLinkData.fetchDeferredAppLinkData(activity) {
            when (it?.targetUri.toString()) {
                "null" -> {
                    viewModelScope.launch {
                        Log.d("customTag", "apps status ${urlEntity?.appsStatus.toString()}")
                        if (urlEntity?.appsStatus == null) {
                            startApps(activity!!)
                            Log.d("customTAGSPECISAL", "apps invocation")
                        }
                    }
                }
                else -> {
                    url.postValue(
                        builder.buildUrl(
                            it?.targetUri.toString(),
                            null,
                            activity!!,
                        )
                    )
                    sendTag(it?.targetUri.toString(), null)
                }
            }
        }
    }

    private fun startApps(activity: Context) {
        Log.d("customTag", "in apps")
        AppsFlyerLib.getInstance()
            .init(activity.getString(R.string.apps_dev_key), object : AppsFlyerConversionListener {
                override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                    url.postValue(builder.buildUrl("null", data, activity))
                    sendTag("null", data)
                    Log.d("customTag", "apps success")
                }

                override fun onConversionDataFail(p0: String?) {
                    url.postValue(builder.buildUrl("null", null, activity))
                    Log.d("customTag", "apps fail")
                }

                override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}

                override fun onAttributionFailure(p0: String?) {}

            }, activity)
        AppsFlyerLib.getInstance().start(activity)
    }

    fun sendTag(deepLink: String, data: MutableMap<String, Any>?) {
        val campaign = data?.get("campaign").toString()

        if (campaign == "null" && deepLink == "null") {
            OneSignal.sendTag("key2", "organic")
        } else if (deepLink != "null") {
            OneSignal.sendTag("key2", deepLink.replace("myapp://", "").substringBefore("/"))
        } else if (campaign != "null") {
            OneSignal.sendTag("key2", campaign.substringBefore("_"))
        }
    }

    fun checkADB(activity: Activity): String? {
        return Settings.Global.getString(activity.contentResolver, Settings.Global.ADB_ENABLED)
    }

    fun saveUrl(urlEntity: UrlEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            rep.insertUrl(urlEntity)
        }
}