package com.tarun.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tarun.myapplication.R
import com.tarun.myapplication.remote.ConnectivityInterceptor
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response


abstract class BaseViewModel : ViewModel() {
    companion object

    val error = MutableLiveData<Any>()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun initializeApiError(response: Response<*>) {
        // Use response code for specific error handling
        error.value = R.string.something_went_wrong
    }

    fun initializeIOError(throwable: Throwable) {
        if (throwable is ConnectivityInterceptor.NoConnectivityException) {
            error.value = R.string.internet_not_available
        } else {
            error.value = R.string.something_went_wrong
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}