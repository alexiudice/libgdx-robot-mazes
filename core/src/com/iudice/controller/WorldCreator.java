package com.iudice.controller;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.iudice.model.actors.maptiles.MapTileObject;
import com.iudice.model.actors.maptiles.WallBottom;
import com.iudice.model.actors.maptiles.WallLeft;
import com.iudice.model.actors.maptiles.WallRight;
import com.iudice.model.actors.maptiles.WallTop;
import com.iudice.model.meta.GameManager;
import com.iudice.view.screen.PlayScreen;

/**
 *
 *
 * WorldCreator
 */
public class WorldCreator {

    private Array<MapTileObject> mapTileObjects;

    private Vector2 startPosition;
    private Vector2 flagPosition;

    public WorldCreator(PlayScreen playScreen, TiledMap tiledMap) {

        mapTileObjects = new Array<MapTileObject>();

        MapLayer mapLayer = tiledMap.getLayers().get("WallRight");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new WallRight(playScreen, (x + 8) / GameManager.PPM, (y + 8) / GameManager.PPM, (TiledMapTileMapObject) mapObject));
            }
        }

        mapLayer = tiledMap.getLayers().get("WallLeft");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new WallLeft(playScreen, (x + 8) / GameManager.PPM, (y + 8) / GameManager.PPM, (TiledMapTileMapObject) mapObject));
            }
        }

        mapLayer = tiledMap.getLayers().get("WallTop");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new WallTop(playScreen, (x + 8) / GameManager.PPM, (y + 8) / GameManager.PPM, (TiledMapTileMapObject) mapObject));
            }
        }


        mapLayer = tiledMap.getLayers().get("WallBottom");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new WallBottom(playScreen, (x + 8) / GameManager.PPM, (y + 8) / GameManager.PPM, (TiledMapTileMapObject) mapObject));
            }
        }

        flagPosition = new Vector2();

        mapLayer = tiledMap.getLayers().get("Flag");
        if (mapLayer != null) {
            if (mapLayer.getObjects().getCount() > 0) {
                float x = ((TiledMapTileMapObject) mapLayer.getObjects().get(0)).getX();
                float y = ((TiledMapTileMapObject) mapLayer.getObjects().get(0)).getY();

                flagPosition = new Vector2(x, y);
            }
        }


        startPosition = new Vector2(64.0f, 64.0f);

        mapLayer = tiledMap.getLayers().get("Start");
        if (mapLayer != null) {
            if (mapLayer.getObjects().getCount() > 0) {
                float x = ((TiledMapTileMapObject) mapLayer.getObjects().get(0)).getX();
                float y = ((TiledMapTileMapObject) mapLayer.getObjects().get(0)).getY();

                startPosition = new Vector2(x, y);
            }
        }

    }

    public Vector2 getStartPosition() {
        return startPosition;
    }

    public Vector2 getFlagPosition() {
        return flagPosition;
    }

    public Array<MapTileObject> getMapTileObject() {
        return mapTileObjects;
    }

}
