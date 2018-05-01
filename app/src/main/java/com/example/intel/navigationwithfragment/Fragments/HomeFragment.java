package com.example.intel.navigationwithfragment.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intel.navigationwithfragment.R;
import com.example.intel.navigationwithfragment.Utils.CommonFuns;
import com.example.intel.navigationwithfragment.WebService.APIClient;
import com.example.intel.navigationwithfragment.WebService.ApiInterface;
import com.example.intel.navigationwithfragment.Pojos.MineResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment{

    EditText edt_Username,edt_Password;
    Button Btn_Login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login_fragment,null);
        intilise(view);
        return view;
    }

    void intilise(View view) {
        edt_Username=view.findViewById(R.id.username);
        edt_Password=view.findViewById(R.id.password_edt);
        Btn_Login=view.findViewById(R.id.login_btn);

        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callWebService();
            }
        });



    }

    void callWebService() {
        CommonFuns.showProgressDialog(getActivity());

        ApiInterface apiInterface= APIClient.getClient1().create(ApiInterface.class);


        Call<MineResponse> call=apiInterface.getLoginDetails(edt_Username.getText().toString(),edt_Password.getText().toString());

        call.enqueue(new Callback<MineResponse>() {

            @Override
            public void onResponse(Call<MineResponse> call, Response<MineResponse> response) {
                CommonFuns.stopProgressDialog(getActivity());
                MineResponse mineResponse=response.body();
                Log.d("TAG","Result : " + response.body().toString());
                if(mineResponse.getIsError().equals("false")){

                    Toast.makeText(getActivity(),mineResponse.getMsg(),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getActivity(),mineResponse.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MineResponse> call, Throwable t) {

                CommonFuns.stopProgressDialog(getActivity());
            }
        });


    }
}
