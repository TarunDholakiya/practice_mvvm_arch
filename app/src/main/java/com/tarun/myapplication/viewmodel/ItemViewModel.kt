package com.tarun.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tarun.myapplication.MyApplication
import com.tarun.myapplication.model.User
import com.tarun.myapplication.remote.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel


class ItemViewModel : BaseViewModel() {
    val TAG = ItemViewModel::class.java.simpleName
    val items = MutableLiveData<List<User>>()

    fun getItemList() {
        items.value = MyApplication.db.userDao().getUsers()
        compositeDisposable.add(
            RemoteDataSource.getItemList()
                .subscribe({
                    if (it.isSuccessful) {
                        items.value = it.body()?.users
                        MyApplication.db.userDao().deleteAll()
                        items.value?.let { it1 -> MyApplication.db.userDao().insertAll(it1) }
                    } else {
                        initializeApiError(it)
                    }
                }, {
                    initializeIOError(it)
                })
        )
    }
}