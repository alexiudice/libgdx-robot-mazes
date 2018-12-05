package com.iudice.controller.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.iudice.controller.AssetLoader;
import com.iudice.model.actors.Robot;
import com.iudice.model.actors.maptiles.Wall;
import com.iudice.model.meta.AssetMaster;
import com.iudice.model.meta.GameManager;
import com.iudice.model.meta.LevelManager;
import com.iudice.model.misc.Movement;
import com.iudice.model.phys.Collider;
import com.iudice.model.phys.RigidBody;
import com.iudice.view.screen.PlayScreen;

import java.util.Map;

public class RobotController
{

    private static Robot robot;

    private RobotController()
    {

    }

    public static void init()
    {
        robot.textureAtlas = AssetMaster.getTextureAtlas( "robot" );

        robot.orientation.put( Movement.LEFT, new TextureRegion( robot.textureAtlas.findRegion( "robot_left" ) ));
        robot.orientation.put( Movement.RIGHT, new TextureRegion( robot.textureAtlas.findRegion( "robot_right" ) ));
        robot.orientation.put( Movement.UP, new TextureRegion( robot.textureAtlas.findRegion( "robot_top" ) ));
        robot.orientation.put( Movement.DOWN, new TextureRegion( robot.textureAtlas.findRegion( "robot_bottom" ) ));

        robot.numMoves = LevelManager.tmxMap.get( LevelManager.currentLevel ).numMoves;

        robot.state = Robot.State.WAITING;

        robot.setRegion( robot.orientation.get( Movement.LEFT ) );
        robot.setBounds(robot.getX(),robot.getY(), 16 / GameManager.PPM, 16 / GameManager.PPM);
    }


    public static void defBody( Robot robot, World world, Body body ) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(robot.getX(), robot.getY());
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / GameManager.PPM / 2, 16 / GameManager.PPM / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = GameManager.ROBOT_BIT;
        fixtureDef.filter.maskBits = GameManager.FLAGPOLE_BIT | GameManager.WALL_BIT;
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef).setUserData(robot);

        shape.dispose();

    }

    public static void update( float delta )
    {

        switch ( robot.state )
        {
        case WAITING:
            updateMovementList( Input.Keys.UP, Movement.UP );
            updateMovementList( Input.Keys.DOWN, Movement.DOWN );
            updateMovementList( Input.Keys.LEFT, Movement.LEFT );
            updateMovementList( Input.Keys.RIGHT, Movement.RIGHT );
            if(Gdx.input.isKeyJustPressed( Input.Keys.ENTER ) && robot.movementList.size() == robot.numMoves)
            {
                robot.state = Robot.State.MOVING;
            }
            break;
        case MOVING:
            //Make single move

        }


        robot.setPosition(robot.getBody().getPosition().x - robot.getWidth() / 2, robot.getBody().getPosition().y - robot.getHeight() / 2);
    }


    public void onCollide( Collider other )
    {
        if(other.getUserData() instanceof Wall )
        {
            robot.isCollided = true;
        }
    }

    public static void setRobot( Robot robot )
    {
        RobotController.robot = robot;
    }

    public static void updateMovementList(int keyPressed, Movement movement)
    {
        if(robot.movementList.size() < robot.numMoves && robot.state.equals( Robot.State.WAITING ))
        {
            if ( Gdx.input.isKeyJustPressed( keyPressed )) {
                robot.movementList.add( movement );
            }
        }
    }
}
