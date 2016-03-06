package com.madwa.sangraha.Activiteis;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.madwa.sangraha.R;
import com.madwa.sangraha.adapter.PeopleAdapter;
import com.madwa.sangraha.model.People;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    AutoCompleteTextView txtSearch;
    List<People> mList;
    PeopleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activitymain);
        mList = retrievePeople();
        txtSearch = (AutoCompleteTextView) findViewById(R.id.autotext);
        txtSearch.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        txtSearch.setOnItemSelectedListener(this);
        txtSearch.setOnItemClickListener(this);

        adapter = new PeopleAdapter(this, R.layout.search_activitymain, R.id.lbl_name, mList);
        txtSearch.setAdapter(adapter);


    }

    private List<People> retrievePeople() {
        List<People> list = new ArrayList<People>();
        list.add(new People("James", 1));
        list.add(new People("Jason", 2));
        list.add(new People("Ethan", 3));
        list.add(new People("Sherlock", 4));
        list.add(new People("David", 5));
        list.add(new People("Bryan", 6));
        list.add(new People("Arjen", 7));
        list.add(new People("Van", 8));
        list.add(new People("Zinedine", 9));
        list.add(new People("Luis", 10));
        list.add(new People("John", 11));
        list.add(new People("martianJason", 12));

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int arg2, long id) {
        Log.d("AutocompleteContacts", "Position:" + arg2 + " Month:" + arg0.getItemAtPosition(arg2));
        Log.d("Autocomplet", mList.get(arg2).getName());
        Log.d("Autocomplet", "hai");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Autocomplet", mList.get(position).getName());
        Log.d("Autocomplet", "hai");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d("Autocomplet", mList + "");
        Log.d("Autocomplet", "hai");
    }
}
