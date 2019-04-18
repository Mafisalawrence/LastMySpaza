package com.example.lastmyspaza.Admin;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lastmyspaza.Shared.Fragments.AccountFragment;
import com.example.lastmyspaza.Admin.Fragments.NotificationFragment;
import com.example.lastmyspaza.Admin.Fragments.StaticticsFragment;
import com.example.lastmyspaza.R;

public class AdminActivity extends AppCompatActivity
        implements StaticticsFragment.OnFragmentInteractionListener,
                    NotificationFragment.OnFragmentInteractionListener,
                    AccountFragment.OnFragmentInteractionListener{

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(new StaticticsFragment());
                    return true;
                case R.id.navigation_dashboard:
                    loadFragment(new AccountFragment());
                    return true;
                case R.id.navigation_notifications:
                    loadFragment(new NotificationFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new StaticticsFragment());
    }

    private void loadFragment(Fragment fragment){
                this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

}
