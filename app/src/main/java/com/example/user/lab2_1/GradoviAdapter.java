package com.example.user.lab2_1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 11/21/16.
 */

public class GradoviAdapter extends BaseAdapter implements  Response.Listener<JSONObject> {
    @Override
    public void onResponse(JSONObject response) {
        try {
            mGradovi = new ArrayList<>();
            JSONArray arr = response.getJSONArray("geonames");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                mGradovi.add(new Grad(obj));
            }

            for (GradoviListener listener : listeners) {
                listener.gradoviError();
            }

        } catch (JSONException e) {
            for (GradoviListener listener : listeners) {
                listener.gradoviError();
            }
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    interface GradoviListener {
        void gradoviLoaded(ArrayList<Grad> data);
        void gradClicked(Grad grad);
        void gradoviError();
    }

    private ArrayList<GradoviListener> listeners;

    public void setData(ArrayList<Grad> data) {
        mGradovi = data;
        notifyDataSetChanged();
    }

    ArrayList<Grad> mGradovi;
    private Context mContext;

    public void addListener(GradoviListener listener) {
        this.listeners.add(listener);
    }

    public GradoviAdapter(Context ctx) {
        mGradovi = new ArrayList<>();
        setContext(ctx);
        listeners = new ArrayList<>();
    }

    public void queueUpdate() {
        String url = "http://api.geonames.org/citiesJSON?north=42.3376462&south=40.9201646&east=22.5389436&west=20.89274&lang=en&username=finki";
        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.GET, url, null, this, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        for (GradoviListener listener : listeners) {
                            listener.gradoviError();
                        }
                    }
                });
        RequestQueue queue = App.getInstance().mRequestQueue;

        queue.add(req);
    }

    @Override
    public int getCount() {
        return mGradovi.size();
    }

    @Override
    public Object getItem(int position) {
        return mGradovi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Grad grad = (Grad) getItem(position);
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.item_grad, null);
        TextView ime = (TextView) v.findViewById(R.id.itemGradIme);
        ime.setText(grad.name);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), grad.name, Toast.LENGTH_SHORT).show();
                for (GradoviListener listener : listeners) {
                    listener.gradClicked(grad);
                }
            }
        });

        return v;
    }
}
