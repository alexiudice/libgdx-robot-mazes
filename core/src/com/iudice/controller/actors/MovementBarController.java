package com.iudice.controller.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.iudice.model.actors.gui.MovementBar;
import com.iudice.model.meta.AssetMaster;
import com.iudice.model.meta.GameManager;
import com.iudice.model.meta.LevelManager;
import com.iudice.model.misc.Movement;

import java.util.ArrayList;
import java.util.List;

public class MovementBarController
{
    private static MovementBar movementBar;
    private static MovementBar.MovementHighlighter movementHighlighter;

    private static TextureAtlas miscAtlas;

    private void MovementBarController()
    {

    }

    public static void init()
    {
        miscAtlas = AssetMaster.getTextureAtlas( "misc" );

        movementBar.textureAtlas = AssetMaster.getTextureAtlas( "arrows" );

        movementBar.numMoves = LevelManager.tmxMap.get( LevelManager.currentLevel ).numMoves;

        movementBar.cursorStartPosition = new Vector2( movementBar.getX(), movementBar.getY() );

        movementBar.state = MovementBar.State.CONSUMING;

//        movementBar.setRegion( movementBar.orientation.get( Movement.LEFT ) );
        movementBar.setBounds(movementBar.getX(),movementBar.getY(), movementBar.numMoves * 16 / GameManager.PPM, 16 / GameManager.PPM);


        movementHighlighter.setRegion( miscAtlas.findRegion( "yellowHighlight" ) );
        movementHighlighter.setAlpha( 0.5F );

    }

    public static void update(float delta)
    {

        updateCursorPosition();

        for(int i = movementBar.movementArrows.size()-1; i >= 0; i--)
        {
            movementBar.movementArrows.get( i ).update( delta );
            if(movementBar.movementArrows.get( i ).isDestroyed())
            {
                movementBar.movementArrows.remove( i );
            }
        }
        
        switch ( movementBar.state )
        {
        case CONSUMING:
            if(movementBar.movementArrows.size() < movementBar.numMoves )
            {
                if ( Gdx.input.isKeyJustPressed( Input.Keys.UP )) {
                    movementBar.addArrowWithMovement( Movement.UP );
                }
                else if ( Gdx.input.isKeyJustPressed( Input.Keys.DOWN ) )
                {
                    movementBar.addArrowWithMovement( Movement.DOWN );
                }
                else if ( Gdx.input.isKeyJustPressed( Input.Keys.LEFT ) )
                {
                    movementBar.addArrowWithMovement( Movement.LEFT );
                }
                else if ( Gdx.input.isKeyJustPressed( Input.Keys.RIGHT ) )
                {
                    movementBar.addArrowWithMovement( Movement.RIGHT );
                }
            }

            if ( Gdx.input.isKeyJustPressed( Input.Keys.ENTER ) && movementBar.movementArrows.size() == movementBar.numMoves )
            {
                movementBar.state = MovementBar.State.PRODUCING;
            }
            break;
        case PRODUCING:
            List<Movement> movements = new ArrayList<Movement>(  );
            for( MovementBar.MovementArrow movementArrow : movementBar.movementArrows)
            {
                movements.add( movementArrow.movement );
            }
            RobotController.startMoving( movements );
            break;
        }
    }

    public static void updateCursorPosition()
    {
        movementBar.cursorCurrentPosition = new Vector2(  movementBar.cursorStartPosition.x + movementBar.movementArrows.size(), movementBar.cursorStartPosition.y);
    }

    public static void reset()
    {
        for( MovementBar.MovementArrow movementArrow : movementBar.movementArrows)
        {
            movementArrow.queueDestroy();
        }

        movementBar.state = MovementBar.State.CONSUMING;
        updateCursorPosition();
    }

    public static void removeMovementArrow( MovementBar.MovementArrow movementArrow)
    {
        movementBar.movementArrows.remove( movementArrow );
    }

    public static void setMovementBar( MovementBar movementBar )
    {
        MovementBarController.movementBar = movementBar;
    }

    public static void setMovementHighlighter( MovementBar.MovementHighlighter movementHighlighter )
    {
        MovementBarController.movementHighlighter = movementHighlighter;
    }

    public static void highlightUpdate()
    {
        movementHighlighter.getBody().setTransform(
                movementBar.cursorStartPosition.x + RobotController.currentMovePosition(),
                movementBar.cursorStartPosition.y, 0);
    }
}
