package com.mex.GalaxyChain.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.AllVarietyBean;


public class AllVarietyAdapter extends  RecyclerView.Adapter
        <AllVarietyAdapter.AllVarietyViewHolder>{
    private final Context mContext;
    private final AllVarietyBean allVarietyBean;
    private final LayoutInflater mLayoutInflater;

    public AllVarietyAdapter(Context mContext, AllVarietyBean allVarietyBean) {
         this.mContext=mContext;
         this.allVarietyBean=allVarietyBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public AllVarietyAdapter.AllVarietyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView= mLayoutInflater.inflate(R.layout.item_market_pair_view,null);
        AllVarietyViewHolder allVarietyViewHolder = new AllVarietyViewHolder(mContext,itemView);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickLisenter.onItemClick("恒普",0);


            }
        });

          return allVarietyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllVarietyAdapter.AllVarietyViewHolder holder, int position) {
        AllVarietyViewHolder allVarietyViewHolder= (AllVarietyViewHolder)holder;
        allVarietyViewHolder.variety_name.setText(allVarietyBean.getVarietyName());
    }

    @Override
    public int getItemCount() {
        return 1;
    }



    public interface OnItemClickLisenter{
        void onItemClick(String data, int position);
    }

    private   OnItemClickLisenter onItemClickLisenter;
    public void setOnItemClickLisenter(OnItemClickLisenter onItemClickLisenter) {
        this.onItemClickLisenter=onItemClickLisenter;
    }


    public class AllVarietyViewHolder extends  RecyclerView.ViewHolder {
        Context context;
        TextView variety_name;

        public AllVarietyViewHolder(Context context, View itemView) {
            super(itemView);
            this.context=context;
               variety_name=(TextView)itemView.findViewById(R.id.variety_name);

         }


    }
}
