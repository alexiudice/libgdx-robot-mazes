package com.iudice.controller.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.iudice.model.actors.gui.MovementBar;
import com.iudice.model.meta.AssetMaster;
import com.iudice.model.meta.GameManager;
import com.iudice.model.meta.LevelManager;
import com.iudice.model.misc.Movement;

public class MovementBarController
{
    private static MovementBar movementBar;

    private void MovementBarController()
    {

    }

    public static void init()
    {
        movementBar.textureAtlas = AssetMaster.getTextureAtlas( "arrows" );

        movementBar.numMoves = LevelManager.tmxMap.get( LevelManager.currentLevel ).numMoves;

        movementBar.cursorStartPosition = new Vector2( movementBar.getX(), movementBar.getY() );

        movementBar.state = MovementBar.State.CONSUMING;

//        movementBar.setRegion( movementBar.orientation.get( Movement.LEFT ) );
        movementBar.setBounds(movementBar.getX(),movementBar.getY(), movementBar.numMoves * 16 / GameManager.PPM, 16 / GameManager.PPM);

    }

    public static void update(float delta)
    {

        updateCursorPosition();
        
        switch ( movementBar.state )
        {
        case CONSUMING:
            if(movementBar.movementArrows.size() < movementBar.numMoves )
            {
                if ( Gdx.input.isKeyJustPressed( Input.Keys.UP )) {
                    movementBar.addArrowWithMovement( Movement.UP );
                }
            }
            else if(movementBar.movementArrows.size() < movementBar.numMoves )
            {
                if ( Gdx.input.isKeyJustPressed( Input.Keys.DOWN )) {
                    movementBar.addArrowWithMovement( Movement.DOWN );
                }
            }
            else if(movementBar.movementArrows.size() < movementBar.numMoves )
            {
                if ( Gdx.input.isKeyJustPressed( Input.Keys.LEFT )) {
                    movementBar.addArrowWithMovement( Movement.LEFT );
                }
            }
            else if(movementBar.movementArrows.size() < movementBar.numMoves )
            {
                if ( Gdx.input.isKeyJustPressed( Input.Keys.RIGHT )) {
                    movementBar.addArrowWithMovement( Movement.RIGHT );
                }
            }
            if ( Gdx.input.isKeyJustPressed( Input.Keys.ENTER ) && movementBar.movementArrows.size() == movementBar.numMoves )
            {
                movementBar.state = MovementBar.State.PRODUCING;
            }
            break;
        case PRODUCING:
        }
    }

    public static void updateCursorPosition()
    {
        movementBar.cursorCurrentPosition = new Vector2(  movementBar.cursorStartPosition.x + movementBar.movementArrows.size(), movementBar.cursorStartPosition.y);
    }

    public static void setMovementBar( MovementBar movementBar )
    {
        MovementBarController.movementBar = movementBar;
    }
}
