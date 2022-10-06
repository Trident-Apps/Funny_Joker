package com.stanleyhks.b.util

import android.content.Context
import androidx.core.net.toUri
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.stanleyhks.b.JokerApplication
import com.stanleyhks.b.R
import com.stanleyhks.b.activities.HostActivity
import java.util.*

class UriBuilder {

    fun buildUrl(
        deeplink: String,
        data: MutableMap<String, Any>?,
        activity: Context
    ): String {
        val sb = java.lang.StringBuilder()
        val sourceKeyParam: String
        val afIdKeyParam: String

        if (deeplink == "null") {
            sourceKeyParam = data?.get("media_source").toString()
            afIdKeyParam = AppsFlyerLib.getInstance().getAppsFlyerUID(activity).toString()
        } else {
            sourceKeyParam = "deeplink"
            afIdKeyParam = "null"
        }
        sb.append(activity.getString(R.string.base_url))
            .append("?")
            .append(activity.getString(R.string.secure_get_parametr))
            .append("=")
            .append(activity.getString(R.string.secure_key))
            .append("&")
            .append(activity.getString(R.string.dev_tmz_key))
            .append("=")
            .append(TimeZone.getDefault().id)
            .append("&")
            .append(activity.getString(R.string.gadid_key))
            .append("=")
            .append(JokerApplication.adID)
            .append("&")
            .append(activity.getString(R.string.deeplink_key))
            .append("=")
            .append(deeplink)
            .append("&")
            .append(activity.getString(R.string.source_key))
            .append("=")
            .append(sourceKeyParam)
            .append("&")
            .append(activity.getString(R.string.af_id_key))
            .append("=")
            .append(afIdKeyParam)
            .append("&")
            .append(activity.getString(R.string.adset_id_key))
            .append("=")
            .append(data?.get("adset_id"))
            .append("&")
            .append(activity.getString(R.string.campaign_id_key))
            .append("=")
            .append(data?.get("campaign_id"))
            .append("&")
            .append(activity.getString(R.string.app_campaign_key))
            .append("=")
            .append(data?.get("campaign"))
            .append("&")
            .append(activity.getString(R.string.adset_key))
            .append("=")
            .append(data?.get("adset"))
            .append("&")
            .append(activity.getString(R.string.adgroup_key))
            .append("=")
            .append(data?.get("adgroup"))
            .append("&")
            .append(activity.getString(R.string.orig_cost_key))
            .append("=")
            .append(data?.get("orig_cost"))
            .append("&")
            .append(activity.getString(R.string.af_siteid_key))
            .append("=")
            .append(data?.get("af_siteid"))
        return sb.toString()
    }

//    fun buildUrl(
//        deeplink: String,
//        data: MutableMap<String, Any>?,
//        adId: String,
//        activity: Context?
//    ): String {
//
//        val url = activity!!.getString(R.string.base_url).toUri().buildUpon().apply {
//            appendQueryParameter(
//                activity.getString(R.string.secure_get_parametr),
//                activity.getString(R.string.secure_key)
//            )
//            appendQueryParameter(activity.getString(R.string.dev_tmz_key), TimeZone.getDefault().id)
//            appendQueryParameter(activity.getString(R.string.gadid_key), adId)
//            appendQueryParameter(activity.getString(R.string.deeplink_key), deeplink)
//            appendQueryParameter(
//                activity.getString(R.string.source_key),
//                data?.get("media_source").toString()
//            )
//            when (deeplink) {
//                "null" -> {
//                    appendQueryParameter(
//                        activity.getString(R.string.source_key),
//                        data?.get("media_source").toString()
//                    )
//                    appendQueryParameter(
//                        activity.getString(R.string.af_id_key),
//                        AppsFlyerLib.getInstance().getAppsFlyerUID(activity.applicationContext)
//                    )
//                }
//                else -> {
//                    appendQueryParameter(activity.getString(R.string.source_key), "deeplink")
//                    appendQueryParameter(activity.getString(R.string.af_id_key), "null")
//                }
//            }
//            appendQueryParameter(
//                activity.getString(R.string.af_id_key),
//                AppsFlyerLib.getInstance().getAppsFlyerUID(activity.applicationContext)
//            )
//            appendQueryParameter(
//                activity.getString(R.string.adset_id_key),
//                data?.get("adset_id").toString()
//            )
//            appendQueryParameter(
//                activity.getString(R.string.campaign_id_key),
//                data?.get("campaign_id").toString()
//            )
//            appendQueryParameter(
//                activity.getString(R.string.app_campaign_key),
//                data?.get("campaign").toString()
//            )
//            appendQueryParameter(
//                activity.getString(R.string.adset_key),
//                data?.get("adset").toString()
//            )
//            appendQueryParameter(
//                activity.getString(R.string.adgroup_key),
//                data?.get("adgroup").toString()
//            )
//            appendQueryParameter(
//                activity.getString(R.string.orig_cost_key),
//                data?.get("orig_cost").toString()
//            )
//            appendQueryParameter(
//                activity.getString(R.string.af_siteid_key),
//                data?.get("af_siteid").toString()
//            )
//
//        }.toString()
//        return url
//    }
}