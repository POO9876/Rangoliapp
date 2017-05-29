package com.appswager.rangoliapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View viewroot = inflater.inflate(R.layout.fragment_one, container, false);

     viewroot.findViewById(R.id.enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We normally won't show the welcome slider again in real app
                // but this is for testing


                startActivity(new Intent(getActivity(), Upload2Activity.class));

              /*  PrefManager prefManager = new PrefManager(getActivity());

                // make first time launch TRUE
                prefManager.setFirstTimeLaunch(true);
                startActivity(new Intent(getActivity(), WelcomeActivity.class));*/

            }
        });

        return viewroot;
    }

}
