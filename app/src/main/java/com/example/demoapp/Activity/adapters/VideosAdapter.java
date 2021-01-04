package com.example.demoapp.Activity.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.Activity.pojos.PojoUser;
import com.example.demoapp.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosAdapterViewHolder> {
    private Context mContext;
    ArrayList<PojoUser> userArrayList;

    boolean playWhenReady = true;
    int currentWindow = 0;
    long playbackPosition = 0;

    private SimpleExoPlayer player;
    private PlaybackStateListener playbackStateListener;

    public VideosAdapter(Context context, ArrayList<PojoUser> userArrayList){
        this.mContext = context;
        this.userArrayList = userArrayList;
        playbackStateListener = new PlaybackStateListener();
    }

    @NonNull
    @Override
    public VideosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.card_exo_player_container, parent, false);
        return new VideosAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapterViewHolder holder, int position) {
        PojoUser singleUser = userArrayList.get(position);

        initializePlayer(holder.playerView, singleUser.getVideo());
    }

    private void initializePlayer(PlayerView playerView, String videoUri){
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(mContext);
        trackSelector.setParameters(
                trackSelector.buildUponParameters().setMaxVideoSizeSd());

        player = new SimpleExoPlayer
                .Builder(mContext)
                .setTrackSelector(trackSelector)
                .build();
        playerView.setPlayer(player);

        MediaItem mediaItem = new MediaItem.Builder()
                .setUri(videoUri)
                .build();
        player.setMediaItem(mediaItem);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.addListener(playbackStateListener);
        player.prepare();
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.removeListener((Player.EventListener) playbackStateListener);
            player.release();
            player = null;
        }
    }

    @Override
    public int getItemCount() {
        if(userArrayList == null) return 0;
        return userArrayList.size();
    }

    public static class VideosAdapterViewHolder extends RecyclerView.ViewHolder{
        PlayerView playerView;
        public VideosAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            playerView = itemView.findViewById(R.id.epVideoPlayer);
        }
    }

    public void addVideos(ArrayList<PojoUser> userArrayList){
        this.userArrayList = userArrayList;
        notifyDataSetChanged();
    }

    private class PlaybackStateListener implements Player.EventListener{

        @Override
        public void onPlaybackStateChanged(int playbackState) {
            String stateString;
            switch (playbackState) {
                case ExoPlayer.STATE_IDLE:
                    stateString = "ExoPlayer.STATE_IDLE      -";
                    break;
                case ExoPlayer.STATE_BUFFERING:
                    stateString = "ExoPlayer.STATE_BUFFERING -";
                    break;
                case ExoPlayer.STATE_READY:
                    stateString = "ExoPlayer.STATE_READY     -";
                    break;
                case ExoPlayer.STATE_ENDED:
                    stateString = "ExoPlayer.STATE_ENDED     -";
                    break;
                default:
                    stateString = "UNKNOWN_STATE             -";
                    break;
            }
            Log.d("TAGvs", "changed state to " + stateString);
        }
    }
}
