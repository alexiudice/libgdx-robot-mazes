package com.iudice.controller.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.iudice.model.actors.Robot;
import com.iudice.model.actors.maptiles.Wall;
import com.iudice.model.meta.AssetMaster;
import com.iudice.model.meta.GameManager;
import com.iudice.model.meta.LevelManager;
import com.iudice.model.misc.Movement;
import com.iudice.model.phys.Collider;

import java.util.List;

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
        robot.startPosition = new Vector2( robot.getX(), robot.getY() );

        robot.setRegion( robot.orientation.get( Movement.LEFT ) );
        robot.setBounds(robot.getX(),robot.getY(), 16 / GameManager.PPM, 16 / GameManager.PPM);
    }


    public static void update( float delta )
    {

        switch ( robot.state )
        {
        case MOVING:
            if(Gdx.input.isKeyJustPressed( Input.Keys.ESCAPE ))
            {
                reset();
                break;
            }
            Movement movement = robot.movementList.get( robot.nextMove % robot.numMoves );
            RobotController.preformMove( movement );
            robot.nextMove++;
            break;
        case WAITING:
            //Make single move

        }


        robot.setPosition(robot.getBody().getPosition().x - 16 / GameManager.PPM / 2, robot.getBody().getPosition().y - 16 / GameManager.PPM / 2);
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

    public static void startMoving( List<Movement> movements )
    {
        robot.state = Robot.State.MOVING;
        robot.movementList = movements;
    }

    public static void preformMove(Movement m){
        float currentX = robot.getBody().getPosition().x;
        float currentY = robot.getBody().getPosition().y;
        switch ( m )
        {
        case UP:
            robot.setRegion( robot.orientation.get( Movement.UP ) );
            robot.getBody().setTransform( currentX,currentY+GameManager.PPM, 0  );
            break;
        case DOWN:
            robot.setRegion( robot.orientation.get( Movement.DOWN ) );
            robot.getBody().setTransform( currentX,currentY-GameManager.PPM, 0  );
            break;
        case LEFT:
            robot.setRegion( robot.orientation.get( Movement.LEFT ) );
            robot.getBody().setTransform( currentX - GameManager.PPM,currentY, 0  );
            break;
        case RIGHT:
            robot.setRegion( robot.orientation.get( Movement.RIGHT) );
            robot.getBody().setTransform( currentX + GameManager.PPM,currentY, 0  );
            break;
        }

    }

    public static void reset()
    {
        robot.getBody().setTransform( robot.startPosition, 0 );
        robot.movementList.clear();
        robot.nextMove = 0;
        robot.state = Robot.State.WAITING;
    }
}
