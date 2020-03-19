package com.iudice.model.meta;

import com.iudice.GameStart;
import com.iudice.view.screen.LevelScreen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LevelManager
{
    public static LevelManager instance;

    public static Map<String,Level> tmxMap;

    public static Iterator<String> levelIterator;
    public static String currentLevel;

    public static final int NUM_LEVELS = 8;

    public static Level[] levels = new Level[NUM_LEVELS];
    static
    {
        levels[0] = new Level( "1","1", "maps/Level_1.tmx", 4);
        levels[1] = new Level( "1","2", "maps/Level_2.tmx", 4);
        levels[2] = new Level( "1", "3", "maps/Level_3.tmx", 5 );
        levels[3] = new Level( "1", "4", "maps/Level_4.tmx", 5 );
        levels[4] = new Level( "1", "4", "maps/Level_5.tmx", 5 );
        levels[5] = new Level( "1", "4", "maps/Level_6.tmx", 5 );
        levels[6] = new Level( "1", "4", "maps/Level_7.tmx", 6 );
        levels[7] = new Level( "1", "4", "maps/Level_8.tmx", 6 );
    }




    public LevelManager()
    {
        if (instance == null) {
            instance = this;
        }


        assignTmxFilesToMap();
    }

    private void assignTmxFilesToMap()
    {
        tmxMap = new LinkedHashMap<String,Level>(  );
        tmxMap.put( "1-1",levels[0]);
        tmxMap.put( "1-2",levels[1]);
        tmxMap.put( "1-3",levels[2]);
        tmxMap.put( "1-4",levels[3]);
        tmxMap.put( "1-5",levels[4]);
        tmxMap.put( "1-6",levels[5]);
        tmxMap.put( "1-7",levels[6]);
        tmxMap.put( "1-8",levels[7]);


        levelIterator = tmxMap.keySet().iterator();
        currentLevel = levelIterator.next();
    }

    public static void loadNextLevel( GameStart gameStart )
    {
        if( levelIterator.hasNext())
        {
            currentLevel = levelIterator.next();
        }
        loadCurrentLevel( gameStart );
    }

    public static void loadCurrentLevel( GameStart gameStart )
    {
        gameStart.setScreen( new LevelScreen( gameStart, tmxMap.get( currentLevel ).tmxFile ));
    }



}
