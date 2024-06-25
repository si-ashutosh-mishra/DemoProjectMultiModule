package com.example.base.utils

import com.example.base.helper.BaseConfigContract
import com.example.base.helper.BaseLocalStorageManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CustomRequestInterceptor @Inject constructor(
    private val baseConfigContract: BaseConfigContract,
    private val baseLocalStorageManager: BaseLocalStorageManager
) : Interceptor {

    @Throws(IllegalArgumentException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("auth", baseConfigContract.getApiAuthKey())
        val token = runBlocking { baseLocalStorageManager.getUserToken().firstOrNull()?:"" }
        token?.let {
            builder.addHeader("usertoken", it)
        }
        return chain.proceed(builder.build())
    }
}
