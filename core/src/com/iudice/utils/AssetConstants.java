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
        temp.put("1-1", "maps/Level_1-tmp.tmx");
//        temp.put("1-2", "maps/Level_1-2.tmx");
        mapsMap = Collections.unmodifiableMap(temp);
    }
}
