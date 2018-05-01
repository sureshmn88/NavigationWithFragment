package com.example.intel.navigationwithfragment.WebService;

import com.example.intel.navigationwithfragment.Pojos.MineResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by manikkam on 27/4/18.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("signin.php")
    Call<MineResponse> getLoginDetails(@Field("name") String name, @Field("pass") String pass);

    @FormUrlEncoded
    @POST("signin.php")
    Call<StoreResponse> getLoginDetails(@Body User user);

    @GET("getallstores2.php?")
    Call<StoreResponse> getProductDetails(@Query("userid") String userid);
}
