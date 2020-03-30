package com.mask.mediaplayer_demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class VideoPlayerActivity extends AppCompatActivity {

    private static final String KEY_SOURCE = "Source";

    private StandardGSYVideoPlayer video_player;

    private OrientationUtils orientationUtils;

    private String videoSource;

    public static void startActivity(Context context, String source) {
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra(KEY_SOURCE, source);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        video_player = findViewById(R.id.video_player);

        // 增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher);
        video_player.setThumbImageView(imageView);
        // 增加title
        video_player.getTitleTextView().setVisibility(View.VISIBLE);
        // 设置返回键
        video_player.getBackButton().setVisibility(View.VISIBLE);
        // 设置旋转
        orientationUtils = new OrientationUtils(this, video_player);
        // 设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        video_player.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        // 是否可以滑动调整
        video_player.setIsTouchWiget(true);
        //设置返回按键功能
        video_player.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        videoSource = intent.getStringExtra(KEY_SOURCE);

        video_player.setUp(videoSource, true, "测试视频");

        video_player.startPlayLogic();
    }

    private void initListener() {

    }

}
