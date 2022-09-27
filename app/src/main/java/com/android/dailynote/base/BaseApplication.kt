package com.android.dailynote.base

import android.app.Application
//import com.kakao.sdk.common.KakaoSdk

class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
//        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }
}