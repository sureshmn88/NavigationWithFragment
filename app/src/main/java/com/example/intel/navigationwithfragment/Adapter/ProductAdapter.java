package com.example.intel.navigationwithfragment.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intel.navigationwithfragment.R;
import com.example.intel.navigationwithfragment.WebService.StoreDetail;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    Context mContext;
    List<StoreDetail> mList;

    public ProductAdapter(Context mContext, List<StoreDetail> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_item,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        StoreDetail item=mList.get(position);

        holder.nameTxt.setText(item.getStorename());
        holder.countryTxt.setText(item.getCountry());
        holder.stateTxt.setText(item.getState());
        holder.cityTxt.setText(item.getCity());

        if (!item.getImage().isEmpty()) {
            String imageUrl = "http://andydevelopment.com/CurbCart/" + item.getImage();
            Glide.with(mContext).load(imageUrl).into(holder.profileimage);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profileimage;
        TextView nameTxt,countryTxt,cityTxt,stateTxt;

        public MyViewHolder(View itemView) {
            super(itemView);
            profileimage=(CircleImageView)itemView.findViewById(R.id.image_vie);
            nameTxt=(TextView)itemView.findViewById(R.id.name_txt);
            countryTxt=(TextView)itemView.findViewById(R.id.country_txt);
            cityTxt=(TextView)itemView.findViewById(R.id.city_txt);
            stateTxt=(TextView)itemView.findViewById(R.id.state_txt);
        }
    }
}
