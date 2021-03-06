package com.madwa.sangraha.Fragments.Home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.madwa.sangraha.R;
import com.madwa.sangraha.Utils.ApiUrls;
import com.madwa.sangraha.Utils.Utils;
import com.madwa.sangraha.adapter.RecyclerViewAdapter;
import com.madwa.sangraha.model.Mantra;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by pace on 17/12/15.
 */
public class MusicFragment extends Fragment {

    private LinearLayoutManager mLayoutMgr;
    private Context activity;
    private SharedPreferences preferences;
    private LinearLayout layoutProgress;
    private RecyclerView mRecyclerView;
    private ArrayList<Mantra> list;
    private int i = 0;

    public static Fragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        // Bundle args = new Bundle();
        //args.putInt(ARG_POSITION, position);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_fragment_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewmusic);
        setupRecyclerView();
        return view;
    }


    private void setupRecyclerView() {
        mLayoutMgr = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutMgr);
        getList();
    }

    private void getList() {

        try {
            final AsyncHttpClient client = new AsyncHttpClient();

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("language_id", 1);
            jsonParams.put("category_id", 1);
            StringEntity entity = new StringEntity(jsonParams.toString());
            Log.e("Music params", jsonParams.toString());
            client.post(activity, ApiUrls.madwa_home_url, entity, "application/json", new AsyncHttpResponseHandler() {

                @Override
                public void onStart() {
                    super.onStart();

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    // If the response is JSONObject instead of expected JSONArray
                    if (statusCode == 200) {
                        try {
                            list = new ArrayList<Mantra>();
                            String str = new String(responseBody, "UTF-8");
                            JSONArray responseArray = new JSONArray(str);
                            Log.e("Music response", str);
                            if (responseArray.length() > 0) {
                                for (int i = 0; i < responseArray.length(); i++) {
                                    JSONObject mantraObj = responseArray.getJSONObject(i);
                                    Mantra mantra = new Mantra();
                                    mantra.setId(mantraObj.getInt("id"));
                                    mantra.setCat_id(mantraObj.getInt("category_id"));
                                    mantra.setLang_id(mantraObj.getInt("language_id"));
                                    mantra.setTitle(mantraObj.getString("title"));
                                    mantra.setMantra_file_url(mantraObj.getString("mantra_file_url"));

                                    list.add(mantra);
                                }

                                RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
                                mRecyclerView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.e("onFailure", statusCode + "\n");
                    String str = null;
                    JSONObject errors = null;

                    if (statusCode == 0) {
                        Utils.showToast(activity, "Connection Timed Out");
                        return;
                    }
                    try {
                        str = new String(responseBody, "UTF-8");
                        errors = new JSONObject(str);
                        if (statusCode == 401) {

                            try {
                                Utils.showToast(activity, errors.getString("errors"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                //Utils.showToast(activity, getString(R.string.network_prolem));
                            }

                        } else if (statusCode == 400) {
                            Utils.showToast(activity, errors.getString("errors"));
                        } else if (statusCode == 0) {
                            //Utils.showToast(activity, getString(R.string.network_prolem));
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
