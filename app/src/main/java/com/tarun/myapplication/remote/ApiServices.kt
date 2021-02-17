package com.tarun.myapplication.remote

import com.tarun.myapplication.model.Result
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("api/?results=10")
    fun getItemList(): Observable<Response<Result>>
}