package com.iudice.model.actors;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.iudice.model.meta.GameManager;
import com.iudice.model.phys.Collider;
import com.iudice.model.phys.RigidBody;
import com.iudice.view.screen.PlayScreen;

public class Robot extends RigidBody
{

    public boolean levelCompleted;
    public Robot( PlayScreen playScreen, float x, float y )
    {
        super( playScreen, x, y );
    }

    @Override
    protected void defBody() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / GameManager.PPM / 2, 16 / GameManager.PPM / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = GameManager.ROBOT_BIT;
        fixtureDef.filter.maskBits = GameManager.FLAGPOLE_BIT | GameManager.WALL_BIT;
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef).setUserData(this);

        shape.dispose();

    }

    @Override
    public void update( float delta )
    {

    }

    @Override
    public void onCollide( Collider other )
    {
        super.onCollide( other );
    }
}
