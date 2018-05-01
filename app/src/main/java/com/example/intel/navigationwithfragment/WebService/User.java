package com.example.intel.navigationwithfragment.WebService;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    private String name;
    @SerializedName("pass")
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
