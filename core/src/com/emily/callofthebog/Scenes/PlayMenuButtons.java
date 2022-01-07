package com.emily.callofthebog.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayMenuButtons implements Disposable {
    private Viewport viewport; //seperate for the tangible world

    public Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonPlay;
    private BitmapFont white, black;
    private Label heading;



    public PlayMenuButtons(SpriteBatch sb) {

        viewport = new FitViewport(com.emily.callofthebog.CallofTheBog.V_WIDTH, com.emily.callofthebog.CallofTheBog.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        table = new Table();
        table.top();
        table.setFillParent(true);

        atlas = new TextureAtlas(Gdx.files.internal("button/buttons.pack"));
        skin = new Skin(atlas);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        //textButtonStyle.up = skin.getDrawable();

        buttonPlay = new TextButton("PLAY", new TextButton.TextButtonStyle());


        white = new BitmapFont(Gdx.files.internal("font/white.fnt"), false);
        black = new BitmapFont(Gdx.files.internal("font/black.fnt"), false);

/*
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("up-button");
        textButtonStyle.down = skin.getDrawable("down-button");
        textButtonStyle.checked = skin.getDrawable("checked-button");
        button = new TextButton("Button1", textButtonStyle);

        table.add(button).expandX().padTop(10); //expands to length of screen
*/

        stage.addActor(table);//adds table to stage


    }


    @Override
    public void dispose() {

        stage.dispose();

    }
}
