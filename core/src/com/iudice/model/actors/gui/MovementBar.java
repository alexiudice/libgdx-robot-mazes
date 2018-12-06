package com.iudice.model.actors.gui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.iudice.controller.actors.MovementBarController;
import com.iudice.model.actors.maptiles.MapTileObject;
import com.iudice.model.meta.GameManager;
import com.iudice.model.misc.Movement;
import com.iudice.model.phys.RigidBody;
import com.iudice.view.screen.PlayScreen;

import java.util.ArrayList;
import java.util.List;

public class MovementBar extends RigidBody
{
    public enum State{
        PRODUCING, // Producing output
        CONSUMING // Consuming input
    }
    public TextureAtlas textureAtlas;
    public Vector2 cursorStartPosition;
    public Vector2 cursorCurrentPosition;



    public State state;
    public int numMoves;
    public List<MovementArrow> movementArrows = new ArrayList<MovementArrow>(  );

    public MovementBar( PlayScreen playScreen, float x, float y )
    {
        super( playScreen, x, y );
        MovementBarController.setMovementBar( this );
        MovementBarController.init();
    }

    @Override
    protected void defBody()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(bodyDef);

        body.setUserData( this );
    }

    @Override
    public void update( float delta )
    {
        MovementBarController.update( delta );
    }

    public void addArrowWithMovement(Movement m)
    {
        switch ( m )
        {
        case UP:
            movementArrows.add( new MovementArrow.ArrowUp( playScreen, cursorCurrentPosition.x, cursorCurrentPosition.y,
                    new TextureRegion( textureAtlas.findRegion( "arrow_up" ) ) ) );
            break;
        case DOWN:
            movementArrows.add( new MovementArrow.ArrowDown( playScreen, cursorCurrentPosition.x, cursorCurrentPosition.y,
                    new TextureRegion( textureAtlas.findRegion( "arrow_down" ) )) );
            break;
        case LEFT:
            movementArrows.add( new MovementArrow.ArrowLeft( playScreen, cursorCurrentPosition.x, cursorCurrentPosition.y,
                    new TextureRegion( textureAtlas.findRegion( "arrow_left" ) )) );
            break;
        case RIGHT:
            movementArrows.add( new MovementArrow.ArrowRight( playScreen, cursorCurrentPosition.x, cursorCurrentPosition.y,
                    new TextureRegion( textureAtlas.findRegion( "arrow_right" ) )) );
            break;
        }

    }

    public static class MovementArrow extends RigidBody
    {
        public boolean isDrawn;
        public Movement movement;

        public MovementArrow( PlayScreen playScreen, float x, float y, TextureRegion textureRegion, Movement movement )
        {
            super( playScreen, x, y );
            this.movement = movement;
            setRegion( textureRegion );
            float width = 8 / GameManager.PPM;
            float height = 8 / GameManager.PPM;

            setBounds(x - width / 2, y - height / 2, width, height);
        }

        @Override
        protected void defBody()
        {
            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(getX(), getY());
            bodyDef.type = BodyDef.BodyType.StaticBody;

            body = world.createBody(bodyDef);
            body.setUserData( this );
        }

        @Override
        public void update( float delta )
        {
            if (destroyed) {
                return;
            }

            if (toBeDestroyed) {
                setSize(0, 0 );
                world.destroyBody(body);
//                MovementBarController.removeMovementArrow( this );
                destroyed = true;
                return;
            }

            setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
        }

        public static class ArrowUp extends MovementArrow
        {
            public ArrowUp( PlayScreen playScreen, float x, float y, TextureRegion textureRegion)
            {
                super( playScreen, x, y, textureRegion, Movement.UP );
            }
        }

        public static class ArrowDown extends MovementArrow
        {
            public ArrowDown( PlayScreen playScreen, float x, float y, TextureRegion textureRegion )
            {
                super( playScreen, x, y, textureRegion,Movement.DOWN );
            }
        }

        public static class ArrowLeft extends MovementArrow
        {
            public ArrowLeft( PlayScreen playScreen, float x, float y, TextureRegion textureRegion )
            {
                super( playScreen, x, y, textureRegion, Movement.LEFT );
            }
        }

        public static class ArrowRight extends MovementArrow
        {
            public ArrowRight( PlayScreen playScreen, float x, float y, TextureRegion textureRegion )
            {
                super( playScreen, x, y, textureRegion, Movement.RIGHT );
            }
        }


    }


}
