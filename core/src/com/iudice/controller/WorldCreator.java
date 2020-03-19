package com.iudice.controller;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.iudice.model.actors.maptiles.EndTile;
import com.iudice.model.actors.maptiles.MapTileObject;
import com.iudice.model.actors.maptiles.StartTile;
import com.iudice.model.actors.maptiles.Wall;
import com.iudice.model.meta.GameManager;
import com.iudice.utils.LogicalBoard;
import com.iudice.view.screen.PlayScreen;

import static com.iudice.utils.GameConstants.BOARD_SIZE;

/**
 *
 *
 * WorldCreator
 */
public class WorldCreator {

    private Array<MapTileObject> mapTileObjects;
    private LogicalBoard logicalBoard;

    private Vector2 startPosition;
    private Vector2 flagPosition;
    private Vector2 cursorPosition;

    private int boardSize;

    public WorldCreator(PlayScreen playScreen, TiledMap tiledMap, float mapHeight) {

        // Get the Ordered Spaces of the Board
        MapLayer mapLayer = tiledMap.getLayers().get("Board");
        if (mapLayer != null) {
            if ( !mapLayer.getProperties().containsKey( BOARD_SIZE ) )
            {
                throw new RuntimeException( "No 'boardSize' property specified in the .tmx" );
            }
            boardSize = Integer.valueOf( (String) mapLayer.getProperties().get( BOARD_SIZE ) );
//            for (MapObject mapObject : mapLayer.getObjects()) {
//                float x = ((TiledMapTileMapObject) mapObject).getX();
//                float y = ((TiledMapTileMapObject) mapObject).getY();
//            }
        }

        logicalBoard = new LogicalBoard( boardSize );
        mapTileObjects = new Array<MapTileObject>();

        mapLayer = tiledMap.getLayers().get("WallEast");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new Wall(playScreen, (x + 8) / GameManager.PPM, (y + 8) / GameManager.PPM, (TiledMapTileMapObject) mapObject));
                logicalBoard.updateTile( x, mapHeight - y, LogicalBoard.WallDirection.EAST );
            }
        }

        mapLayer = tiledMap.getLayers().get("WallWest");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new Wall(playScreen, (x + 8) / GameManager.PPM, (y + 8) / GameManager.PPM, (TiledMapTileMapObject) mapObject));
                logicalBoard.updateTile( x, mapHeight - y, LogicalBoard.WallDirection.WEST );
            }
        }

        mapLayer = tiledMap.getLayers().get("WallNorth");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new Wall(playScreen, (x + 8) / GameManager.PPM, (y + 8) / GameManager.PPM, (TiledMapTileMapObject) mapObject));
                logicalBoard.updateTile( x, mapHeight - y, LogicalBoard.WallDirection.NORTH );
            }
        }


        mapLayer = tiledMap.getLayers().get("WallSouth");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new Wall(playScreen, (x + 8) / GameManager.PPM, (y + 8) / GameManager.PPM, (TiledMapTileMapObject) mapObject));
                logicalBoard.updateTile( x, mapHeight - y, LogicalBoard.WallDirection.SOUTH );
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
                MapObject mapObject = mapLayer.getObjects().get( 0 );
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                startPosition = new Vector2(x, y);
                mapTileObjects.add(new StartTile(playScreen, (x + 8) / GameManager.PPM, (y + 8) / GameManager.PPM, (TiledMapTileMapObject) mapObject));
            }
        }

        mapLayer = tiledMap.getLayers().get("End");
        if (mapLayer != null) {
            if (mapLayer.getObjects().getCount() > 0) {
                MapObject mapObject = mapLayer.getObjects().get( 0 );
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new EndTile(playScreen, (x + 8) / GameManager.PPM, (y + 8) / GameManager.PPM, (TiledMapTileMapObject) mapObject));
                logicalBoard.setEndTile( x, mapHeight - y );
            }
        }

        cursorPosition = new Vector2(64.0f, 64.0f);

        mapLayer = tiledMap.getLayers().get("Cursor");
        if (mapLayer != null) {
            if (mapLayer.getObjects().getCount() > 0) {
                float x = ((TiledMapTileMapObject) mapLayer.getObjects().get(0)).getX();
                float y = ((TiledMapTileMapObject) mapLayer.getObjects().get(0)).getY();

                cursorPosition = new Vector2(x, y);
            }
        }

    }

    public Vector2 getStartPosition() {
        return startPosition;
    }

    public Vector2 getFlagPosition() {
        return flagPosition;
    }

    public Vector2 getCursorPosition()
    {
        return cursorPosition;
    }

    public Array<MapTileObject> getMapTileObject() {
        return mapTileObjects;
    }

    public LogicalBoard getLogicalBoard()
    {
        return logicalBoard;
    }

    public int getBoardSize()
    {
        return boardSize;
    }
}
