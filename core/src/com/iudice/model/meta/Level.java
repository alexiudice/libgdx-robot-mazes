package com.iudice.model.meta;

public class Level
{

    public String world;
    public String subworld;
    public String tmxFile;
    public int numMoves;

    public Level( String world, String subworld, String tmxFile, int numMoves )
    {
        this.world = world;
        this.subworld = subworld;
        this.tmxFile = tmxFile;
        this.numMoves = numMoves;
    }
}
