package com.example.intel.navigationwithfragment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intel.navigationwithfragment.Pojos.PersonalDetails;
import com.example.intel.navigationwithfragment.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {
    private Activity activity1;
    Context context;
    ArrayList<PersonalDetails> mList;
    OnClickListener onClickListener;
    RecyclerView recyclerView;
    private static LayoutInflater inflater = null;

    public interface OnClickListener {
        void onLayoutClick(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }



    public DetailAdapter(Context context, ArrayList<PersonalDetails> mList, RecyclerView mRecyclerView) {
        this.context = context;
        this.mList = mList;
        this.recyclerView=mRecyclerView;
       inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listdatatoadapter,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {

            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            params.setFullSpan(false);
            holder.itemView.setLayoutParams(params);
            PersonalDetails item = mList.get(position);
            holder.countryTxt.setText(item.getCity());
            holder.stateTxt.setText(item.getState());

            MyViewHolder holder1 = (MyViewHolder) holder;

            /*holder1.countryTxt.setText(item.getCity());
            holder1.stateTxt.setText(item.getState());*/
            holder1.cityTxt.setText(item.getEmail());


            holder1.nameTxt.setText(item.getName());

            if (item.getType().equals("1")) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DP(80));
                holder1.imagelayout.setLayoutParams(layoutParams);
            }

            if (item.getType().equals("2")) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DP(160));
                holder1.imagelayout.setLayoutParams(layoutParams);
            }

            if (item.getType().equals("3")) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DP(240));
                holder1.imagelayout.setLayoutParams(layoutParams);
            }
            holder1.setIsRecyclable(false);
            /*
            String attachmentBaseStr=item.getImage(); // pojo.getImage();
            byte[] imageByteArray = Base64.decode(attachmentBaseStr, Base64.DEFAULT);
            Glide.with(context)
                    .load(imageByteArray)
                    .asBitmap()
                    .into(holder.profileimage); //imageview name        }
            */

       /* String mobileNo="";
        for (int i=0;i<item.getPhoneno().size();i++){
            mobileNo=mobileNo+item.getPhoneno().get(i)+" - ";
        }
        holder.mobile.setText(mobileNo);*/
        }

    }

    @Override
    public int getItemCount() {

        return mList == null ? 0 : mList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileimage;
        TextView nameTxt,countryTxt,cityTxt,stateTxt;
        RelativeLayout imagelayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            profileimage=(CircleImageView)itemView.findViewById(R.id.image_vie);
            nameTxt=(TextView)itemView.findViewById(R.id.name_txt);
            countryTxt=(TextView)itemView.findViewById(R.id.country_txt);
            cityTxt=(TextView)itemView.findViewById(R.id.city_txt);
            stateTxt=(TextView)itemView.findViewById(R.id.state_txt);
            imagelayout=(RelativeLayout) itemView.findViewById(R.id.imagelayout1);
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
    public int DP(int dp)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        int dp1 = (int) (dp * scale + 0.5f);

        return dp1;
    }
}
