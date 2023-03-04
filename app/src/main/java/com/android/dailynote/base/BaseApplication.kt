package com.android.dailynote.base

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle

//import com.kakao.sdk.common.KakaoSdk

class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
//        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }

    fun getMetaDataString(metaDataKey: String): String {
        return try {
            val ai = packageManager.getApplicationInfo(applicationContext.packageName,
                PackageManager.ApplicationInfoFlags.of(PackageManager.GET_META_DATA.toLong()));
            val bundle: Bundle = ai.metaData
            bundle.getString(metaDataKey) ?: ""
        } catch (e: PackageManager.NameNotFoundException) {
            ""
        }
    }

}