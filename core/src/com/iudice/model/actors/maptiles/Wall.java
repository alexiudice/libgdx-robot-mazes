package com.iudice.model.actors.maptiles;

import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.iudice.model.phys.Collider;
import com.iudice.view.screen.PlayScreen;

public class Wall extends MapTileObject
{
    public Orientation orientation;
    public int logicalBoardPosition;

    public Wall( PlayScreen playScreen, float x, float y, TiledMapTileMapObject mapObject )
    {
        super( playScreen, x, y, mapObject );
    }

    public Wall( PlayScreen playScreen, float x, float y, TiledMapTileMapObject mapObject, Orientation orientation )
    {
        super( playScreen, x, y, mapObject );
        this.orientation = orientation;
    }

    @Override
    protected void defBody()
    {

    }
}
