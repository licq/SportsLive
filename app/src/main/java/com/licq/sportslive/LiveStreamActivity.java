package com.licq.sportslive;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.io.Serializable;

public class LiveStreamActivity extends AppCompatActivity {

    private ExoPlayer player;
    private PlayerView playerView;

    private Links links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_stream);

        Intent intent = getIntent();
        links = (Links) intent.getSerializableExtra("links");

        playerView = findViewById(R.id.player_view);
        new GetStreamLinkTask().execute(links.first());
    }

    private class GetStreamLinkTask extends AsyncTask<Link, Void, String> {

        @Override
        protected String doInBackground(Link... links) {
            Link link = links[0];
            return WebScraper.getStreamLink(link);
        }

        @Override
        protected void onPostExecute(String streamUrl) {
            if(streamUrl == null || streamUrl.isEmpty()) {
                initializeError();
            }else {
                try {
                    initializePlayer(streamUrl);
                } catch (Exception e) {
                    initializeError();
                }
            }
        }
    }

    private void initializeError() {
        TextView error = new TextView(this);
        error.setText("当前比赛未在进行中");
        error.setGravity(Gravity.CENTER);
        error.setTextColor(Color.parseColor("#FF0000"));
        error.setTextSize(24);
        setContentView(error);
    }

    private void initializePlayer(String streamUrl) {
        player = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        MediaItem videoItem = MediaItem.fromUri(streamUrl);

        player.addMediaItem(videoItem);
        player.prepare();
        player.play();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
