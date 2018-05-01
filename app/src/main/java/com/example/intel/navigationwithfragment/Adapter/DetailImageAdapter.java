package com.example.intel.navigationwithfragment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intel.navigationwithfragment.Pojos.PersonalDetails;
import com.example.intel.navigationwithfragment.Pojos.PersonalImage;
import com.example.intel.navigationwithfragment.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailImageAdapter extends  RecyclerView.Adapter<DetailImageAdapter.MyViewHolder> {

    Context context;
    ArrayList<PersonalImage> mList;
    DetailAdapter.OnClickListener onClickListener;

    public interface OnClickListener {
        void onLayoutClick(int position);
    }

    public void setOnClickListener(DetailAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    public DetailImageAdapter(Context context, ArrayList<PersonalImage> mList) {
        this.context = context;
        this.mList = mList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listdatatoadapter,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PersonalImage item = mList.get(position);
        holder.nameTxt.setText(item.getName());
        holder.countryTxt.setText(item.getCountry());
        holder.stateTxt.setText(item.getState());
        holder.cityTxt.setText(item.getCity());
        //USE HERE GLIDE
        if (!item.getImage().isEmpty()) {
            String imageUrl = "http://andydevelopment.com/CurbCart/" + item.getImage();
            Glide.with(context).load(imageUrl).into(holder.profileimage);
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
            //mLayout=(LinearLayout)itemView.findViewById(R.id.layout_list);




          /*  mLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(onClickListener!=null)
                        onClickListener.onLayoutClick(getAdapterPosition());

                    return false;
                }
            });*/
        }
    }
}
