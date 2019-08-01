package com.example.lastmyspaza.Shared.Activities;

import android.app.Activity;
import android.content.Intent;

public class NavigationHelper {

    private Activity activity;
    public NavigationHelper(Activity currentActivity)
    {
        this.activity = currentActivity;

    }
    public void loadActivity(Activity targetActivity){
        Intent intent =  new Intent(activity,targetActivity.getClass());
        activity.startActivity(intent);
    }
}
