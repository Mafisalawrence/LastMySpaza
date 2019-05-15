package com.example.lastmyspaza.Manager.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Models.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Product selectedProduct = (Product) getIntent().getSerializableExtra("selectedProduct");
        Toast.makeText(ProductDetailsActivity.this,selectedProduct.getProductName(),Toast.LENGTH_LONG).show();
    }
}
