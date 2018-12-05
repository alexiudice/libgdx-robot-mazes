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
        tmxMap.put( "1-1",new Level( "1","1", "maps/Level_1-1.tmx", 4) );
        tmxMap.put( "1-2",new Level( "1","2", "maps/Level_1-2.tmx", 4) );


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
