package com.example.user.lab2_1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class GradFragment extends Fragment {
    Grad mGrad;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GradFragment() {
        mGrad = null;
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GradFragment newInstance(Grad grad) {
        GradFragment fragment = new GradFragment();
        Bundle args = new Bundle();
        args.putSerializable("grad", grad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mGrad = (Grad) getArguments().getSerializable("grad");
        }

        Toast.makeText(getActivity(), mGrad.name, Toast.LENGTH_SHORT).show();

        Log.i("Lifecycle", "GradFragment.onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grad, container, false);
        TextView tv = (TextView) view.findViewById(R.id.longitude);
        tv.setText(Double.toString(mGrad.lng));

        tv = (TextView) view.findViewById(R.id.latitude);
        tv.setText(Double.toString(mGrad.lat));

        tv = (TextView) view.findViewById(R.id.name);
        tv.setText(mGrad.name);

        tv = (TextView) view.findViewById(R.id.wiki);
        tv.setText(mGrad.wikipedia);

        tv = (TextView) view.findViewById(R.id.countryCode);
        tv.setText(mGrad.countryCode);

        tv = (TextView) view.findViewById(R.id.fcodeName);
        tv.setText(mGrad.fcodeName);

        tv = (TextView) view.findViewById(R.id.population);
        tv.setText(Integer.toString(mGrad.population));


        tv = (TextView) view.findViewById(R.id.fcode);
        tv.setText(mGrad.fcode);

        tv = (TextView) view.findViewById(R.id.fcl);
        tv.setText(mGrad.fcl);

        tv = (TextView) view.findViewById(R.id.geoId);
        tv.setText(Integer.toString(mGrad.geoNameId));

        Log.i("Lifecycle", "GradFragment.onCreateView");
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Lifecycle", "GradFragment.onAttach");
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Lifecycle", "GradFragment.onDetach");
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(GradItem item);
    }
}
