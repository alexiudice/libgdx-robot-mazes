package com.iudice.model.actors.maptiles;

import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.iudice.view.screen.PlayScreen;

public class StartTile extends MapTileObject
{

    public StartTile( PlayScreen playScreen, float x, float y, TiledMapTileMapObject mapObject )
    {
        super( playScreen, x, y, mapObject );
    }

    @Override
    public void update( float delta )
    {
        super.update( delta );
    }

    @Override
    protected void defBody()
    {

    }
}
