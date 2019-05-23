package org.aviran.cookiebarsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.aviran.cookiebar2.CookieBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTop = findViewById(R.id.btn_top);
        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.Companion.build(MainActivity.this)
                        .setTitle(R.string.top_cookie_title)
                        .setTitleColor(R.color.yellow)
                        .setMessage(R.string.top_cookie_message)
                        .setIcon(R.drawable.ic_android_white_48dp)
                        .setDuration(5000)
                        .show();
            }
        });

        final Button btnBottom = findViewById(R.id.btn_bottom);
        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.Companion.build(MainActivity.this)
                        .setDuration(5000)
                        .setTitle(R.string.bottom_cookie_title)
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage(R.string.bottom_cookie_message)
                        .setTitleColor(R.color.yellow)
                        .setCookiePosition(CookieBar.Companion.getBOTTOM())
                        .show();
            }
        });


        Button btnCustomAnimation = findViewById(R.id.btn_custom_anim);
        btnCustomAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.Companion.build(MainActivity.this)
                        .setTitle(R.string.custom_anim_cookie_title)
                        .setMessage(R.string.custom_anim_cookie_message)
                        .setIcon(R.drawable.ic_android_white_48dp)
                        .setMessageColor(R.color.liteblue)
                        .setDuration(5000)
                        .setAnimationIn(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                        .setAnimationOut(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
                        .show();
            }
        });


        Button btnBottomAnimated = findViewById(R.id.btn_bottom_animated);
        btnBottomAnimated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.Companion.build(MainActivity.this)
                        .setTitle(R.string.fancy_cookie_title)
                        .setMessage(R.string.fancy_cookie_message)
                        .setIcon(R.drawable.ic_settings_white_48dp)
                        .setIconAnimation(R.animator.iconspin)
                        .setTitleColor(R.color.fancyTitle)
                        .setMessageColor(R.color.fancyMessage)
                        .setDuration(5000)
                        .setLayoutGravity(Gravity.BOTTOM)
                        .show();
            }
        });

        Button btnCustomView = findViewById(R.id.btn_custom_view);
        btnCustomView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                CookieBar.Companion.build(MainActivity.this)
                        .setCustomView(R.layout.custom_cookie)
                        .setTitle(R.string.custom_view_cookie_title)
                        .setMessage(R.string.custom_view_cookie_message)
                        .setCookiePosition(Gravity.TOP)
                        .show();
                }
        });

        findViewById(R.id.activity_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CookieBar.Companion.dismiss(MainActivity.this);
            }
        });

    }
}
