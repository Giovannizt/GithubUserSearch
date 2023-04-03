package com.giovanni.githubuserssearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback

class MainViewModel: ViewModel() {
    private val _username = MutableLiveData<List<ItemsItem>>()
    val username: LiveData<List<ItemsItem>> = _username

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MainViewModel"
        private const val USERNAME = "Arif"
    }

    init {
        findUsers(USERNAME)
    }
    fun findUsers(query:String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsers(query)
        client.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>){
                _isLoading.value = false
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null) {
                        _username.value = response.body()?.items as List<ItemsItem>?
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

            }

            override fun onFailure(
                call: Call<Response>,
                t: Throwable
            ) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}