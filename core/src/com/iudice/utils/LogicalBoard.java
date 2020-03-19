package com.iudice.utils;

import com.iudice.model.meta.GameManager;
import com.iudice.model.misc.LogicalTile;
import com.iudice.model.misc.Movement;

/**
 *
 * [ 0,0   1,0   2,0 . . . n,0 ]
 * [ 0,1   1,1   2,1 . . . n,1]
 * . . .
 * [ 0,n   1,n   2,n . . . n,n]
 */
public class LogicalBoard
{
    final int boardSize;
    LogicalTile[][] levelBoard;

    public LogicalBoard( int boardSize )
    {
        this.boardSize = boardSize;
        this.levelBoard = new LogicalTile[boardSize][boardSize];

        for ( int i = 0; i < boardSize; i++ )
        {
            for ( int j = 0; j < boardSize; j++ )
            {
                levelBoard[i][j] = new LogicalTile( new Coordinate( i, j ) );
            }
        }
    }

    public void updateTile( float pixelX, float pixelY, WallDirection wallDirection )
    {
        Coordinate logicalCoordinate = transformToLogical( new Coordinate( (int) pixelX, (int) pixelY ) );

        LogicalTile logicalTile = levelBoard[logicalCoordinate.getX()][logicalCoordinate.getY()];

        if ( logicalTile == null )
        {
            logicalTile = new LogicalTile( logicalCoordinate, wallDirection );
        }
        else
        {
            logicalTile.setWall( wallDirection, true );
        }

        // need to also set the wall for the adjacent tile
        Coordinate adjacentCoordinate = adjacentTileCoordinate( logicalCoordinate, wallDirection );
        if ( isCoordinateInBoard( adjacentCoordinate ) )
        {
            LogicalTile adjacentTile = levelBoard[adjacentCoordinate.getX()][adjacentCoordinate.getY()];
            if ( adjacentTile == null )
            {
                adjacentTile = new LogicalTile( adjacentCoordinate, wallDirection.opposite );
            }
            else
            {
                adjacentTile.setWall( wallDirection.opposite, true );
            }
        }
    }

    public boolean isValidMove( Movement m, Coordinate logicalCoordinate )
    {
        if ( isCoordinateInBoard( logicalCoordinate ) )
        {
            LogicalTile logicalTile = levelBoard[logicalCoordinate.getX()][logicalCoordinate.getY()];
            return !logicalTile.isWalled( Movement.toWallDirection( m ) );
        }
        return false;
    }

    public enum WallDirection
    {
        NORTH,
        SOUTH,
        EAST,
        WEST;

        private WallDirection opposite;

        static {
            NORTH.opposite = SOUTH;
            SOUTH.opposite = NORTH;
            EAST.opposite = WEST;
            WEST.opposite = EAST;
        }

    }

    public static Coordinate transformToLogical( Coordinate c)
    {
        return new Coordinate( (c.getX() / (int) GameManager.PPM) - 1, ((c.getY() - (int) GameManager.PPM) / (int) GameManager.PPM) - 1 );
    }

    public static Coordinate adjacentTileCoordinate( Coordinate c, WallDirection wallDirection )
    {
        switch ( wallDirection )
        {
        case NORTH:
            return new Coordinate( c.getX(), c.getY() - 1 );
        case SOUTH:
            return new Coordinate( c.getX(), c.getY() + 1 );
        case EAST:
            return new Coordinate( c.getX() + 1, c.getY() );
        case WEST:
            return new Coordinate( c.getX() - 1, c.getY() );
        }
        throw new RuntimeException( "LogicalBoard.adjacentTileCoordinate failed to match in switch" );
    }

    public boolean isCoordinateInBoard( Coordinate c )
    {
        return !(c.getX() < 0 || c.getY() < 0 || c.getX() >= boardSize || c.getY() >= boardSize);
    }

    public int getBoardSize()
    {
        return boardSize;
    }
}
