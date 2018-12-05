package com.iudice.model.meta;

public class Level
{

    public String world;
    public String level;
    public String tmxFile;

    public Level( String world, String level, String tmxFile )
    {
        this.world = world;
        this.level = level;
        this.tmxFile = tmxFile;
    }
}
