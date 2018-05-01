package com.example.intel.navigationwithfragment.Expandable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.intel.navigationwithfragment.R;

import java.util.ArrayList;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context mContext;
    ArrayList<ExpContact> mContactList;
    OnClickListener onClickListener;

    public ExpandAdapter(Context mContext, ArrayList<ExpContact> mContactList) {
        this.mContext = mContext;
        this.mContactList = mContactList;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onParentClick(int groupPosition);
        void onChildClick(int groupPosition,int childPosition);
    }

    @Override
    public int getGroupCount() {
        return mContactList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mContactList.get(groupPosition).getHistoryList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mContactList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mContactList.get(groupPosition).getHistoryList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_parent_item, null);
        }

        TextView tvId= convertView.findViewById(R.id.contact_id);
        TextView tvName= convertView.findViewById(R.id.contact_name);
        TextView tvMobile= convertView.findViewById(R.id.contact_mobile);

        ExpContact parentItem=mContactList.get(groupPosition);

        tvId.setText(parentItem.getId());
        tvName.setText(parentItem.getName());
        tvMobile.setText(parentItem.getMobile());

        tvId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener!=null)
                    onClickListener.onParentClick(groupPosition);
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_child_item, null);
        }

        TextView tvDate= convertView.findViewById(R.id.call_date);
        TextView tvTime= convertView.findViewById(R.id.call_time);

        ExpCallHistory childItem=mContactList.get(groupPosition).getHistoryList().get(childPosition);

        tvDate.setText(childItem.getDate());
        tvTime.setText(childItem.getTime());

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener!=null)
                    onClickListener.onChildClick(groupPosition,childPosition);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
