package com.example.lastmyspaza.Manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Models.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.support.constraint.Constraints.TAG;

public class AddProductActivity extends Activity {

    private EditText productName;
    private EditText productCategory;
    private EditText productPrice;
    private EditText productQuantity;
    private Button addProduct;
    private Authentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Inflate the layout for this fragment
        initializeLayoutComponents();

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = addProductDetailsToModel();
                addProductDetails(product);
            }
        });
    }

    private void addProductDetails(Product product)
    {
        DatabaseIteration databaseIteration = new DatabaseIteration(AddProductActivity.this);
        authentication = new Authentication(AddProductActivity.this);
        String uid = authentication.GetCurrentUser().getUid();
        databaseIteration.addProductDetailsToDb(uid, product)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Do what you need to do
                            Intent i = new Intent(AddProductActivity.this, ManagerActivity.class);
                            startActivity(i);
                        } else {
                            Log.d(TAG, task.getException().getMessage());
                        }

                    }
                });
    }
    private Product addProductDetailsToModel(){

        Product productDetails= new Product();
        productDetails.setProductName(productName.getText().toString());
        productDetails.setProductCategory(productCategory.getText().toString());
        productDetails.setPrice(Double.parseDouble(productPrice.getText().toString()));
        productDetails.setQuantity(Integer.parseInt(productQuantity.getText().toString()));

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        productDetails.setDateAdded(formatter.format(calendar.getTime()));
        return productDetails;
    }

    private void initializeLayoutComponents(){
        productName =  findViewById(R.id.product_name);
        productCategory =  findViewById(R.id.product_category);
        productPrice =  findViewById(R.id.product_price);
        productQuantity =  findViewById(R.id.product_quantity);
        addProduct =  findViewById(R.id.add_product_button);
    }
}
