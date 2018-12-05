package com.iudice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.iudice.model.meta.GameManager;
import com.iudice.model.meta.LevelManager;

public class GameStart extends Game {
	public SpriteBatch batch;

	private GameManager gameManager;
	private LevelManager levelManager;

	@Override
	public void create () {
		batch = new SpriteBatch();

		if (GameManager.instance != null) {
			gameManager = GameManager.instance;
		}
		else {
			gameManager = new GameManager();
		}

		if (LevelManager.instance != null)
		{
			levelManager = LevelManager.instance;
		}
		else{
			levelManager = new LevelManager();
		}

		LevelManager.loadNextLevel( this );
	}

	@Override
	public void render () {
		super.render();
	}


	@Override
	public void dispose() {
		super.dispose();
		gameManager.dispose();
	}

}
