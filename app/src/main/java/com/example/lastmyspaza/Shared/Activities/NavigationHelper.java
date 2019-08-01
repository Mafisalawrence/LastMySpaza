package com.example.lastmyspaza.Shared.Activities;

import android.app.Activity;
import android.content.Intent;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.example.lastmyspaza.Shared.Fragments.Registration.StoreDetails;
import com.example.lastmyspaza.Shared.Fragments.Registration.StoreList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NavigationHelper {

    private Activity activity;
    private FragmentManager fragmentManager;

    public NavigationHelper(Activity currentActivity)
    {
        this.activity = currentActivity;
    }
    public NavigationHelper(FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }
    public void loadActivity(Activity targetActivity){
        Intent intent =  new Intent(activity,targetActivity.getClass());
        activity.startActivity(intent);
    }

    public void moveTONextFragment(int fragmentContainer, Fragment targetFragment){

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(fragmentContainer, targetFragment)
                    .commit();
        }
    }
