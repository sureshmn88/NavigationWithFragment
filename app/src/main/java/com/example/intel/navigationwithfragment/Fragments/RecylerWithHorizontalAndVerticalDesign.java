package com.example.intel.navigationwithfragment.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
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
import com.example.intel.navigationwithfragment.Utils.Constants;
import com.example.intel.navigationwithfragment.Utils.GlobalValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class RecylerWithHorizontalAndVerticalDesign extends Fragment {
    RecyclerView mRecyclerView;
    StaggeredGridLayoutManager mLayoutManager;
    DetailAdapter mAdapter;
    ArrayList<PersonalDetails> mList=new ArrayList<>();
    GlobalValues mGlobalValues;
    int[] array ={1,2};

    public RecylerWithHorizontalAndVerticalDesign() {
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

            AndroidNetworking.post(Constants.GET_DETAILS)
                    .setTag("GetDetails")
                    .setPriority(Priority.HIGH)
                    // .addBodyParameter("name", "34") // Parameter Name and Value
                    //.addBodyParameter("pass", "454")
                    .build()
                    //.getAsJSONArray()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // do anything with response

                            CommonFuns.stopProgressDialog(getActivity());

                            try {
                                JSONArray jsonArray=new JSONArray(Constants.SampleJSON1);

                                mList=new ArrayList<>();

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jObj = jsonArray.getJSONObject(i);
                                    PersonalDetails item = new PersonalDetails();
                                    item.setCity(jObj.getString("id"));
                                    item.setName(jObj.getString("name"));
                                    item.setState(jObj.getString("price"));
                                    item.setEmail(jObj.getString("uom"));
                                    //this staticvalues for use ziczac design
                                    item.setType(String.valueOf(getRandom()));
                                    mList.add(item);

                                }

                                FetchDataWithAdapter();


                            } catch (JSONException e) {
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

    public int getRandom() {
        int rnd=new Random().nextInt(array.length);
        return array[rnd];
    }

    void FetchDataWithAdapter() {

        if(mList.size()>0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            //alertTxt.setVisibility(View.GONE);
            mRecyclerView.setHasFixedSize(true);

            mLayoutManager = new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            mLayoutManager.setItemPrefetchEnabled(false);


            mAdapter = new DetailAdapter(getActivity(), mList,mRecyclerView);
            mRecyclerView.setAdapter(mAdapter);
           // mAdapter.notifyDataSetChanged();

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
