package com.shahin.assignmentinfomvvm.data.network.services;



import com.shahin.assignmentinfomvvm.data.network.model.UserResponse;
import com.shahin.assignmentinfomvvm.utils.AppConstants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Shahin on 16/11/2019.
 */
public class UserService {

    private static final String URL = AppConstants.BASE_URL;

    private UserApi mUserApi;

    private static UserService instance;


    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {
        Retrofit mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();
        mUserApi = mRetrofit.create(UserApi.class);
    }

    public UserApi getUserApi() {
        return mUserApi;
    }

    public interface UserApi {
        @GET(AppConstants.FACTS_URL)
        Call<UserResponse> getAllUser();
    }

}


