package com.iudice.controller;

import com.iudice.model.meta.AssetMaster;
import com.iudice.utils.AssetConstants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AssetLoader
{

    public static void loadAssets()
    {
        loadImgs();
        loadMaps();
    }

    public static void loadImgs()
    {
        for(String assetName : AssetConstants.imgsMap.keySet())
        {
            AssetMaster.addTextureAtlas( assetName, AssetConstants.imgsMap.get( assetName ));
        }
    }

    public static void loadMaps()
    {
        for(String assetName : AssetConstants.mapsMap.keySet())
        {
            AssetMaster.addTiledMap( assetName, AssetConstants.mapsMap.get( assetName ));
        }
    }
}
