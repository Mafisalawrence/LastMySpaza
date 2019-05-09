package com.example.lastmyspaza.Manager.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lastmyspaza.Manager.ManagerActivity;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Fragments.Registration.PersonalDetails;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Models.ProductDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment {


    private EditText productName;
    private EditText productCategory;
    private EditText productPrice;
    private EditText productQuantity;
    private Button addProduct;
    private Authentication authentication;

    public AddProductFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_add_product, container, false);
      initializeLayoutComponents(view);

      addProduct.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              ProductDetails productDetails = addProductDetailsToModel();
              addProductDetails(productDetails);
          }
      });
      return view;
    }
    private void addProductDetails(ProductDetails productDetails)
    {
        DatabaseIteration databaseIteration = new DatabaseIteration(getContext());
        authentication = new Authentication(getContext());
        String uid = authentication.GetCurrentUser().getUid();
        databaseIteration.addProductDetailsToDb(uid, productDetails)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Do what you need to do
                            Intent i = new Intent(getActivity(), ManagerActivity.class);
                            startActivity(i);
                        } else {
                            Log.d(TAG, task.getException().getMessage());
                        }

                    }
                });
    }
    private ProductDetails addProductDetailsToModel(){

        ProductDetails productDetails= new ProductDetails();
        productDetails.setProductName(productName.getText().toString());
        productDetails.setProductCategory(productCategory.getText().toString());
        productDetails.setPrice(Double.parseDouble(productPrice.getText().toString()));
        productDetails.setQuantity(Integer.parseInt(productQuantity.getText().toString()));

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        productDetails.setDateAdded(formatter.format(calendar.getTime()));
         return productDetails;
    }

    private void initializeLayoutComponents(View view){
        productName = view.findViewById(R.id.product_name);
        productCategory = view.findViewById(R.id.product_category);
        productPrice = view.findViewById(R.id.product_price);
        productQuantity = view.findViewById(R.id.product_quantity);
        addProduct = view.findViewById(R.id.add_product_button);
    }
}
