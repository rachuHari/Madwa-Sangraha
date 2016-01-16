package com.madwa.sangraha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madwa.sangraha.R;
import com.madwa.sangraha.model.Mantra;

import java.util.ArrayList;

/**
 * Created by pace on 17/12/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Mantra> arrayList;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<Mantra> list) {
        this.arrayList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_adapter, parent, false);


        return new RecyclerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
        holder.tvid.setText(arrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private static class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvid;

        public RecyclerItemViewHolder(View view) {
            super(view);
            tvid = (TextView) view.findViewById(R.id.tvid);
        }
    }
}
