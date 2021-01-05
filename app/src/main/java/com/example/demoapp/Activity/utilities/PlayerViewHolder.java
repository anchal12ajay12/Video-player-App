package com.example.demoapp.Activity.utilities;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.demoapp.Activity.pojos.PojoCount;
import com.example.demoapp.Activity.pojos.PojoUser;
import com.example.demoapp.R;

public class PlayerViewHolder extends RecyclerView.ViewHolder {
    /**
     * below view have public modifier because
     * we have access PlayerViewHolder inside the ExoPlayerRecyclerView
     */
    public FrameLayout mediaContainer;
    public ImageView mediaCoverImage, volumeControl,iv_profile_pic,iv_isVerified, iv_setLike,iv_setcomment;
    public ProgressBar progressBar;
    public RequestManager requestManager;
    private TextView user_id,description, tv_like_count,tv_comment_count;
    private TextView tv_song;
    private View parent;
    public PlayerViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;

        mediaContainer = itemView.findViewById(R.id.mediaContainer);
        mediaCoverImage = itemView.findViewById(R.id.ivMediaCoverImage);
        mediaCoverImage = itemView.findViewById(R.id.ivMediaCoverImage);
        user_id = itemView.findViewById(R.id.tv_user_id);
        tv_like_count = itemView.findViewById(R.id.tv_like_count);
        tv_comment_count = itemView.findViewById(R.id.tv_comment_count);
        description = itemView.findViewById(R.id.tv_description);
        tv_song = itemView.findViewById(R.id.tv_song);
        progressBar = itemView.findViewById(R.id.progressBar);
        iv_setLike = itemView.findViewById(R.id.iv_setLike);
        iv_setcomment = itemView.findViewById(R.id.iv_setcomment);
        iv_isVerified = itemView.findViewById(R.id.iv_isVerified);
        iv_profile_pic = itemView.findViewById(R.id.iv_profile_pic);
        volumeControl = itemView.findViewById(R.id.ivVolumeControl);
    }
    public void onBind(PojoUser mediaObject, RequestManager requestManager) {
        this.requestManager = requestManager;
        parent.setTag(this);
        if(mediaObject.getUser_info().getVerified().equals("1")){
            iv_isVerified.setVisibility(View.VISIBLE);
        }

        user_id.setText(mediaObject.getUser_info().getUsername());
        tv_like_count.setText(mediaObject.getCount().getLike_count());
        tv_comment_count.setText(mediaObject.getCount().getVideo_comment_count());
        description.setText(mediaObject.getDescription());
        tv_song.setText(mediaObject.getSound().getSound_name());
        this.requestManager
                .load(mediaObject.getUser_info().getProfile_pic())
                .into(iv_profile_pic);
        this.requestManager
                .load(mediaObject.getThum())
                .into(mediaCoverImage);
    }
}