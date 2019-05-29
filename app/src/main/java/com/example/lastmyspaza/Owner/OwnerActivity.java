package com.example.lastmyspaza.Owner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.lastmyspaza.Owner.Fragments.StoresFragment;
import com.example.lastmyspaza.Owner.Fragments.NotificationFragment;
import com.example.lastmyspaza.Owner.Fragments.StatisticsFragment;
import com.example.lastmyspaza.Manager.Fragments.SettingsFragment;
import com.example.lastmyspaza.R;

public class OwnerActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(new StatisticsFragment());
                    return true;
                case R.id.navigation_dashboard:
                    loadFragment(new StoresFragment());
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
        setContentView(R.layout.activity_admin);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new StatisticsFragment());
    }

    private void loadFragment(Fragment fragment){
                this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
