package com.shahin.assignmentinfomvvm.ui.activity.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import com.shahin.assignmentinfomvvm.data.network.model.UserData
import com.shahin.assignmentinfomvvm.data.network.model.UserResponse
import com.shahin.assignmentinfomvvm.data.network.services.UserService

import java.util.ArrayList
import java.util.Collections

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Shahin on 17/11/2019.
 */
class MainViewModel internal constructor(private val userService: UserService) : ViewModel() {

    internal val userDatas: MutableLiveData<List<UserData>>
    internal val loadingStatus: MutableLiveData<Boolean>

    init {
        userDatas = MutableLiveData()
        loadingStatus = MutableLiveData()
    }

    internal fun loadUserDatasNetwork() {
        setIsLoading(true)

        val UserDataCall = userService.userApi.allUser
        UserDataCall.enqueue(UserDataCallback())
    }

    internal fun loadUserDataLocal() {
        setIsLoading(true)

        val name = "Breaking Bad"
        val image =
            "https://coderwall-assets-0.s3.amazonaws.com/uploads/picture/file/622/breaking_bad_css3_svg_raw.png"

        val UserDatas = ArrayList<UserData>()
        UserDatas.add(UserData(name, image, name))
        UserDatas.add(UserData(name, image, name))
        UserDatas.add(UserData(name, image, name))
        setUserDatas(UserDatas)
    }

    internal fun showEmptyList() {
        setUserDatas(emptyList())
    }

    private fun setIsLoading(loading: Boolean) {
        loadingStatus.postValue(loading)
    }

    private fun setUserDatas(UserDatas: List<UserData>?) {
        setIsLoading(false)
        userDatas.postValue(UserDatas)
    }

    /**
     * Callback
     */
    private inner class UserDataCallback : Callback<UserResponse> {

        override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
            val UserDataResponse = response.body()

            if (UserDataResponse != null) {
                setUserDatas(UserDataResponse.userData)
            } else {
                setUserDatas(emptyList())
            }
        }

        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            setUserDatas(emptyList())

        }
    }
}
