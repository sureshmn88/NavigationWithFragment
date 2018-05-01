package com.example.intel.navigationwithfragment.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.intel.navigationwithfragment.Adapter.DetailAdapter;
import com.example.intel.navigationwithfragment.Adapter.DetailImageAdapter;
import com.example.intel.navigationwithfragment.Pojos.PersonalDetails;
import com.example.intel.navigationwithfragment.Pojos.PersonalImage;
import com.example.intel.navigationwithfragment.R;
import com.example.intel.navigationwithfragment.Utils.CommonFuns;
import com.example.intel.navigationwithfragment.Utils.GlobalValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImagesDesignWithrecy extends Fragment {
    RecyclerView mRecyclerView;
    DetailImageAdapter mAdapter;
    ArrayList<PersonalImage> mList=new ArrayList<>();
    GlobalValues mGlobalValues;

    public ImagesDesignWithrecy() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.recyclerviewhorzi, container, false);
        mGlobalValues=new GlobalValues(getContext());
        mRecyclerView=(RecyclerView)rootview.findViewById(R.id.recycelr_list);


        //getDetailsFromServer(numberEdt.getText().toString());
        getDetailsFromServer();


        return rootview;
    }

    void getDetailsFromServer() {

        if (CommonFuns.isNetworkConnected(getActivity())) {

            CommonFuns.showProgressDialog(getActivity());

            // Get
            //AndroidNetworking.get(Constants.GET_DETAILS)

            String Url="http://andydevelopment.com/CurbCart/getallstores2.php?userid=0";
            //String Url="http://andydevelopment.com/CurbCart/getallstores2.php?userid="+valuesedt
            // +"&email="+emaiedt+"&age="+ageedt;

            Log.d("TAGGG","url : "+Url);

            AndroidNetworking.get(Url)
                    .setTag("GetDetails")
                    .setPriority(Priority.HIGH)
                    // .addBodyParameter("name", "34") // Parameter Name and Value
                    //.addBodyParameter("pass", "454")
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // do anything with response


                            Log.d("TAGW", "Server Response:" + response);
                            CommonFuns.stopProgressDialog(getActivity());

                            try {
                                if (response.has("status")) {
                                    if(response.getString("status").equals("true")){
                                        JSONArray itemArray = response.getJSONArray("data");

                                        mList=new ArrayList<>();

                                        for (int i = 0; i < itemArray.length(); i++) {
                                            JSONObject jObj = itemArray.getJSONObject(i);
                                            PersonalImage item = new PersonalImage();
                                            item.setName(jObj.getString("storename"));
                                            item.setCity(jObj.getString("city"));
                                            item.setState(jObj.getString("state"));
                                            item.setEmail(jObj.getString("email"));
                                            item.setCountry(jObj.getString("country"));
                                            item.setImage(jObj.getString("image"));
                                            mList.add(item);
                                            mGlobalValues.put("email",jObj.getString("email"));

                                            //mDBHandler.addProductJsonData(jObj.getString("id"), jObj.getString("name"), jObj.getString("price"), jObj.getString("uom"));
                                        }

                                        FetchDataWithAdapter();





                                    }

                                } }catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(ANError error) {
                            // handle error
                            //prepareResponse(error.getErrorBody());
                            CommonFuns.stopProgressDialog(getActivity());
                            Toast.makeText(getActivity(), error.getErrorBody(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }



    }

    void FetchDataWithAdapter() {

        if(mList.size()>0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            //alertTxt.setVisibility(View.GONE);

            mAdapter = new DetailImageAdapter(getActivity(), mList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            /*mAdapter.setOnClickListener(new ProductData.OnClickListener() {
                @Override
                public void onLayoutClick(int position) {
                    selectPosition = position;

                    Intent intent = new Intent(MainActivity.this, AddDataList.class);
                    intent.putExtra("getdata", mList.get(selectPosition));
                    startActivityForResult(intent, requestCodeForResult);
                }
            });*/
        }else {

            //mRecyclerView.setVisibility(View.GONE);
            // alertTxt.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
