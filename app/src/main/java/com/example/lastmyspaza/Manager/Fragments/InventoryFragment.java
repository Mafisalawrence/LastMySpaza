package com.example.lastmyspaza.Manager.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lastmyspaza.Manager.Activities.AddProductActivity;
import com.example.lastmyspaza.Manager.Adapters.ProductsAdapter;
import com.example.lastmyspaza.Manager.Activities.ProductDetailsActivity;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.shared.Interfaces.OnGetDataListener;
import com.example.lastmyspaza.shared.Interfaces.OnItemClickListener;
import com.example.lastmyspaza.shared.Models.Product;
import com.example.lastmyspaza.shared.Models.Store;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFragment extends Fragment {

    private ProductsAdapter  productsAdapter;
    private ArrayList<Product> products = new ArrayList<>();
    private FloatingActionButton addProduct;
    private String managerStore;
    private DatabaseIteration databaseIteration;

    public InventoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);
        addProduct = rootView.findViewById(R.id.add_product_button);
        productsAdapter = new ProductsAdapter(products, new OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent  = new Intent(getActivity(), ProductDetailsActivity.class);
                intent.putExtra("selectedProduct",product);
                startActivity(intent);
            }

            @Override
            public void onStoreItemClick(Store store) {

            }
        });
//
//        EmptyRecyclerView list = rootView.findViewById(R.id.products_recyclerView);
//        list.setLayoutManager(new LinearLayoutManager(getContext()));
//        list.setEmptyView(rootView.findViewById(R.id.empty_list));
//        list.setAdapter(productsAdapter);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getActivity(), AddProductActivity.class);
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

//        databaseIteration = new DatabaseIteration(getContext());
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("manager",MODE_PRIVATE);
//        managerStore = sharedPreferences.getString("managerStore",null);
//        getProductAllProducts();
    }

    public void getProductAllProducts() {
        Toast.makeText(getContext(),managerStore,Toast.LENGTH_LONG).show();
        databaseIteration.getAllProducts("products", managerStore, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot data) {
                Product product = data.getValue(Product.class);
                products.add(product);
                productsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.d("get all products error", databaseError.toString());
            }

            @Override
            public void onStart() {

            }
        });
    }


}
