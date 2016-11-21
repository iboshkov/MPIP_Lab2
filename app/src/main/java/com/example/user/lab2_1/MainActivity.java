package com.example.user.lab2_1;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GradoviAdapter.GradoviListener, GradFragment.OnListFragmentInteractionListener {
    MainActivityFragment mainFragment;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        App.getInstance().mRequestQueue = Volley.newRequestQueue(this);
        ViewGroup vg = (ViewGroup) findViewById(R.id.fragmentContainer);

        mainFragment = new MainActivityFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContainer, mainFragment);
        transaction.commit();
        mainFragment.getAdapter().addListener(this);

        updateData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void updateData() {
        progress = ProgressDialog.show(this, "Освежување податоци", "Почекајте додека се превземаат нови податоци...", true);
        mainFragment.getAdapter().queueUpdate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // Vo glavniot fragment sme, pravi update
            if (mainFragment.isVisible()) {
                updateData();
            }
            else {   // Vo glavniot fragment sme, pravi update

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Предупредување");
                builder.setMessage("При освежување на податоците ќе бидете вратени на " +
                        "претходниот екран, кликнете откажи ако не сакате да се вратите назад.");
                builder.setPositiveButton("Продолжи", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getSupportFragmentManager().popBackStack(); // odi nazad na main
                        updateData();
                    }
                });
                builder.setNegativeButton("Откажи", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                builder.show();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void gradoviLoaded(ArrayList<Grad> data) {
        progress.hide();
    }

    @Override
    public void gradClicked(Grad grad) {
        ViewGroup vg = (ViewGroup) findViewById(R.id.fragmentContainer);

        Fragment newFragment = GradFragment.newInstance(grad);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragmentContainer, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void gradoviError() {
        progress.hide();
    }

    @Override
    public void onListFragmentInteraction(GradItem item) {

    }
}
