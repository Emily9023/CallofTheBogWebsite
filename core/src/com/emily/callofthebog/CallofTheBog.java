package com.emily.callofthebog;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.emily.callofthebog.Screens.PlayMenu;
import com.emily.callofthebog.Screens.PlayScreen;

import java.applet.Applet;

public class CallofTheBog extends Game {

	public static final int V_WIDTH = 1600;
	public static final int V_HEIGHT = 390;
	public SpriteBatch batch; //contain that holds images and textures etc. memory intensive so only requires one
	public static final float PPM = 100;

	public static final short DEFAULT_BIT = 1;
	public static final short PENGO_BIT = 2;
	public static final short BOULDER_BIT = 4;
	public static final short EVIL_BIT = 8;
	public static final short DESTROYED_BIT = 16;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();

	}

}
