package com.example.lastmyspaza.Manager.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Interfaces.OnItemClickListener;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private ArrayList<Product> products;
    private OnItemClickListener onItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, productCategory, productQuantity, productPrice, productDate;
        public Button viewProduct;

        public MyViewHolder(View view) {
            super(view);
            productName = view.findViewById(R.id.product_name);
            productCategory = view.findViewById(R.id.product_category);
            productQuantity =  view.findViewById(R.id.product_quantity);
            productPrice =  view.findViewById(R.id.product_price);
            productDate =view.findViewById(R.id.product_date);
            viewProduct =  view.findViewById(R.id.view);
        }

        public void bind(final Product product, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    onItemClickListener.onItemClick(product);
                }
            });
        }
    }


    public ProductsAdapter(ArrayList<Product> products, OnItemClickListener onItemClickListener) {
        this.products = products;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productName.setText(product.getProductName());
        holder.productCategory.setText(product.getProductCategory());
        holder.productPrice.setText(product.getPrice().toString());
        holder.productDate.setText(product.getDateAdded());
        holder.productQuantity.setText(product.getQuantity().toString());

        holder.bind(products.get(position), onItemClickListener);
    }


    @Override
    public int getItemCount() {
        return products.size();
    }
}