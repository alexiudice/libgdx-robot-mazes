package com.iudice.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AssetConstants
{
    public static final Map<String, String> imgsMap;
    static {
        Map<String, String> temp = new HashMap<String,String>(  );
        temp.put("robot", "imgs/robot.atlas");
        temp.put("arrows", "imgs/arrows.atlas");
        temp.put("boardSprites", "imgs/boardSprites.atlas");
        imgsMap = Collections.unmodifiableMap(temp);
    }

    public static final Map<String, String> mapsMap;
    static {
        Map<String, String> temp = new HashMap<String,String>(  );
        // test map
        temp.put("0-0", "maps/5x5_tmp.tmx");

        temp.put("1-1", "maps/Level_1.tmx");
        temp.put("1-2", "maps/Level_2.tmx");
        temp.put("1-3", "maps/Level_3.tmx");
        temp.put("1-4", "maps/Level_4.tmx");
        temp.put("1-5", "maps/Level_5.tmx");
        temp.put("1-6", "maps/Level_6.tmx");
        temp.put("1-7", "maps/Level_7.tmx");
        temp.put("1-8", "maps/Level_8.tmx");

        mapsMap = Collections.unmodifiableMap(temp);
    }
}
