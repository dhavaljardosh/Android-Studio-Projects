package com.dhavaljardosh.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Flappy_Bird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;

	Texture[] birds;
	int flapState= 0;
	int count = 0;
	int birdY;
	int velocity = 0;
	int gameState = 0;
	float gravity = 2;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
		birdY = Gdx.graphics.getHeight()/2-(birds[0].getHeight()/2);
	}

	@Override
	public void render () {



		if(gameState!=0) {
			if(Gdx.input.justTouched()){
				velocity = -20;
			}

			velocity= (int) (velocity+gravity);
			birdY -= velocity;

		} else{
			if(Gdx.input.justTouched()){
				gameState=1;
			}
		}

		if (count < 10) {
			count++;
			flapState = 0;
		} else {
			count = 0;
			flapState = 1;
		}


		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - (birds[flapState].getWidth() / 2), birdY);
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
