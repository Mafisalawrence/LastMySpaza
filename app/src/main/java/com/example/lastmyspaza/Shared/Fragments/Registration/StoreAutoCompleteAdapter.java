package com.example.lastmyspaza.Shared.Fragments.Registration;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Models.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreAutoCompleteAdapter extends ArrayAdapter<Store> {

    private Context context;
    private int resourceId;
    private List<Store> items, tempItems, suggestions;
    public StoreAutoCompleteAdapter(@NonNull Context context, int resourceId, ArrayList<Store> items) {
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(resourceId, parent, false);
            }
            Store store = getItem(position);
            TextView storeName = view.findViewById(R.id.store_name);
            TextView storeLocation = view.findViewById( R.id.store_location);
            storeName.setText(store.getStoreName());
            storeLocation.setText(store.getStoreLocation());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
    @Nullable
    @Override
    public Store getItem(int position) {
        return items.get(position);
    }
    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @NonNull
    @Override
    public Filter getFilter() {
        return StoreListFilter;
    }

    private Filter StoreListFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Store store = (Store) resultValue;
            return store.getStoreName();
        }
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (Store store: tempItems) {
                    if (store.getStoreName().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                        Log.d("store addded", store.getStoreName());
                        suggestions.add(store);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<Store> tempValues = (ArrayList<Store>) filterResults.values;
            if (filterResults != null && filterResults.count > 0) {
                clear();
                for (Store store : tempValues) {
                    add(store);
                    notifyDataSetChanged();
                }
            } else {
                clear();
                notifyDataSetChanged();
            }
        }
    };
}
