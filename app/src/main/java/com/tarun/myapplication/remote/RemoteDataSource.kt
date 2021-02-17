package com.tarun.myapplication.remote

import com.tarun.myapplication.model.Result
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

object RemoteDataSource {
    private val apiServices = ApiClient.getApiService()

    fun getItemList(): Observable<Response<Result>> {
        return apiServices.getItemList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}