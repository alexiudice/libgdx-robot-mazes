package com.iudice.model.misc;

import com.iudice.utils.LogicalBoard;

public enum Movement
{
    UP,
    DOWN,
    LEFT,
    RIGHT,
    NONE;

    public static LogicalBoard.WallDirection toWallDirection(Movement m)
    {
        switch ( m )
        {
        case UP:
            return LogicalBoard.WallDirection.NORTH;
        case DOWN:
            return LogicalBoard.WallDirection.SOUTH;
        case LEFT:
            return LogicalBoard.WallDirection.WEST;
        case RIGHT:
            return LogicalBoard.WallDirection.EAST;
        }
        throw new RuntimeException( "Bad MovementToWallDirection conversion." );
    }
}
