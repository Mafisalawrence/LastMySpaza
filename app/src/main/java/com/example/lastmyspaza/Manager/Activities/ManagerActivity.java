package com.example.lastmyspaza.Manager.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lastmyspaza.Manager.Fragments.PurchaseFragment;
import com.example.lastmyspaza.Owner.Fragments.NotificationFragment;
import com.example.lastmyspaza.Owner.Fragments.StatisticsFragment;
import com.example.lastmyspaza.Manager.Fragments.InventoryFragment;
import com.example.lastmyspaza.Manager.Fragments.SettingsFragment;
import com.example.lastmyspaza.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ManagerActivity extends AppCompatActivity
{
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_sales:
                    loadFragment(new PurchaseFragment());
                    return true;
                case R.id.navigation_inventory:
                    loadFragment(new InventoryFragment());
                    return true;
                case R.id.navigation_statistics:
                    loadFragment(new StatisticsFragment());
                    return true;
                case R.id.navigation_notifications:
                    loadFragment(new NotificationFragment());
                    return true;
                case R.id.navigation_settings:
                    loadFragment(new SettingsFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new PurchaseFragment());
    }
    private void loadFragment(Fragment fragment){
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
