package com.mask.mediaplayer_demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mask.mediaplayer_demo.utils.FileUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Activity activity;

    private String videoNetUrl;// Video网络Url
    private String videoLocalPath;// Video本地路径
    private String videoSource;// VideoSource

    private TextView tv_video_info;
    private Button btn_video_source_switch;
    private Button btn_video_player_open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshVideoInfo();
    }

    private void initView() {
        tv_video_info = findViewById(R.id.tv_video_info);
        btn_video_source_switch = findViewById(R.id.btn_video_source_switch);
        btn_video_player_open = findViewById(R.id.btn_video_player_open);
    }

    private void initData() {
        videoNetUrl = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";

        File videoDirFile = FileUtils.getCacheMovieDir(this);
        boolean mkdirs = videoDirFile.mkdirs();
        File videoLocalFile = new File(videoDirFile, "MediaRecorder_20200327_144432966.mp4");
        videoLocalPath = videoLocalFile.getAbsolutePath();

        videoSource = videoNetUrl;
    }

    private void initListener() {
        btn_video_source_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoNetUrl.equals(videoSource)) {
                    videoSource = videoLocalPath;
                } else if (videoLocalPath.equals(videoSource)) {
                    videoSource = videoNetUrl;
                }

                refreshVideoInfo();
            }
        });
        btn_video_player_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoPlayerActivity.startActivity(activity, videoSource);
            }
        });
    }

    /**
     * 刷新 VideoInfo
     */
    private void refreshVideoInfo() {
        tv_video_info.setText(videoSource);
    }
}
