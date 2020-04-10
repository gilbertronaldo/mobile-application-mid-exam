package com.apps.spendingtrack;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.UserViewHolder> {

    private ArrayList<ItemProperty> dataList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public ItemAdapter(ArrayList<ItemProperty> dataList) {
        this.dataList = dataList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_list_item, parent, false);
        return new UserViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.tv_listNama.setText(dataList.get(position).getNama());
        holder.tv_listNominal.setText(dataList.get(position).getNominal());
        holder.tv_listTanggal.setText(dataList.get(position).getTanggal());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_listNama, tv_listNominal, tv_listTanggal;

        public UserViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            tv_listNama = (TextView) itemView.findViewById(R.id.tv_listNama);
            tv_listNominal = (TextView) itemView.findViewById(R.id.tv_listNominal);
            tv_listTanggal = (TextView) itemView.findViewById(R.id.tv_listTanggal);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}