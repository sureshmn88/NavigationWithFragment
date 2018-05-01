package com.example.intel.navigationwithfragment.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.intel.navigationwithfragment.Activity.MainActivity;
import com.example.intel.navigationwithfragment.Adapter.ProductAdapter;
import com.example.intel.navigationwithfragment.R;
import com.example.intel.navigationwithfragment.Utils.GlobalValues;
import com.example.intel.navigationwithfragment.WebService.APIClient;
import com.example.intel.navigationwithfragment.WebService.ApiInterface;
import com.example.intel.navigationwithfragment.WebService.StoreResponse;
import com.example.intel.navigationwithfragment.WebService.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends Fragment {

    String TAG="ProfileTAG";
    GlobalValues mGlobalValues;
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_profile, container, false);
        mGlobalValues=new GlobalValues(getContext());
        mRecyclerView=(RecyclerView)rootview.findViewById(R.id.recycelr_list);
        callAPIService();
        return rootview;
    }

    void callAPIService() {

        ApiInterface apiService =APIClient.getClient().create(ApiInterface.class);

        /*
        User user=new User();
        user.setName("manik");
        user.setPass("123");
        Call<StoreResponse> call = apiService.getLoginDetails(user);
        */

        Call<StoreResponse> call = apiService.getProductDetails("0");
        call.enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse>call, Response<StoreResponse> response) {
                StoreResponse mineResponse = response.body();
                if (mineResponse.getStatus().equals("true")) {
                    Log.d(TAG, "Number of movies received: " + mineResponse.getStoreDetails().get(0).getPhone());
                    Toast.makeText(getActivity(), mineResponse.getStatus(), Toast.LENGTH_SHORT).show();

                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    ProductAdapter mProductAdapter=new ProductAdapter(getActivity(),mineResponse.getStoreDetails());
                    mRecyclerView.setAdapter(mProductAdapter);

                    /*
                    Intent intent=new Intent(getActivity(), MainActivity.class);
                    intent.putParcelableArrayListExtra("testList",mineResponse.getStoreDetails());
                    startActivity(intent);
                    */

                } else {
                    // False Statement
                }

            }

            @Override
            public void onFailure(Call<StoreResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                //Toast.makeText(MainActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

}
