package com.example.lastmyspaza.Manager.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lastmyspaza.Manager.Activities.AddProductActivity;
import com.example.lastmyspaza.R;


public class EmptyStateInventoryFragment extends Fragment {


    public EmptyStateInventoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_empty_state_inventory, container, false);

        Button addProduct = view.findViewById(R.id.add_product_button);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getActivity(), AddProductActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
