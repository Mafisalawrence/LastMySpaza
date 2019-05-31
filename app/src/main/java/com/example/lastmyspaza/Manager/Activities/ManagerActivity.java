package com.example.lastmyspaza.Manager.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lastmyspaza.Owner.Fragments.NotificationFragment;
import com.example.lastmyspaza.Owner.Fragments.StatisticsFragment;
import com.example.lastmyspaza.Manager.Fragments.InventoryFragment;
import com.example.lastmyspaza.Manager.Fragments.SettingsFragment;
import com.example.lastmyspaza.R;

public class ManagerActivity extends AppCompatActivity
{

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
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

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new InventoryFragment());
    }
    private void loadFragment(Fragment fragment){
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
