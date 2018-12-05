package com.iudice.model.actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.iudice.controller.actors.RobotController;
import com.iudice.model.meta.GameManager;
import com.iudice.model.misc.Movement;
import com.iudice.model.phys.Collider;
import com.iudice.model.phys.RigidBody;
import com.iudice.view.screen.PlayScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Robot extends RigidBody
{
    public enum State{
        MOVING,
        WAITING
    }

    public Map<Movement,TextureRegion> orientation = new HashMap<Movement,TextureRegion>(  );
    public TextureAtlas textureAtlas;

    public List<Movement> movementList = new ArrayList<Movement>(  );
    public int nextMove;
    public int numMoves;

    public boolean isCollided;
    public State state;

    public Robot( PlayScreen playScreen, float x, float y )
    {
        super( playScreen, x, y );
        RobotController.setRobot( this );
        RobotController.init( );
    }

    @Override
    protected void defBody()
    {
        RobotController.defBody(this, world,body );
    }

    @Override
    public void update( float delta )
    {
        RobotController.update( delta );
    }

    @Override
    public void onCollide( Collider other )
    {
        super.onCollide( other );
    }

    public Map<Movement,TextureRegion> getOrientation()
    {
        return orientation;
    }

    public TextureAtlas getTextureAtlas()
    {
        return textureAtlas;
    }

    public List<Movement> getMovementList()
    {
        return movementList;
    }

    public int getNextMove()
    {
        return nextMove;
    }
}
