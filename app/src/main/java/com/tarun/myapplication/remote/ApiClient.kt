package com.tarun.myapplication.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.tarun.myapplication.utils.NetworkUtils
import com.tarun.myapplication.MyApplication
import com.tarun.myapplication.R
import com.tarun.myapplication.utils.Constants.BASE_URL
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApiClient {
    private lateinit var retrofit: Retrofit
    private lateinit var apiServices: ApiServices

    fun getApiService(): ApiServices {
        getInstance()
        return apiServices
    }

    private fun getInstance(): Retrofit {
        if (this::retrofit.isInitialized.not()) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .addInterceptor(ConnectivityInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(0, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        }
        apiServices = retrofit.create(ApiServices::class.java)
        return retrofit
    }
}

class ConnectivityInterceptor : Interceptor {

    @RequiresApi(Build.VERSION_CODES.M)
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val isNetworkActive = NetworkUtils.isNetworkConnected(MyApplication.get())
        return if (!isNetworkActive) {
            throw NoConnectivityException(
                message = String()
            )
        } else {
            chain.proceed(chain.request())
        }
    }

    /**
     * Throws NoConnectivityException if network not available
     */
    class NoConnectivityException(override var message: String) :
        IOException(MyApplication.get().getString(R.string.internet_not_available))
}
