package com.example.jardosh.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {

    private SpriteBatch batch;
    private BitmapFont font;
    private int screenWidth, screenHeight;
    private String message = "Touch Me!";

	@Override
	public void create () {
        batch = new SpriteBatch();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        font = new BitmapFont();
        font.setColor(Color.FIREBRICK);
        font.getData().scale(5);

        Gdx.input.setInputProcessor(this);

	}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

	@Override
	public void render () {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(font,message);

        float height = screenHeight/2 - layout.height/2;
        float width = screenWidth/2 - layout.width/2;

        batch.begin();
        font.draw(batch,message, width,height);
        batch.end();
	}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	    message = "Touch down at " + screenX + " + " +screenY;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        message = "Touch UP at " + screenX + " + " +screenY;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        message = "Dragging at " + screenX + " + " +screenY;
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
