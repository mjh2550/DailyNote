package com.android.dailynote.base

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle

//import com.kakao.sdk.common.KakaoSdk

class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
//        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }

    fun getMetaDataString(metaDataKey: String, context: Context): String {
        return try {
            val ai = context.packageManager.getApplicationInfo(context.packageName,PackageManager.GET_META_DATA)
            val bundle: Bundle = ai.metaData!!
            bundle.getString(metaDataKey) ?: ""
        } catch (e: PackageManager.NameNotFoundException) {
            ""
        }
    }

}