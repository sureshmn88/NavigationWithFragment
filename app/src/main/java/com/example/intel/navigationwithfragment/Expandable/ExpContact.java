package com.example.intel.navigationwithfragment.Expandable;

import java.util.ArrayList;

public class ExpContact {

    String id;
    String name;
    String mobile;
    ArrayList<ExpCallHistory> historyList;

    public ExpContact() {
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public ArrayList<ExpCallHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(ArrayList<ExpCallHistory> historyList) {
        this.historyList = historyList;
    }
}
