package com.iudice.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AssetConstants
{
    public static final Map<String, String> imgsMap;
    static {
        Map<String, String> temp = new HashMap<String,String>(  );
        temp.put("tilemap_gutter", "maps/tileset_gutter.png");
        temp.put("actors", "imgs/actors.atlas");
        imgsMap = Collections.unmodifiableMap(temp);
    }

    public static final Map<String, String> mapsMap;
    static {
        Map<String, String> temp = new HashMap<String,String>(  );
        temp.put("1-1", "maps/Level_1-1.tmx");
        temp.put("1-2", "maps/Level_1-2.tmx");
        mapsMap = Collections.unmodifiableMap(temp);
    }
}
