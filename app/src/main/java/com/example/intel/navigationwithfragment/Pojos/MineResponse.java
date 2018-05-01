package com.example.intel.navigationwithfragment.Pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manikkam on 27/4/18.
 */

public class MineResponse {

    @SerializedName("isError")
    private String isError;
    @SerializedName("msg")
    private String msg;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public String getIsError() {
        return isError;
    }

    public void setIsError(String isError) {
        this.isError = isError;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
