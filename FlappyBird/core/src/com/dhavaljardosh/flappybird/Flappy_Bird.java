package com.dhavaljardosh.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

	int score = 0;
	int scoringTube = 0;

	BitmapFont font;

	int flapState= 0;
	int count = 0;
	int birdY;
	Circle birdCircle;



	float velocity = 0;
	int gameState = 0;

	float gravity = 2;
	float gap = 400;
	float maxTubeOffset;
	Random randomGenerator;
	int numberOfTubes = 4;
	float[] tubeOffset = new float[numberOfTubes];
	float[] tubeX = new float[numberOfTubes];
	float distanceBetweenTubes;
	float tubeVelocity = 4f;

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
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(10);



		topTube = new Texture("toptube.png");
		bottomTube = new Texture("bottomtube.png");

		maxTubeOffset = Gdx.graphics.getHeight() / 2 - gap / 2 - 100;
		randomGenerator = new Random();
		distanceBetweenTubes = Gdx.graphics.getWidth() * 3/4;
		topTubeRectangle = new Rectangle[numberOfTubes];
		bottomTubeRectangle = new Rectangle[numberOfTubes];

		for(int i = 0;i<numberOfTubes;i++){
			tubeX[i] = Gdx.graphics.getWidth()/2 - topTube.getWidth()/2 + Gdx.graphics.getWidth() + i * distanceBetweenTubes;
			topTubeRectangle[i] = new Rectangle();
			bottomTubeRectangle[i]=new Rectangle();
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

				if(tubeX[scoringTube] < Gdx.graphics.getWidth()/2){
					score++;

					if(scoringTube < numberOfTubes - 1){
						scoringTube++;
					}else{
						scoringTube = 0;
					}


				}
				Gdx.app.log("Score", String.valueOf(score));

				if(tubeX[i] < - topTube.getWidth()){
					tubeX[i] += numberOfTubes * distanceBetweenTubes;
					tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
				} else{
					tubeX[i] -= tubeVelocity;


				}





				batch.draw(topTube,tubeX[i],Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i]);
				batch.draw(bottomTube,tubeX[i],Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i]);

				topTubeRectangle[i].set(tubeX[i],Gdx.graphics.getHeight() / 2 + gap / 2+ tubeOffset[i], topTube.getWidth(),topTube.getHeight());
				bottomTubeRectangle[i].set(tubeX[i],Gdx.graphics.getHeight() / 2 - gap / 2  - bottomTube.getHeight()+ tubeOffset[i], bottomTube.getWidth(), bottomTube.getHeight());
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

		if (count < 6) {
			count++;
			flapState = 0;
		} else {
			count = 0;
			flapState = 1;
		}



		batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - (birds[flapState].getWidth() / 2), birdY);

		//Place the Score on Screen
		font.draw(batch,String.valueOf(score),100,300);

		batch.end();

		birdCircle.set(Gdx.graphics.getWidth()/2,birdY+birds[flapState].getHeight()/2,birds[flapState].getWidth()/2);

//		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		shapeRenderer.setColor(Color.GREEN);
//		shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);

		for(int i = 0;i<numberOfTubes;i++){
//			shapeRenderer.setColor(Color.BLUE);
//			shapeRenderer.rect(tubeX[i],Gdx.graphics.getHeight() / 2 + gap / 2+ tubeOffset[i], topTube.getWidth(),topTube.getHeight());
//			shapeRenderer.rect(tubeX[i],Gdx.graphics.getHeight() / 2 - gap / 2  - bottomTube.getHeight() + tubeOffset[i], bottomTube.getWidth(), bottomTube.getHeight());

//			if(Intersector.overlaps(birdCircle, topTubeRectangle[i]) || Intersector.overlaps(birdCircle, bottomTubeRectangle[i])){
//				Gdx.app.log("Collision","Yes");
//			}
		}

//		shapeRenderer.end();

	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
