package com.dhavaljardosh.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Flappy_Bird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;

	ShapeRenderer shapeRenderer;


	Texture[] birds;
	Texture topTube;
	Texture bottomTube;

	int flapState= 0;
	int count = 0;
	int birdY;
	Circle birdCircle;



	int velocity = 0;
	int gameState = 0;

	float gravity = 2;
	float gap = 400;
	float maxTubeOffset;
	Random randomGenerator;
	int numberOfTubes = 4;
	float[] tubeOffset = new float[numberOfTubes];
	float[] tubeX = new float[numberOfTubes];
	float distanceBetweenTubes;
	int tubeVelocity = 4;

	Rectangle[] topTubeRectangle;
	Rectangle[] bottomTubeRectangle;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		shapeRenderer = new ShapeRenderer();
		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
		birdY = Gdx.graphics.getHeight()/2-(birds[0].getHeight()/2);
		birdCircle = new Circle();

		topTube = new Texture("toptube.png");
		bottomTube = new Texture("bottomtube.png");

		maxTubeOffset = Gdx.graphics.getHeight() / 2 - gap / 2 - 100;
		randomGenerator = new Random();
		distanceBetweenTubes = Gdx.graphics.getWidth() * 3/4;
		topTubeRectangle = new Rectangle[numberOfTubes];
		bottomTubeRectangle = new Rectangle[numberOfTubes];

		for(int i = 0;i<numberOfTubes;i++){


			tubeX[i] = Gdx.graphics.getWidth()/2 - topTube.getWidth()/2 + i * distanceBetweenTubes;

		}

	}

	@Override
	public void render () {

		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


		if(gameState!=0) {

			if(Gdx.input.justTouched()){
				velocity = -30;
			}


			for(int i = 0; i<numberOfTubes; i++){



				if(tubeX[i] < - topTube.getWidth()){
					tubeX[i] += numberOfTubes * distanceBetweenTubes;
					tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);

				} else{
					tubeX[i] = tubeX[i] - tubeVelocity;
				}


				batch.draw(topTube,tubeX[i],Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i]);
				batch.draw(bottomTube,tubeX[i],Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i]);

				topTubeRectangle[i] = new Rectangle(tubeX[i],Gdx.graphics.getHeight() / 2 + gap / 2+ tubeOffset[i], topTube.getWidth(),topTube.getHeight());
				bottomTubeRectangle[i] = new Rectangle(tubeX[i],Gdx.graphics.getHeight() / 2 - gap / 2  - bottomTube.getHeight()+ tubeOffset[i], bottomTube.getWidth(), bottomTube.getHeight());
				Gdx.app.log(String.valueOf(topTubeRectangle[i]),String.valueOf(bottomTubeRectangle[i]));
			}


			if(birdY>0 || velocity<0){
				velocity= (int) (velocity+gravity);
				birdY -= velocity;
			}

		} else{
			if(Gdx.input.justTouched()){
				gameState = 1;
			}
		}

		if (count < 10) {
			count++;
			flapState = 0;
		} else {
			count = 0;
			flapState = 1;
		}



		batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - (birds[flapState].getWidth() / 2), birdY);
		batch.end();

		birdCircle.set(Gdx.graphics.getWidth()/2,birdY+birds[flapState].getHeight()/2,birds[flapState].getWidth()/2);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);

		for(int i = 0;i<numberOfTubes;i++){
			shapeRenderer.setColor(Color.BLUE);
			shapeRenderer.rect(tubeX[i],Gdx.graphics.getHeight() / 2 + gap / 2+ tubeOffset[i], topTube.getWidth(),topTube.getHeight());
			shapeRenderer.rect(tubeX[i],Gdx.graphics.getHeight() / 2 - gap / 2  - bottomTube.getHeight() + tubeOffset[i], bottomTube.getWidth(), bottomTube.getHeight());

//			Gdx.app.log("Check "+String.valueOf(topTubeRectangle[i]),"Dhaval JArdosh");
//			if(Intersector.overlaps(birdCircle, topTubeRectangle[i])){
//				Gdx.app.log("Collision","Yes");
//			}
		}

		shapeRenderer.end();

	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
