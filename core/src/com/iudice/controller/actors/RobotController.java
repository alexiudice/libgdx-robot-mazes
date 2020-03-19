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
import com.iudice.utils.Coordinate;
import com.iudice.utils.LogicalBoard;

import java.util.List;

public class RobotController
{

    private static Robot robot;
    private static LogicalBoard logicalBoard;

    private RobotController()
    {

    }

    public static void init()
    {
        robot.textureAtlas = AssetMaster.getTextureAtlas( "robot" );

        robot.orientationMap.put( Movement.LEFT, new TextureRegion( robot.textureAtlas.findRegion( "robot_left" ) ) );
        robot.orientationMap.put( Movement.RIGHT, new TextureRegion( robot.textureAtlas.findRegion( "robot_right" ) ) );
        robot.orientationMap.put( Movement.UP, new TextureRegion( robot.textureAtlas.findRegion( "robot_top" ) ) );
        robot.orientationMap.put( Movement.DOWN, new TextureRegion( robot.textureAtlas.findRegion( "robot_bottom" ) ) );

        robot.numMoves = LevelManager.tmxMap.get( LevelManager.currentLevel ).numMoves;

        robot.state = Robot.State.WAITING;
        robot.startPosition = new Vector2( robot.getX(), robot.getY() );

        robot.setRobotOrientation( Movement.UP ); //robot.setRegion( robot.orientationMap.get( Movement.UP ) );
        robot.setBounds( robot.getX(), robot.getY(), 16 / GameManager.PPM, 16 / GameManager.PPM );
        robot.setRobotPosition( new Coordinate(  (int) robot.getBody().getPosition().x, (int) robot.getBody().getPosition().y ));
    }

    public static void update( float delta )
    {
        if ( logicalBoard.isWinningPosition( robotPositionToLogicalPosition() ) )
        {
            robot.state = Robot.State.LEVEL_COMPLETED;
        }

        switch ( robot.state )
        {
        case MOVING:
            if ( robot.timeSinceLastMove > robot.timeBetweenMoves )
            {
                Movement movement = robot.movementList.get( robot.nextMove % robot.numMoves );
                robot.setCurrentOrientation( movement );
                if ( !logicalBoard.isValidMove( movement, robotPositionToLogicalPosition() ) )
                {
                    movement = Movement.NONE;
                }
                RobotController.preformMove( movement );
                robot.nextMove++;
                robot.timeSinceLastMove = 0f;
            }
            else
            {
                robot.timeSinceLastMove += delta;
            }
            break;
        case WAITING:
            break;
        case LEVEL_COMPLETED:
            break;
        }
        if ( Gdx.input.isKeyJustPressed( Input.Keys.ESCAPE ) )
        {
            reset();
        }

        robot.setPosition( robot.getBody().getPosition().x - 16 / GameManager.PPM / 2, robot.getBody().getPosition().y - 16 / GameManager.PPM / 2 );

        if(robot.state == Robot.State.LEVEL_COMPLETED)
        {
            reset();
        }
    }

    public static void onCollide( Collider other )
    {
        if ( other.getUserData() instanceof Wall )
        {
            robot.isCollided = true;
        }
    }

    public static void setRobot( Robot robot )
    {
        RobotController.robot = robot;
    }

    public static void setLogicalBoard( LogicalBoard logicalBoard )
    {
        RobotController.logicalBoard = logicalBoard;
    }

    public static void startMoving( List<Movement> movements )
    {
        robot.state = Robot.State.MOVING;
        robot.movementList = movements;
    }

    public static void preformMove( Movement m )
    {
        float currentX = robot.getBody().getPosition().x;
        float currentY = robot.getBody().getPosition().y;
        Movement orientationBeforeMove = robot.getCurrentOrientation();
        float moveDistance = 16 / GameManager.PPM;
        switch ( m )
        {
        case UP:
            robot.setRobotOrientation( Movement.UP );
            robot.getBody().setTransform( currentX, currentY + moveDistance, 0 );
            break;
        case DOWN:
            robot.setRobotOrientation( Movement.DOWN );
            robot.getBody().setTransform( currentX, currentY - moveDistance, 0 );
            break;
        case LEFT:
            robot.setRobotOrientation( Movement.LEFT );
            robot.getBody().setTransform( currentX - moveDistance, currentY, 0 );
            break;
        case RIGHT:
            robot.setRobotOrientation( Movement.RIGHT );
            robot.getBody().setTransform( currentX + moveDistance, currentY, 0 );
            break;
        case NONE:
            robot.setRobotOrientation( orientationBeforeMove );
            robot.getBody().setTransform( currentX, currentY, 0 );
            break;
        }

    }

    public static void reset()
    {
        robot.getBody().setTransform( robot.startPosition, 0 );
        robot.movementList.clear();
        robot.nextMove = 0;
        robot.state = robot.state == Robot.State.LEVEL_COMPLETED ? Robot.State.LEVEL_COMPLETED : Robot.State.WAITING;
        MovementBarController.reset();
    }

    public static Coordinate robotPositionToLogicalPosition()
    {
        Coordinate robotPosition = robot.getRobotPosition();
        int x = (robotPosition.getX() ) - 1;
        int y = (logicalBoard.getBoardSize() - robotPosition.getY());
        return new Coordinate( x,y );
    }
}
