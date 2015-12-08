package com.codepath.apps.Twitter.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Lin on 12/8/15.
 */
public class User {
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;

    public static User fromJSON(JSONObject jsonObject) {
        User user = new User();

        try {
            user.name = jsonObject.getString("name");
            user.uid = jsonObject.getLong("uid");
            user.screenName = jsonObject.getString("screen_name");
            user.profileImageUrl = jsonObject.getString("porfile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;

    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }
}
