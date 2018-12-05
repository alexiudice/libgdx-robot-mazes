package com.iudice.model.actors.gui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.iudice.controller.actors.MovementBarController;
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

        public MovementArrow( PlayScreen playScreen, float x, float y, TextureRegion textureRegion )
        {
            super( playScreen, x, y );
            isDrawn = false;
            setRegion( textureRegion );
            setBounds( x, y, 8/ GameManager.PPM, 8/ GameManager.PPM );
        }

        @Override
        protected void defBody()
        {
            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(getX(), getY());
            bodyDef.type = BodyDef.BodyType.StaticBody;

            body = world.createBody(bodyDef);
        }

        @Override
        public void update( float delta )
        {
            if (destroyed) {
                return;
            }

            if (toBeDestroyed) {
                setBounds(0, 0, 0, 0);
                world.destroyBody(body);
                destroyed = true;
                return;
            }

            setPosition(body.getPosition().x - 8 / GameManager.PPM, body.getPosition().y - 8 / GameManager.PPM);
        }

        public static class ArrowUp extends MovementArrow
        {
            public ArrowUp( PlayScreen playScreen, float x, float y, TextureRegion textureRegion)
            {
                super( playScreen, x, y, textureRegion );
                movement = Movement.UP;
            }
        }

        public static class ArrowDown extends MovementArrow
        {
            public ArrowDown( PlayScreen playScreen, float x, float y, TextureRegion textureRegion )
            {
                super( playScreen, x, y, textureRegion );
                movement = Movement.DOWN;
            }
        }

        public static class ArrowLeft extends MovementArrow
        {
            public ArrowLeft( PlayScreen playScreen, float x, float y, TextureRegion textureRegion )
            {
                super( playScreen, x, y, textureRegion );
                movement = Movement.LEFT;
            }
        }

        public static class ArrowRight extends MovementArrow
        {
            public ArrowRight( PlayScreen playScreen, float x, float y, TextureRegion textureRegion )
            {
                super( playScreen, x, y, textureRegion );
                movement = Movement.RIGHT;
            }
        }


    }


}
