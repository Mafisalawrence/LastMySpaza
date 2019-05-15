package com.example.lastmyspaza.Manager.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Models.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView productName;
    private TextView productCategory;
    private TextView productPrice;
    private TextView productQuantity;
    private Button deleteProduct;
    private DatabaseIteration databaseIteration;
    private Authentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        final Product selectedProduct = (Product) getIntent().getSerializableExtra("selectedProduct");
        initializeLayoutComponents();
        setProductDetails(selectedProduct);

        databaseIteration =  new DatabaseIteration(ProductDetailsActivity.this);
        authentication = new Authentication(ProductDetailsActivity.this);
        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = authentication.GetCurrentUser().toString();
                deleteSelectedProduct(uid,selectedProduct);
            }
        });
        Toast.makeText(ProductDetailsActivity.this,selectedProduct.getProductName(),Toast.LENGTH_LONG).show();
    }
    private void deleteSelectedProduct(String uid, Product product)
    {
        databaseIteration.deleteProductFromDb(uid,product)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ProductDetailsActivity.this,"product deleted", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(ProductDetailsActivity.this,"unable to delete product", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    //Todo update the number of available product in db
    private void updateQuatity(){

    }

    private void initializeLayoutComponents(){
        productName =  findViewById(R.id.product_name);
        productCategory =  findViewById(R.id.product_category);
        productPrice =  findViewById(R.id.product_price);
        productQuantity =  findViewById(R.id.product_quantity);
        deleteProduct = findViewById(R.id.delete_product_button);
    }
    private void setProductDetails(Product productDetails)
    {
        productName.setText(productDetails.getProductName());
        productCategory.setText(productDetails.getProductCategory());
        productPrice.setText(productDetails.getPrice().toString());
        productQuantity.setText(productDetails.getQuantity().toString());
    }
}
