package com.android.dailynote.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by KimBH on 2021/10/18.
 */
interface ApiClient {

    companion object {

        fun getClient(): ApiClient = retrofit.create(ApiClient::class.java)

        private fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

        private fun gsonFactory(): GsonConverterFactory {
            val gson = GsonBuilder().setLenient().create()
            return GsonConverterFactory.create(gson)
        }

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(HttpDefine.getHost())
                .client(okHttpClient())
                .addConverterFactory(gsonFactory())
                .build()
        }

    }

    //Api List
//    @GET("/mobile/v1/api/getAllList.do")
//    fun getAllList() : Call<ArrayList<Test>>Test
}