package com.example.demoapp.Activity.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.demoapp.Activity.adapters.VideosAdapter;
import com.example.demoapp.Activity.pojos.PojoUser;
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

public class MainActivity extends AppCompatActivity {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    RecyclerView rvVideos;
    VideosAdapter videosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.app_color));
        }

        rvVideos = findViewById(R.id.rvVideos);

        LinearLayoutManager lm = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvVideos.setLayoutManager(lm);
        VideosAdapter videosAdapter = new VideosAdapter(this, null);
        rvVideos.setAdapter(videosAdapter);

        makeHttpRequest();
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

        }
        else{
            Toast.makeText(this, "Error while fetching data.", Toast.LENGTH_SHORT).show();
        }
    }
}