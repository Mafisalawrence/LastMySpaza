package com.example.lastmyspaza.Manager.Fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lastmyspaza.R;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseFragment extends Fragment {

    private Button goToProducts;
    public PurchaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_purchase, container, false);
        goToProducts =  view.findViewById(R.id.go_to_products);
        goToProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNavigationMenuView = getActivity().findViewById(R.id.navigation);
                bottomNavigationMenuView.setSelectedItemId(R.id.navigation_inventory);
            }
        });
        return view;
    }

}
