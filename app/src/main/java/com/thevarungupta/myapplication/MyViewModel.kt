package com.thevarungupta.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

open class MyViewModel() : ViewModel() {

    open fun passData(query: String): LiveData<List<Result>> {

        val liveData = MutableLiveData<List<Result>>()

        ApiCall().getDataFromAPI(query).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    liveData.postValue(it.results)
                }
            }, {

            })

        return liveData
    }


}