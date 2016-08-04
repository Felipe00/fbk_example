package br.com.interaje.social;

import android.app.Application;

import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;

/**
 * Created by rayquaza on 19/07/16.
 */
public class AppMain extends Application {

    Permission[] permissions = new Permission[]{
            Permission.USER_PHOTOS,
            Permission.EMAIL,
            Permission.PUBLISH_ACTION
    };

    @Override
    public void onCreate() {
        super.onCreate();
        SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
                .setAppId(getResources().getString(R.string.app_id))
                .setNamespace("ShareAppTest")
                .setPermissions(permissions)
                .build();

        SimpleFacebook.setConfiguration(configuration);
    }
}
