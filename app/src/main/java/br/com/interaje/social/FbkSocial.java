package br.com.interaje.social;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Feed;
import com.sromku.simple.fb.entities.Photo;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnPublishListener;

import java.util.List;

public class FbkSocial extends AppCompatActivity {

    SimpleFacebook mSimpleFacebook;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbk_social);

        btn = (Button) findViewById(R.id.btnPublishPhoto);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnLoginListener onLoginListener = new OnLoginListener() {

                    @Override
                    public void onLogin(String accessToken, List<Permission> acceptedPermissions, List<Permission> declinedPermissions) {
                        // change the state of the button or do whatever you want
                        Log.i("---Log->", "Logged in");
                    }

                    @Override
                    public void onCancel() {
                        // user canceled the dialog
                    }

                    @Override
                    public void onFail(String reason) {
                        // failed to login
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        // exception from facebook
                    }

                };
                mSimpleFacebook.login(onLoginListener);
                // callFeed();
                callPhoto();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSimpleFacebook = SimpleFacebook.getInstance(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mSimpleFacebook.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void callFeed() {
        Feed feed = new Feed.Builder()
                .setMessage("Lá vem outra coisa!")
                .setName("Biblioteca para facebook legal")
                .setCaption("Uma \"caption\" para minha publicação")
                .setDescription("Usando uma biblioteca pra facilitar o uso do fbk")
                .setPicture("https://raw.github.com/sromku/android-simple-facebook/master/Refs/android_facebook_sdk_logo.png")
                .setLink("https://github.com/sromku/android-simple-facebook")
                .build();

        OnPublishListener onPublishListener = new OnPublishListener() {
            @Override
            public void onComplete(String postId) {
                Log.i("---> Log", "Published successfully. The new post id = " + postId);
            }
        };

        mSimpleFacebook.publish(feed, onPublishListener);
    }

    public void callPhoto() {

        ImageView image = (ImageView) findViewById(R.id.imageView);
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();

        OnPublishListener onPublishListeners = new OnPublishListener() {
            @Override
            public void onComplete(String id) {
                Log.i("---Log-->", "Published successfully. id = " + id);
            }
        };

        // Bitmap bitmap = BitmapFactory.decodeResource(FbkSocial.this.getResources(), R.mipmap.ic_fbk_test);

        Photo photo = new Photo.Builder()
                .setImage(bitmap)
                .setName("Outra foto")
                .build();

        mSimpleFacebook.publish(photo, false, onPublishListeners);
    }
}
