package com.iudice.model.meta;

import com.iudice.GameStart;
import com.iudice.view.screen.LevelScreen;

import java.util.HashMap;
import java.util.Map;

public class LevelManager
{
    public static LevelManager instance;

    public static Map<Integer,Map<Integer,String>> tmxMap;

    public static int currentWorld;
    public static int currentLevel;
    public static String currentTmxFile;

    public LevelManager()
    {
        if (instance == null) {
            instance = this;
        }

        currentWorld = 1;
        currentLevel = 1;

        assignTmxFilesToMap();
    }

    private void assignTmxFilesToMap()
    {
        tmxMap = new HashMap<Integer,Map<Integer,String>>(  );
        Map<Integer,String> worldOne = new HashMap<Integer,String>(  );
        worldOne.put( 1, "maps/Level_1-1.tmx" );
        worldOne.put( 2, "maps/Level_1-2.tmx" );



        tmxMap.put( 1, worldOne );
    }

    public static void loadNextLevel( GameStart gameStart )
    {
        currentTmxFile =  tmxMap.get( currentWorld ).get( currentLevel );
        gameStart.setScreen( new LevelScreen( gameStart, currentTmxFile ));

        if(currentLevel == 4)
        {
            currentLevel = 1;
            currentWorld++;
        }
        else
        {
            currentLevel++;
        }

    }

}
