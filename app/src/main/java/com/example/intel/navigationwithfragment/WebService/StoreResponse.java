package com.example.intel.navigationwithfragment.WebService;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StoreResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("count")
    private int count;
    @SerializedName("data")
    private ArrayList<StoreDetail> storeDetails;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<StoreDetail> getStoreDetails() {
        return storeDetails;
    }

    public void setStoreDetails(ArrayList<StoreDetail> storeDetails) {
        this.storeDetails = storeDetails;
    }
}


