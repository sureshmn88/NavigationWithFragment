package com.example.intel.navigationwithfragment.Expandable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.intel.navigationwithfragment.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExpandActivity extends AppCompatActivity {

    ExpandableListView mExpandableListView;
    ArrayList<ExpContact> mContactList;
    ExpandAdapter mExpandAdapter;

    String sampleJSON = "{\"callDetails\":[{\"id\":\"1\",\"name\":\"karthi\",\"number\":\"123456789\",\"history\":[{\"date\":\"28-04-2018\",\"time\":\"10:00:00\"},{\"date\":\"28-04-2018\",\"time\":\"10:00:00\"},{\"date\":\"28-04-2018\",\"time\":\"10:00:00\"}]},{\"id\":\"2\",\"name\":\"Priya\",\"number\":\"123456789\",\"history\":[{\"date\":\"28-04-2018\",\"time\":\"10:00:00\"},{\"date\":\"28-04-2018\",\"time\":\"10:00:00\"}]},{\"id\":\"3\",\"name\":\"Jeeva\",\"number\":\"123456789\",\"history\":[{\"date\":\"28-04-2018\",\"time\":\"10:00:00\"},{\"date\":\"28-04-2018\",\"time\":\"10:00:00\"},{\"date\":\"28-04-2018\",\"time\":\"10:00:00\"}]}]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);

        mExpandableListView = findViewById(R.id.expand_listview);

        prepareDetails();
    }

    void prepareDetails() {

        mContactList = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(sampleJSON);

            JSONArray parentArray = jsonObject.getJSONArray("callDetails");
            for (int i = 0; i < parentArray.length(); i++) {

                // Parent
                JSONObject jObj = parentArray.getJSONObject(i);
                ExpContact parent = new ExpContact();
                parent.setId(jObj.getString("id"));
                parent.setName(jObj.getString("name"));
                parent.setMobile(jObj.getString("number"));

                // Child
                ArrayList<ExpCallHistory> historyList = new ArrayList<>();
                JSONArray childArray = jObj.getJSONArray("history");

                for (int j = 0; j < childArray.length(); j++) {

                    JSONObject cObj = childArray.getJSONObject(j);
                    ExpCallHistory childItem = new ExpCallHistory();
                    childItem.setDate(cObj.getString("date"));
                    childItem.setTime(cObj.getString("time"));
                    historyList.add(childItem);

                }

                parent.setHistoryList(historyList);
                mContactList.add(parent);

            }

            mExpandAdapter = new ExpandAdapter(this, mContactList);
            mExpandableListView.setAdapter(mExpandAdapter);

            mExpandAdapter.setOnClickListener(new ExpandAdapter.OnClickListener() {
                @Override
                public void onParentClick(int groupPosition) {
                    Toast.makeText(ExpandActivity.this,mContactList.get(groupPosition).getName(),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onChildClick(int groupPosition, int childPosition) {
                    Toast.makeText(ExpandActivity.this,mContactList.get(groupPosition).getHistoryList().get(childPosition).getDate(),Toast.LENGTH_SHORT).show();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
