package com.example.lastmyspaza.Manager.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastmyspaza.Admin.Fragments.UsersAdapter;
import com.example.lastmyspaza.Manager.Adapters.ProductsAdapter;
import com.example.lastmyspaza.Manager.AddProductActivity;
import com.example.lastmyspaza.Manager.ProductDetailsActivity;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Interfaces.OnGetDataListener;
import com.example.lastmyspaza.Shared.Interfaces.OnItemClickListener;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFragment extends Fragment {

    private ProductsAdapter  productsAdapter;
   private ArrayList<Product> products = new ArrayList<>();
    public InventoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);
       RecyclerView recyclerView = rootView.findViewById(R.id.products_recyclerView);

        productsAdapter = new ProductsAdapter(products, new OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent  = new Intent(getActivity(), ProductDetailsActivity.class);
                intent.putExtra("selectedProduct",product);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productsAdapter);

        Button addProduct = rootView.findViewById(R.id.add_product_button);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getActivity(), AddProductActivity.class);
                startActivity(intent);
            }
        });
       return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getProductAllProducts();
    }

    public void getProductAllProducts() {
        DatabaseIteration databaseIteration = new DatabaseIteration(getContext());
        String uid = new Authentication(getContext()).GetCurrentUser().getUid();
        databaseIteration.getAllProducts("products", uid, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot data) {
                Product product = data.getValue(Product.class);
                products.add(product);
                productsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.d("get all products error", databaseError.getDetails());
                Log.d("get all products error", databaseError.getMessage());
            }

            @Override
            public void onStart() {

            }
        });
    }


}
