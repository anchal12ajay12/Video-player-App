package com.example.demoapp.Activity.activities;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.demoapp.Activity.adapters.MediaRecyclerAdapter;
import com.example.demoapp.Activity.pojos.PojoUser;
import com.example.demoapp.Activity.utilities.ExoPlayerRecyclerView;
import com.example.demoapp.Activity.utilities.JsonParser;
import com.example.demoapp.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

public class MainActivity extends AppCompatActivity {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    LinearLayout ll_progress_container;
    ExoPlayerRecyclerView mRecyclerView;
    private final ArrayList<PojoUser> mediaObjectList = new ArrayList<>();
    private MediaRecyclerAdapter mAdapter;
    private boolean firstTime = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.app_color));
        }


        initView();
        ll_progress_container = findViewById(R.id.ll_progress_container);



        if (firstTime) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    mRecyclerView.playVideo(false);
                }
            });
            firstTime = false;
        }


        makeHttpRequest();

    }

    void hideStatusBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void initView() {

        mRecyclerView = findViewById(R.id.rvVideos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(mRecyclerView);
    }

        private RequestManager initGlide() {
        RequestOptions options = new RequestOptions();
        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }
    @Override
    protected void onDestroy() {
        if (mRecyclerView != null) {
            mRecyclerView.releasePlayer();
        }
        super.onDestroy();
    }

    public void makeHttpRequest(){
        String url = "http://takatakapp.xyz/API/index.php?p=showAllVideos";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fb_id", "0");
            jsonObject.put("token", "eynBsaVYwIE:APA91bEmLkA0mold83uPz1N570IZjPGwAUE_o93EkDCPjSZ5-sQRxKRRwAEsSpiGvEOFMq06XQxrCx0k1Kbh8GVjYr3OmDjSGikCiJCReVGsM4-hHvPWgYMOaFrP-9HaO1JfWDg7PgZV");
            jsonObject.put("type", "related");
            jsonObject.put("page", "1");
            jsonObject.put("device_id", "af37ba10f52bca24");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(String.valueOf(jsonObject), JSON);
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String rawJsonResponse = response.body().string();
                runOnUiThread(() -> onPostResponseCalls(rawJsonResponse));
            }
        });
    }

    private void onPostResponseCalls(String rawJsonResponse){
        if(!TextUtils.isEmpty(rawJsonResponse)){
            ArrayList<PojoUser> userArrayList = JsonParser.parseRawResponse(rawJsonResponse);
            ll_progress_container.setVisibility(View.GONE);
            mRecyclerView.setMediaObjects(userArrayList);
            mAdapter = new MediaRecyclerAdapter(userArrayList, initGlide());
            //Set Adapter
            mRecyclerView.setAdapter(mAdapter);
//            videosAdapter.addVideos(userArrayList);
        }
        else{
            Toast.makeText(this, "Error while fetching data.", Toast.LENGTH_SHORT).show();
        }
    }
}