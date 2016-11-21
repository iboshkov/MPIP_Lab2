package com.example.user.lab2_1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements GradoviAdapter.GradoviListener {
    ListView listView;
    GradoviAdapter adapter;
    ArrayList<Grad> gradovi;

    public MainActivityFragment() {
        adapter = new GradoviAdapter(this.getActivity());
    }

    public GradoviAdapter getAdapter() {
        return adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView) v.findViewById(R.id.lvGradovi);
        adapter.setContext(getActivity());
        listView.setAdapter(adapter);
        if (gradovi != null) {
            adapter.setData(gradovi);
        }
        adapter.addListener(this);


        return v;
    }

    @Override
    public void gradoviLoaded(ArrayList<Grad> data) {
        gradovi = data;

    }

    @Override
    public void gradClicked(Grad grad) {
    }

    @Override
    public void gradoviError() {

    }
}
