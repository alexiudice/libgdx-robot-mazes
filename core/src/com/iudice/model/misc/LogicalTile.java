package com.iudice.model.misc;

import com.iudice.utils.Coordinate;
import com.iudice.utils.LogicalBoard;

import java.util.HashMap;
import java.util.Map;

public class LogicalTile
{
    public final Coordinate logicalCoordinate;

    // true = active wall
    public Map<LogicalBoard.WallDirection, Boolean> walls;

    public LogicalTile( Coordinate logicalCoordinate )
    {
        this.logicalCoordinate = logicalCoordinate;
        walls = new HashMap<>(  );
    }

    public LogicalTile( Coordinate logicalCoordinate, LogicalBoard.WallDirection dir )
    {
        this.logicalCoordinate = logicalCoordinate;
        walls = new HashMap<>(  );
        walls.put( dir, true );
    }

    public LogicalTile( Coordinate logicalCoordinate, boolean north, boolean south, boolean east, boolean west )
    {
        this.logicalCoordinate = logicalCoordinate;
        walls = new HashMap<LogicalBoard.WallDirection, Boolean>(  );
        walls.put( LogicalBoard.WallDirection.NORTH, north );
        walls.put( LogicalBoard.WallDirection.SOUTH, south );
        walls.put( LogicalBoard.WallDirection.EAST, east );
        walls.put( LogicalBoard.WallDirection.WEST, west );
    }

    public void setWall( LogicalBoard.WallDirection wallDirection, boolean active )
    {
        walls.put( wallDirection, active );
    }

    public boolean isWalled( LogicalBoard.WallDirection wallDirection )
    {
        return walls.getOrDefault( wallDirection, false );
    }
}
