package com.example.lastmyspaza.Manager.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lastmyspaza.Manager.Fragments.AddSelectionFragment;
import com.example.lastmyspaza.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddProductActivity extends AppCompatActivity {

//    private TextInputLayout productName,productCategory,productPrice,productQuantity;
//    private SwitchMaterial switchMaterial;
//    private MaterialCardView productQuantityWrapper;
//    private ImageView addAvailable,subtractAvailable,addWeight,subtractWeight;
//    private Authentication authentication;
//    private String storeId, managerUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Inflate the layout for this fragment
       // initializeLayoutComponents();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new AddSelectionFragment())
                .commit();

//        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    productQuantityWrapper.setVisibility(View.VISIBLE);
//                }else{
//                    productQuantityWrapper.setVisibility(View.GONE);
//                }
//            }
//        });
//        SharedPreferences sharedPreferences = getSharedPreferences("manager",MODE_PRIVATE);
//        storeId = sharedPreferences.getString("managerStore",null);
//        managerUid =  sharedPreferences.getString("managerUid",null);

//        addProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Product product = addProductDetailsToModel();
//                addProductDetails(product);
//            }
//        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_toolbar_button, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,item.getItemId(),Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

//    private void addProductDetails(Product product)
//    {
//        DatabaseIteration databaseIteration = new DatabaseIteration(AddProductActivity.this);
//        authentication = new Authentication(AddProductActivity.this);
//        databaseIteration.addProductDetailsToDb(storeId, product)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            //Do what you need to do
//                            Intent i = new Intent(AddProductActivity.this, ManagerActivity.class);
//                            startActivity(i);
//                        } else {
//                            Log.d(TAG, task.getException().getMessage());
//                        }
//
//                    }
//                });
//    }
//    private Product addProductDetailsToModel(){
//
//        Product productDetails= new Product();
//        productDetails.setProductName(productName.getEditText().getText().toString());
//        productDetails.setProductCategory(productCategory.getEditText().getText().toString());
//        productDetails.setPrice(Double.parseDouble(productPrice.getEditText().toString()));
//        productDetails.setQuantity(Integer.parseInt(productQuantity.getEditText().toString()));
//
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//        productDetails.setDateAdded(formatter.format(calendar.getTime()));
//        return productDetails;
//    }

    private void initializeLayoutComponents(){
//        productName =  findViewById(R.id.product_name);
//        productCategory =  findViewById(R.id.product_category);
//        productPrice =  findViewById(R.id.product_price);
//        switchMaterial = findViewById(R.id.add_weight_switch);
//        productQuantityWrapper = findViewById(R.id.product_quantity_wrapper);
//        addAvailable = findViewById(R.id.add_available);
//        subtractAvailable = findViewById(R.id.subtract_available);
//        addWeight = findViewById(R.id.add_weight);
//        subtractWeight = findViewById(R.id.subtract_weight);

       // productQuantity =  findViewById(R.id.product_quantity);
     //   addProduct =  findViewById(R.id.add_product_button);
    }
}
