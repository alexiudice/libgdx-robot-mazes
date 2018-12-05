package com.iudice.model.actors.maptiles;

import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.iudice.model.meta.GameManager;
import com.iudice.model.phys.Collider;
import com.iudice.view.screen.PlayScreen;

public class WallBottom extends Wall
{

    public int width = 2;
    public int height = 16;
    public Vector2 center = new Vector2( 0,-7 );

    public WallBottom( PlayScreen playScreen, float x, float y, TiledMapTileMapObject mapObject)
    {
        super(playScreen, x, y, mapObject );
    }

    @Override
    protected void defBody()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / GameManager.PPM / 2, height / GameManager.PPM / 2, center, 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = GameManager.GROUND_BIT;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef).setUserData(this);

        shape.dispose();
    }

    @Override
    public void update( float delta )
    {
        super.update( delta );
    }

    @Override
    public void onCollide( Collider other )
    {
        super.onCollide( other );
    }


}
