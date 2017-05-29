package com.appswager.rangoliapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment implements View.OnClickListener {


    public FragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View  viewroot = inflater.inflate(R.layout.fragment_three, container, false);

        Button b = (Button)viewroot.findViewById(R.id.gotit);
        b.setOnClickListener(this);
        return viewroot;
    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(getActivity(),Upload2Activity.class);
        startActivity(i);
    }
}
