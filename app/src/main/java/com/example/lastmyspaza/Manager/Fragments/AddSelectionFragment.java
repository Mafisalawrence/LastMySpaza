package com.example.lastmyspaza.Manager.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lastmyspaza.Manager.Activities.AddProductActivity;
import com.example.lastmyspaza.R;
import com.google.android.material.card.MaterialCardView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
/**
 * A simple {@link Fragment} subclass.
 */
public class AddSelectionFragment extends Fragment {


    private MaterialCardView addProduct;
    private MaterialCardView addCategory;
    public AddSelectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_add_selection, container, false);
        addProduct = view.findViewById(R.id.add_product);
        addCategory = view.findViewById(R.id.add_category);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new AddProductFragment())
                        .commit();
            }
        });
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new AddCategoryFragment())
                        .commit();
            }
        });
       return view;
    }

}
