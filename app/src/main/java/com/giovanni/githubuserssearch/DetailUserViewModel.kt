package com.giovanni.githubuserssearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {
    private val _userDetail = MutableLiveData<DetailUserResponse>()
    val userDetail: LiveData<DetailUserResponse> = _userDetail
    private val _userFollower = MutableLiveData<List<ItemsItem>>()
    val userFollower: LiveData<List<ItemsItem>> = _userFollower
    private val _isLoadingUser = MutableLiveData<Boolean>()
    val isLoadingUser: LiveData<Boolean> = _isLoadingUser
    private val _userFollowing = MutableLiveData<List<ItemsItem>>()
    val userFollowing: LiveData<List<ItemsItem>> = _userFollowing

    companion object{
        private const val TAG = "DetailViewModel"
    }

    fun getUserDetail(query: String){
        _isLoadingUser.value = true
        val client =ApiConfig.getApiService().getDetailUsers(query)
        client.enqueue(object : Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoadingUser.value = false
                if(response.isSuccessful){
                    _userDetail.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoadingUser.value = false
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getFolllower(query: String){
        _isLoadingUser.value = true
        val client =ApiConfig.getApiService().getUserFollower(query)
        client.enqueue(object : Callback<List<ItemsItem>>{
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoadingUser.value = false
                if(response.isSuccessful){
                    _userFollower.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoadingUser.value = false
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getFolllowing(query: String){
        _isLoadingUser.value = true
        val client =ApiConfig.getApiService().getUserFollowing(query)
        client.enqueue(object : Callback<List<ItemsItem>>{
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoadingUser.value = false
                if(response.isSuccessful){
                    _userFollowing.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoadingUser.value = false
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
    }
}