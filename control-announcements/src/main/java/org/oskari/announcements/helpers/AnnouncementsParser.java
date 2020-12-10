package org.oskari.announcements.helpers;

import fi.nls.oskari.control.ActionParameters;
import fi.nls.oskari.control.ActionParamsException;
import org.oskari.announcements.helpers.Announcement;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * Announcements parser
 */
public class AnnouncementsParser {
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_BEGIN_DATE = "begin_date";
    public static final String KEY_END_DATE = "end_date";
    public static final String KEY_ACTIVE = "active";

    /**
     * Parse announcement params from json
     * @param params
     * @return
     * @throws JSONException
     * @throws ActionParamsException
     */
    public static Announcement parseAnnouncementParams (ActionParameters params) throws JSONException, ActionParamsException {
        JSONObject jsonParams =  params.getHttpParamAsJSON("params");
        System.out.println("============!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!=============================");
        System.out.println("jsonParams");
        System.out.println(jsonParams);

        Announcement Announcement = new Announcement();

        if (jsonParams.has(KEY_ID)) {
            Announcement.setId(jsonParams.getInt(KEY_ID));
            System.out.println("id");
            System.out.println(jsonParams.getInt(KEY_ID));
        }

        if (jsonParams.has(KEY_TITLE)) {
            Announcement.setTitle(jsonParams.getString(KEY_TITLE));
        }

        if (jsonParams.has(KEY_CONTENT)) {
            Announcement.setContent(jsonParams.getString(KEY_CONTENT));
        }
        
        if (jsonParams.has(KEY_BEGIN_DATE)) {
            Announcement.setBeginDate(jsonParams.getString(KEY_BEGIN_DATE));
        }
        
        if (jsonParams.has(KEY_END_DATE)) {
            Announcement.setEndDate(jsonParams.getString(KEY_END_DATE));
        }

        if (jsonParams.has(KEY_ACTIVE)) {
            Announcement.setActive(jsonParams.getString(KEY_ACTIVE));
        }

        System.out.println("Announcement");
        System.out.println(Announcement);

        return Announcement;
    }

    public JSONObject parseAnnouncementsMap(final List<Map<String,Object>> list) throws JSONException {
        final JSONArray results = new JSONArray();
        for(Map<String, Object> map : list) {
            results.put(mapData(map));
        }
        JSONObject data = new JSONObject();
        data.put("data", results);
        return data;
    }

    public JSONObject mapData(Map<String, Object> data) throws JSONException {
        if(data == null) {
            return null;
        }

        JSONObject obj = new JSONObject();

        obj.put(KEY_ID, data.get("id"));
        obj.put(KEY_TITLE, data.get("title"));
        obj.put(KEY_CONTENT, data.get("content"));
        obj.put(KEY_BEGIN_DATE, data.get("begin_date"));
        obj.put(KEY_END_DATE, data.get("end_date"));
        obj.put(KEY_ACTIVE, data.get("active"));

        return obj;
    }
}