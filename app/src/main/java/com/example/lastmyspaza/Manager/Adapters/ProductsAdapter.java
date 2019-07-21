package com.example.lastmyspaza.Manager.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.shared.Interfaces.OnItemClickListener;
import com.example.lastmyspaza.shared.Models.Product;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private ArrayList<Product> products;
    private OnItemClickListener onItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, productQuantity, productPrice;
        public Button sellProduct;

        public MyViewHolder(View view) {
            super(view);
            productName = view.findViewById(R.id.product_name);
            productQuantity =  view.findViewById(R.id.product_quantity);
            productPrice =  view.findViewById(R.id.product_price);
            sellProduct =  view.findViewById(R.id.sell_product);
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
        holder.productPrice.setText(product.getPrice().toString());
        holder.productQuantity.setText(product.getQuantity().toString());

        holder.bind(products.get(position), onItemClickListener);
    }


    @Override
    public int getItemCount() {
        return products.size();
    }
}