package com.example.application_hw;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment {

    private Button likeBtn, unlikeBtn;

    public FragmentMain() {
        // Required empty public constructor
    }

    public static FragmentMain newInstance() {
        FragmentMain fragment = new FragmentMain();

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fragment_main, container, false);

        View view = inflater.inflate(R.layout.fragment_fragment_main, container, false);


        likeBtn = view.findViewById(R.id.likeButton);
        unlikeBtn = view.findViewById(R.id.unlikeButton);

        likeBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
               Log.d("FragmentLike", "Click Like button");
               Bundle bundle = new Bundle();
               bundle.putString("button", "like");

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment, FragmentLike.newInstance(bundle)).commit();
            }
        });
        unlikeBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("FragmentUnlike", "Click unlike button");
                Bundle bundle = new Bundle();
                bundle.putString("button", "unlike");

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment, FragmentLike.newInstance(bundle)).commit();
            }
        });




        return view;
    }




}
