package com.iudice.model.meta;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.HashMap;
import java.util.Map;

public class AssetMaster
{

    private static Map<String,TextureAtlas> textureAtlasMap = new HashMap<String,TextureAtlas>(  );

    public static void addTextureAtlas(String name, String path)
    {
        textureAtlasMap.put( name,new TextureAtlas(path));
    }

    public static TextureAtlas getTextureAtlas(String name)
    {
        return textureAtlasMap.get( name );
    }

    private static Map<String,TiledMap> tiledMapMap = new HashMap<String,TiledMap>(  );

    public static void addTiledMap(String name, String path)
    {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        tiledMapMap.put( name,tmxMapLoader.load( path ));
    }

    public static TiledMap getTiledMap(String name)
    {
        return tiledMapMap.get( name );
    }
}
