package com.example.demoapp.Activity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.Activity.pojos.PojoUser;
import com.example.demoapp.R;
import com.google.android.exoplayer2.ExoPlayer;

import java.util.ArrayList;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosAdapterViewHolder> {
    private Context mContext;
    ArrayList<PojoUser> userArrayList;

    public VideosAdapter(Context context, ArrayList<PojoUser> userArrayList){
        this.mContext = context;
        this.userArrayList = userArrayList;
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

//        holder.epVideosPlayer.
    }

    @Override
    public int getItemCount() {
        if(userArrayList == null) return 0;
        return userArrayList.size();
    }

    public static class VideosAdapterViewHolder extends RecyclerView.ViewHolder{
        ExoPlayer epVideosPlayer;

        public VideosAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            epVideosPlayer = itemView.findViewById(R.id.epVideoPlayer);
        }
    }
}
