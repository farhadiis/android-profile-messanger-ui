package com.example.soroushprofile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soroushprofile.models.ConversationType;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeWindow();
        initializeRes();
    }

    private void initializeRes() {
        findViewById(R.id.individual_preview).setOnClickListener(this);
        findViewById(R.id.group_preview).setOnClickListener(this);
        findViewById(R.id.channel_preview).setOnClickListener(this);
    }

    private void initializeWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void onClick(View v) {
        ConversationType type;
        switch (v.getId()) {
            case R.id.individual_preview:
                type = ConversationType.individual;
                break;
            case R.id.group_preview:
                type = ConversationType.group;
                break;
            case R.id.channel_preview:
                type = ConversationType.channel;
                break;
            default:
                throw new IllegalArgumentException();
        }
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.CONVERSATION_TYPE, type.name());
        startActivity(intent);
    }
}
