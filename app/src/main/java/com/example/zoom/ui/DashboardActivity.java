package com.example.zoom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zoom.R;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.URL;

public class DashboardActivity extends AppCompatActivity {

    private EditText secretCodeBox;
    private Button mBtnJoin, mBtnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setUpViews();
    }

    private void setUpViews() {
        secretCodeBox = findViewById(R.id.codeBoxD);
        mBtnJoin = findViewById(R.id.joinBtnD);
        mBtnShare = findViewById(R.id.shareBtnD);

        URL serverUrl;
        try {
            serverUrl = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverUrl)
                            .setWelcomePageEnabled(false)
                            .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mBtnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(secretCodeBox.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();
                JitsiMeetActivity.launch(DashboardActivity.this, options);
            }
        });

    }
}