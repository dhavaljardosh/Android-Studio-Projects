package com.example.jardosh.intentexample;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class dhavalsIntentService extends IntentService{

    private static final String TAG = "package com.example.jardosh.intentexample";

    public dhavalsIntentService(String name) {
        super("dhavalsIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //THis is what the service does
        Log.i(TAG, "The service just started");
    }
}
