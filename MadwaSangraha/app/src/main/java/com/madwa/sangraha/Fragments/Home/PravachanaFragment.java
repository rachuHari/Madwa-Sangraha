package com.madwa.sangraha.Fragments.Home;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.madwa.sangraha.R;

import java.util.ArrayList;

/**
 * Created by pace on 17/12/15.
 */
public class PravachanaFragment extends Fragment {

    private LinearLayoutManager mLayoutMgr;
    private Context activity;
    private SharedPreferences preferences;
    private LinearLayout layoutProgress;
    private RecyclerView mRecyclerView;
    private ArrayList<String> list ;

private  int i=0;
    public static PravachanaFragment newInstance() {

        PravachanaFragment fragment = new PravachanaFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pravachana_fragment_layout, container, false);
        activity=getActivity();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewpra);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        mLayoutMgr=new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutMgr);
        list=new ArrayList<String>();

        for (i=0;i<8;i++){

            list.add("name"+i);

        }
        //RecyclerViewAdapter adapter=new RecyclerViewAdapter(list);
        //mRecyclerView.setAdapter(adapter);

    }
    
}
