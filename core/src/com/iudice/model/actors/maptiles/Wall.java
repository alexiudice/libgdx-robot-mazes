package com.iudice.model.actors.maptiles;

import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.iudice.view.screen.PlayScreen;

public abstract class Wall extends MapTileObject
{

    public Wall( PlayScreen playScreen, float x, float y, TiledMapTileMapObject mapObject )
    {
        super( playScreen, x, y, mapObject );
    }
}
