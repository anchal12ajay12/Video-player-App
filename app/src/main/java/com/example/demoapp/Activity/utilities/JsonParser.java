package com.example.demoapp.Activity.utilities;

import android.util.Log;

import com.example.demoapp.Activity.pojos.PojoAudioPath;
import com.example.demoapp.Activity.pojos.PojoCount;
import com.example.demoapp.Activity.pojos.PojoSound;
import com.example.demoapp.Activity.pojos.PojoUser;
import com.example.demoapp.Activity.pojos.PojoUserInfo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {

    public static ArrayList<PojoUser> parseRawResponse(String rawJsonResponse){
        ArrayList<PojoUser> userArrayList = new ArrayList<>();
        try {
            JSONObject rawJsonObject = new JSONObject(rawJsonResponse);
            JSONArray msg_array = rawJsonObject.getJSONArray("msg");
            for(int i = 0; i < msg_array.length(); i++){
                JSONObject singleUserJsonObject = msg_array.getJSONObject(i);
                Gson gson = new Gson();
                PojoUser singleUser = gson.fromJson(String.valueOf(singleUserJsonObject), PojoUser.class);
//                PojoUserInfo singleUserInfoPojo = gson.fromJson(singleUserJsonObject.getJSONObject("user_info").toString(), PojoUserInfo.class);
//                PojoCount singleUserCountPojo = gson.fromJson(singleUserJsonObject.getJSONObject("count").toString(), PojoCount.class);
//                PojoAudioPath singleUserAudioPathPojo = gson.fromJson(singleUserJsonObject.getJSONObject("sound").getJSONObject("audio_path").toString(), PojoAudioPath.class);
//                PojoSound singleUserSoundPojo = new PojoSound(
//                        singleUserJsonObject.getJSONObject("sound").getString("id"),
//                        singleUserAudioPathPojo,
//                        singleUserJsonObject.getJSONObject("sound").getString("sound_name"),
//                        singleUserJsonObject.getJSONObject("sound").getString("description"),
//                        singleUserJsonObject.getJSONObject("sound").getString("thum"),
//                        singleUserJsonObject.getJSONObject("sound").getString("section"),
//                        singleUserJsonObject.getJSONObject("sound").getString("created")
//                );
//                PojoUser singleUserPojo = new PojoUser(
//                        singleUserJsonObject.getString("id"),
//                        singleUserJsonObject.getString("fb_id"),
//                        singleUserInfoPojo,
//                        singleUserCountPojo,
//                        singleUserJsonObject.getString("liked"),
//                        singleUserJsonObject.getString("video"),
//                        singleUserJsonObject.getString("thum"),
//                        singleUserJsonObject.getString("gif"),
//                        singleUserJsonObject.getString("is_block"),
//                        singleUserJsonObject.getString("description"),
//                        singleUserSoundPojo,
//                        singleUserJsonObject.getString("created")
//                );
                userArrayList.add(singleUser);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userArrayList;
    }
}
